package com.itheima.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView lv = (ListView) findViewById(R.id.lv);
		
		List<String> list = new ArrayList<String>();
		list.add("西门庆");
		list.add("西门吹雪");
		list.add("西门抽血");
		list.add("西门抽筋");
		
		String[] values ={"北京","深圳","广州","惠州","佛山"};
		
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item, values));
		
	}

	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
