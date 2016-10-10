package com.itheima.studentsystem;

import java.util.List;

import com.itheima.studentsystem.db.dao.StudentDao;
import com.itheima.studentsystem.domain.Student;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText ed_name;
	RadioGroup rgb;
	LinearLayout ll_layout;
	
	StudentDao sdao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ʼ���ؼ� 
		ed_name = (EditText) findViewById(R.id.ed_name);
		rgb = (RadioGroup) findViewById(R.id.rgb);
		
		ll_layout = (LinearLayout) findViewById(R.id.ll_layout);
		
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
	public void display(View v){
		
		//��ѯ ���е�ѧ������Ϣ ,Ȼ��  ��ʾ ��  ll_layout
		List<Student> list=  sdao.getAll();
		
		//�Ƴ����� ��� 
		ll_layout.removeAllViews();
		for (Student student : list) {
			
			TextView tv = new TextView(this);
			tv.setText(student.toString());
			ll_layout.addView(tv);
		}
	}
}
