package com.itheima.studentsystem;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.studentsystem.db.dao.StudentDao;
import com.itheima.studentsystem.domain.Student;

public class MainActivity extends Activity {

	EditText ed_name;
	RadioGroup rgb;
	
	ListView lv;
	
	StudentDao sdao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ʼ���ؼ� 
		ed_name = (EditText) findViewById(R.id.ed_name);
		rgb = (RadioGroup) findViewById(R.id.rgb);
		
		lv = (ListView) findViewById(R.id.lv);
		
		
		
		sdao = new StudentDao(this);
	}

	
//����ѧ����Ϣ 
	public void save(View v){
		String name = ed_name.getText().toString().trim();
		if(TextUtils.isEmpty(name)){
			Toast.makeText(this, "��������Ϊ��", 0).show();
			return;
		}
		
		String sex ="male";
		//�õ� �� ѡ��� radio ��ѡ�� 
		int id = rgb.getCheckedRadioButtonId();
		if(id==R.id.male){
			sex ="male";
		}else if(id==R.id.female){
			sex="female";
		}else if(id==R.id.weizhi){
			sex="male";
		}
		
		//���� dao ��ѧ������Ϣ
		
		sdao.insert(name, sex);
		
		Toast.makeText(this, "����ɹ�", 0).show();
	}
//��ʾ ѧ����Ϣ 
	List<Student> list;
	public void display(View v){
		
		//��ѯ ���е�ѧ������Ϣ ,Ȼ��  ��ʾ ��  ll_layout
		list = sdao.getAll();
		
		lv.setAdapter(new MyAdapter());
		
	}
	
	private class MyAdapter extends BaseAdapter{

		
		//���� ϵͳ ���� ���ٸ� ��Ŀ , item
		@Override
		public int getCount() {
			return list.size();
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			// ͨ�� ���뽫  д�õ�layout �ļ� ת���� View���� 
			// �ȶ��� �� xml layout �ļ� , ��ʾ�� ���� һ����  item ��Ŀ�� �ļ����� 
			
			//�õ� ��ǰ�� �� Ӧ����ʾ�ĸ�ѧ������Ϣ 
			Student student = list.get(position);
			
			//���д������� �� һ�� xml layout �ļ�, ת���� view ���� 
			RelativeLayout rl_layout=(RelativeLayout) View.inflate(MainActivity.this, R.layout.item, null);
			
			TextView tv_name = (TextView) rl_layout.findViewById(R.id.tv_name);
			
			ImageView iv_sex = (ImageView) rl_layout.findViewById(R.id.iv_sex);
			
			tv_name.setText(student.getName());
			
			if("male".equals(student.getSex())){
				iv_sex.setImageResource(R.drawable.nan);
			}else{
				
				iv_sex.setImageResource(R.drawable.nv);
			}
			
			
			return rl_layout;
		}
		
		
		

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}




		

		
	}
}
