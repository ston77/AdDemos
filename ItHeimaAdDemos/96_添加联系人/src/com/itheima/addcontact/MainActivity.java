package com.itheima.addcontact;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText et_name ,et_phone ,et_email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_name = (EditText) findViewById(R.id.name);
		et_phone = (EditText) findViewById(R.id.phone);
		et_email = (EditText) findViewById(R.id.email);
	}

	public void save(View v){
		//添加一条联系人记录
		ContentResolver resolver = getContentResolver();
		//2定义查询raw_contact表的uri
		Uri contactUri = Uri.parse("content://com.android.contacts/raw_contacts");
		//查询data表的uri
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		
		//由于不知道当前的contact_id已经走到了多少，所以不能盲目直接编写，应该去查询
		//该表中的_id列，然后倒序排列，取第一条就可以了。 
		Cursor cursor = resolver.query(contactUri, new String[]{"_id"}, null, null, "_id desc");
		cursor.moveToFirst();
		int id = cursor.getInt(0)+1; //在原来的id基础上+1 ，形成了现在新添加的id
		
		ContentValues values = new ContentValues();
		values.put("contact_id", id);
		resolver.insert(contactUri, values);
		
		
		//往data表里面添加数据
		//添加姓名
		ContentValues nameVal = new ContentValues();
		nameVal.put("data1", et_name.getText().toString()); //添加姓名
		nameVal.put("raw_contact_id", id); //添加这个数据属于谁的uri
		nameVal.put("mimetype", "vnd.android.cursor.item/name"); //指定添加的数据属于什么类型
		resolver.insert(dataUri, nameVal);//插入记录
		
		//添加电话
		ContentValues phoneVal = new ContentValues();
		phoneVal.put("data1", et_phone.getText().toString());
		phoneVal.put("raw_contact_id", id);
		phoneVal.put("mimetype", "vnd.android.cursor.item/phone_v2");
		resolver.insert(dataUri, phoneVal);
		
		//添加邮箱
		ContentValues emailVal = new ContentValues();
		emailVal.put("data1", et_email.getText().toString());
		emailVal.put("raw_contact_id", id);
		emailVal.put("mimetype", "vnd.android.cursor.item/email_v2");
		resolver.insert(dataUri, emailVal);
		
	
		Toast.makeText(this, "添加成功", 0).show();
	}
}
