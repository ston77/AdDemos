package com.itheima.bankboss;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
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

	public void insert(View v) {
		// 其他应用通过内容提供者 content provider 暴露数据
		// 本应用想操作内容提供者---内容解析者

		// 1.通过上下文得到内容解析者
		ContentResolver resolver = getContentResolver();

		// 2. 要访问内容提供者，必须要指定口令
		// 由于内容提供者省级了口令，既有主机名也有path路径，所以这里为了能够匹配成功
		// 必须加上主机名还有path路径名
		Uri uri = Uri.parse("content://com.itheima.db.BANK/account");

		ContentValues values = new ContentValues();
		values.put("name", "zhangsan");
		values.put("money", 5000);

		// 3.调用内容提供者中的添加方法
		resolver.insert(uri, values);

	}

	public void delete(View v) {

		// 1.通过上下文得到内容解析者
		ContentResolver resolver = getContentResolver();

		// 2. 要访问内容提供者，必须要指定口令
		// 由于内容提供者省级了口令，既有主机名也有path路径，所以这里为了能够匹配成功
		// 必须加上主机名还有path路径名
		Uri uri = Uri.parse("content://com.itheima.db.BANK/account");
		resolver.delete(uri, "name=?", new String[] { "zhangsan" });
	}

	public void update(View v) {

		// 1.通过上下文得到内容解析者
		ContentResolver resolver = getContentResolver();

		// 2. 要访问内容提供者，必须要指定口令
		// 由于内容提供者省级了口令，既有主机名也有path路径，所以这里为了能够匹配成功
		// 必须加上主机名还有path路径名
		Uri uri = Uri.parse("content://com.itheima.db.BANK/account");

		//定义更新后的数据
		ContentValues values = new ContentValues();
		values.put("money", 500);

		resolver.update(uri, values, "name=?", new String[]{"zhangsan"});
	}

	public void query(View v) {
		
		// 1.通过上下文得到内容解析者
		ContentResolver resolver = getContentResolver();

		// 2. 要访问内容提供者，必须要指定口令
		// 由于内容提供者省级了口令，既有主机名也有path路径，所以这里为了能够匹配成功
		// 必须加上主机名还有path路径名
		Uri uri = Uri.parse("content://com.itheima.db.BANK/account");
		
		Cursor cursor =resolver.query(uri, null, null, null, null);
		while(cursor.moveToNext()){
			int money = cursor.getInt(cursor.getColumnIndex("money")); 
			System.out.println("mone==="+money);
		}
		cursor.close();
	}
}
