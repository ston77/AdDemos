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
 *  �������� ,ר��������ʾ ���ݵ� ��Ļ�ϵ� �ؼ� ...
 * 
 */
public class MainActivity extends Activity {

	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// view ---��ͼ ���� 
		lv = (ListView) findViewById(R.id.lv);
		
		//adapter -- ������ -- controller
		lv.setAdapter(new MyAdapter());
		
	}

	
	// ListAdapter�ṩ�� �� �ֳɵ� ʵ�� ��, ��Щ �� �� ֮ǰ ��ѧϰ�� ��һ���� 
	// BaseXXX, SimpleXXX, DefaultXXX

	private class MyAdapter extends BaseAdapter{

		// ͨ�� adapter���� ��Ҫ�� listView(view) ����ʾ ������ Ŀ ������  
		@Override
		public int getCount() {
			return 100;
		}

		
		//���� ��ʾ ÿ����Ŀʱ �ᱻ���õ��� ���� 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			TextView tv = new TextView(MainActivity.this);
			
			tv.setText("���ǵ� " +position+ "����Ŀ ");
			
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
