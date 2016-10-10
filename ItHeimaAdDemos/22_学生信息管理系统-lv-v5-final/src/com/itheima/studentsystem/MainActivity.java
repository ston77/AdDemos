package com.itheima.studentsystem;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
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
		
		refreshData();
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
		refreshData();
		
	}

	MyAdapter myadapter;
	

	private void refreshData() {
		list = sdao.getAll();
		if(myadapter==null){
			myadapter = new MyAdapter();
			lv.setAdapter(myadapter);
		}else{
//			lv.setAdapter(myadapter);
			
			//֪ͨ ���� ���� �����仯�� 
			// �� ֻ ˢ�� ���� ��������� 
			myadapter.notifyDataSetChanged();
		}
	}
	
	private class MyAdapter extends BaseAdapter{
		
		//���� ϵͳ ���� ���ٸ� ��Ŀ , item
		@Override
		public int getCount() {
			return list.size();
		}
		
		
		// listview�ǳ�ȥ ����ʱ ,�������ʵ��� .
		
		// ������ listView��  getView ��convertView��������ʲô�� 
		// convertView: �������Ż��� ,���� ���൱�� ��Щ �ж� 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			final Student student = list.get(position);
			System.out.println("position : " + position + " convertView : " + convertView);
			View v = null;
			
			if(convertView==null){
				//ͨ�� �����ļ� ת��Ϊ һ��view ����
				v = View.inflate(MainActivity.this, R.layout.item, null);
			}else{
				v = convertView;
			}
			
			TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
			tv_name.setText(student.getName());
			
			
			ImageView iv_sex = (ImageView) v.findViewById(R.id.iv_sex);
			if("male".equals(student.getSex())){
				iv_sex.setImageResource(R.drawable.nan);
			}else{
				iv_sex.setImageResource(R.drawable.nv);
			}
			
			v.findViewById(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					/*System.out.println(" delete ������� .... Ŷ ");
					
					//���� dao ȥɾ�����ݿ��е������� 
					
					sdao.delete(student.getName());
					refreshData();
					
					Toast.makeText(MainActivity.this, "ɾ���ɹ�", 0).show();*/
					
					AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
					builder.setTitle("ɾ��ѧ����Ϣ.");
					builder.setMessage("��, ��ȷ��Ҫɾ�� ���ѧ������Ϣ��?");
					builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							boolean result = sdao.delete(student.getName());
							if(result){
								
								refreshData();
								
								Toast.makeText(MainActivity.this, "ɾ���ɹ�", 0).show();
							}else{
								
								Toast.makeText(MainActivity.this, "ɾ��ʧ��", 0).show();
							}
						}
					});
					
					builder.setNegativeButton("ȡ��", null);
					
					//��ʾ dialog 
					builder.show();
				}
			});
			
			
			return v;
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
