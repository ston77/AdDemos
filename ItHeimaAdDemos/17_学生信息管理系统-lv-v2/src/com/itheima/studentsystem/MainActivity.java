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
		
		//初始化控件 
		ed_name = (EditText) findViewById(R.id.ed_name);
		rgb = (RadioGroup) findViewById(R.id.rgb);
		
		lv = (ListView) findViewById(R.id.lv);
		
		
		
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
	List<Student> list;
	public void display(View v){
		
		//查询 所有的学生的信息 ,然后  显示 到  ll_layout
		list = sdao.getAll();
		
		lv.setAdapter(new MyAdapter());
		
	}
	
	private class MyAdapter extends BaseAdapter{

		
		//告诉 系统 会有 多少个 条目 , item
		@Override
		public int getCount() {
			return list.size();
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			// 通过 代码将  写好的layout 文件 转换成 View对象 
			// 先定义 个 xml layout 文件 , 显示的 就是 一个个  item 条目的 文件布局 
			
			//拿到 当前的 是 应该显示哪个学生的信息 
			Student student = list.get(position);
			
			//这行代码用于 将 一个 xml layout 文件, 转换成 view 对象 
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
