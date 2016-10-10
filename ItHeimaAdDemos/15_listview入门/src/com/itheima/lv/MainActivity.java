package com.itheima.lv;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
/*
 *  重量级的 ,专门用于显示 数据到 屏幕上的 控件 ...
 * 
 */
public class MainActivity extends Activity {

	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// view ---视图 对象 
		lv = (ListView) findViewById(R.id.lv);
		
		//adapter -- 控制器 -- controller
		lv.setAdapter(new MyAdapter());
		
	}

	
	// ListAdapter提供的 有 现成的 实现 类, 这些 是 与 之前 所学习的 是一样的 
	// BaseXXX, SimpleXXX, DefaultXXX

	private class MyAdapter extends BaseAdapter{

		// 通过 adapter告诉 最要在 listView(view) 中显示 多少条 目 的数据  
		@Override
		public int getCount() {
			return 100;
		}

		
		//用来 显示 每个条目时 会被调用到的 方法 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			TextView tv = new TextView(MainActivity.this);
			
			tv.setText("我是第 " +position+ "个条目 ");
			
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
