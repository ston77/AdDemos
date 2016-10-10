package com.itheima.querycontacts;

import java.util.ArrayList;
import java.util.List;

import com.itheima.querycontacts.domain.ContactInfo;
import com.itheima.querycontacts.utils.ContactsUtil;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final List<ContactInfo> contacts = ContactsUtil .readContacts(this);
		
		ListView lv  = (ListView)findViewById(R.id.lv);
		lv.setAdapter(new BaseAdapter() {
			
			
			@Override
			public int getCount() {
				return contacts.size();
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				
				TextView tv = new TextView (MainActivity.this);
				ContactInfo info = contacts.get(position);
				tv.setText(info.toString());
				return tv;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}
		
		});
	}

	/**
	 * 获取所有的联系人数据
	 * @param v 
	 * @return
	 */
	public void query(View v){
		List<ContactInfo> contacts = ContactsUtil .readContacts(this);
		for (ContactInfo info : contacts) {
			System.out.println(info.toString());
		}
		
	}
}
