package com.itheima.studentsystem;

import java.util.List;

import com.itheima.studentsystem.db.dao.StudentDao;
import com.itheima.studentsystem.domain.Student;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
			
			System.out.println("getView ��������  : " + position);
			TextView tv = new TextView(MainActivity.this);
			
			Student student = list.get(position);
			
			//��Ҫ��ʾ����ѧ������Ϣ 
			tv.setText(student.toString() +"�� " +position+ "��ѧ�� ");
			
			return tv;
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
