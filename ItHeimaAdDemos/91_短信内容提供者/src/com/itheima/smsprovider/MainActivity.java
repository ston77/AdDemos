package com.itheima.smsprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	//添加短信
	public void insert(View v) {
		ContentResolver resolver = getContentResolver();
		//定义口令，这个口令，通过查看上层应用中的provider中的TelephonyProvider 这个应用中
//		清单文件和源代码
		Uri uri = Uri.parse("content://sms");
		ContentValues values = new ContentValues();
		values.put("address", "110");
		values.put("date", System.currentTimeMillis());
		values.put("type", 1);
		values.put("body", "您好，尊敬的覃先生：恭喜您荣获好市民奖，希望您在以后的生活中，勇于和敢于扶老奶奶过马路。");
		
		//操作短信的添加方法
		resolver.insert(uri, values);
	}

	//删除短信
	public void delete(View v) {
		ContentResolver resolver = getContentResolver();
		//定义口令，这个口令，通过查看上层应用中的provider中的TelephonyProvider 这个应用中
		//清单文件和源代码
		Uri uri = Uri.parse("content://sms");
		resolver.delete(uri, "address=?", new String[]{"110"});
		Toast.makeText(this, "删除短息成功", 0).show();
	}
}
