package com.itheima.intentdemo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v) {

		Intent intent = new Intent();
		intent.setAction("com.itheima.intentdemo.action.SECOND");
		intent.addCategory("android.intent.category.DEFAULT");
		
		//ָ������
		intent.setDataAndType(Uri.parse("itheima://www.itheima.com"), "text/plain");
		/*
		 * �������������������ǰ������ݣ�Ϊ�˱�֤���Ƕ����ڣ�������ʹ��setDataAndType
		 * 
		 * intent.setType("text/plain");
		intent.setData(Uri.parse("itheima://www.itheima.com"));*/
		startActivity(intent);
	}
}
