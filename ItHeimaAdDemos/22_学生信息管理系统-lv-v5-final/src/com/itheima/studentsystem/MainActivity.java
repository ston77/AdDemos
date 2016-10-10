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
		
		//初始化控件 
		ed_name = (EditText) findViewById(R.id.ed_name);
		rgb = (RadioGroup) findViewById(R.id.rgb);
		
		lv = (ListView) findViewById(R.id.lv);
		
		
		sdao = new StudentDao(this);
		
		refreshData();
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
			
			//通知 数据 集合 发生变化了 
			// 在 只 刷新 部分 界面的数据 
			myadapter.notifyDataSetChanged();
		}
	}
	
	private class MyAdapter extends BaseAdapter{
		
		//告诉 系统 会有 多少个 条目 , item
		@Override
		public int getCount() {
			return list.size();
		}
		
		
		// listview是出去 面试时 ,经常被问到的 .
		
		// 请描述 listView中  getView 的convertView是用来干什么的 
		// convertView: 用来做优化的 ,了它 就相当于 哪些 托儿 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			final Student student = list.get(position);
			System.out.println("position : " + position + " convertView : " + convertView);
			View v = null;
			
			if(convertView==null){
				//通过 布局文件 转换为 一个view 对象
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
					
					/*System.out.println(" delete 被点击了 .... 哦 ");
					
					//调用 dao 去删除数据库中的数据了 
					
					sdao.delete(student.getName());
					refreshData();
					
					Toast.makeText(MainActivity.this, "删除成功", 0).show();*/
					
					AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
					builder.setTitle("删除学生信息.");
					builder.setMessage("亲, 您确认要删除 这个学生的信息吗?");
					builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							boolean result = sdao.delete(student.getName());
							if(result){
								
								refreshData();
								
								Toast.makeText(MainActivity.this, "删除成功", 0).show();
							}else{
								
								Toast.makeText(MainActivity.this, "删除失败", 0).show();
							}
						}
					});
					
					builder.setNegativeButton("取消", null);
					
					//显示 dialog 
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
