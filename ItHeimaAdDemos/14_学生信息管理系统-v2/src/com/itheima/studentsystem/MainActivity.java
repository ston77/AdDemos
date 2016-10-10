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
		
		//初始化控件 
		ed_name = (EditText) findViewById(R.id.ed_name);
		rgb = (RadioGroup) findViewById(R.id.rgb);
		
		ll_layout = (LinearLayout) findViewById(R.id.ll_layout);
		
		sdao = new StudentDao(this);
	}

	
//保存学生信息 
	public void save(View v){
		String name = ed_name.getText().toString().trim();
		if(TextUtils.isEmpty(name)){
			Toast.makeText(this, "姓名不能为空", 0).show();
			return;
		}
		
		String sex ="male";
		//拿到 被 选择的 radio 单选项 
		int id = rgb.getCheckedRadioButtonId();
		if(id==R.id.male){
			sex ="male";
		}else if(id==R.id.female){
			sex="female";
		}else if(id==R.id.weizhi){
			sex="male";
		}
		
		//调用 dao 存学生的信息
		
		sdao.insert(name, sex);
		
		Toast.makeText(this, "保存成功", 0).show();
	}
//显示 学生信息 
	public void display(View v){
		
		//查询 所有的学生的信息 ,然后  显示 到  ll_layout
		List<Student> list=  sdao.getAll();
		
		//移除后再 添加 
		ll_layout.removeAllViews();
		for (Student student : list) {
			
			TextView tv = new TextView(this);
			tv.setText(student.toString());
			ll_layout.addView(tv);
		}
	}
}
