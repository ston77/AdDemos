package com.itheima.querycontacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	public void query(View v){
		//1.获取到内容解析者对象
		ContentResolver resolver = getContentResolver();
		
		//2定义查询raw_contact表的uri
		Uri contactUri = Uri.parse("content://com.android.contacts/raw_contacts");
		//查询data表的uri
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		//3.查询联系人的ID
		Cursor cursor = resolver.query(contactUri, new String[]{"contact_id" }, null, null, null);
		while(cursor.moveToNext()){
			//执行这一行代码，仅仅只查询到了联系人的ID，但是如果还想获取到联系人的其他信息，比如：姓名、电话、邮箱，
			//那么久应该把id放到其他表中去查询了， data
			int id= cursor.getInt(cursor.getColumnIndex("contact_id"));
			System.out.println("id==="+id);
			
			//根据联系人的ID，去data表查询数据，把数据这个联系人的数据查询出来。
			/**
			 * 在查询联系人的数据类型的时候，系统并不是真的去查询data表，而是去查询了view_data表，
			 * 所以， 指定的miemtype,不能写成mimetype_id .并且返回的类型应该是String
			 */
			Cursor dataCursor = resolver.query(dataUri, new String[]{"data1", "mimetype"}, "raw_contact_id=?", new String[]{id+""}, null);
			while(dataCursor.moveToNext()){
				String data = dataCursor.getString(dataCursor.getColumnIndex("data1"));
				System.out.println("data==="+data);
				String mimetype= dataCursor.getString(dataCursor.getColumnIndex("mimetype"));
				System.out.println("mimetype==="+mimetype);
			}
			dataCursor.close();
			
					
		}
		
		cursor.close();
		
		
		
	}
}
