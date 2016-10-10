package com.itheima.share;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void share(View v) {

		/*
		 * <intent-filter> <action android:name="android.intent.action.VIEW" />
		 * <action android:name="android.intent.action.SENDTO" /> <category
		 * android:name="android.intent.category.DEFAULT" /> <category
		 * android:name="android.intent.category.BROWSABLE" /> <data
		 * android:scheme="sms" /> <data android:scheme="smsto" />
		 * </intent-filter>
		 */

		for (int i = 0; i < 100; i++) {
			Intent intent = new Intent();
			intent.setAction("android.intent.action.SENDTO");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.setData(Uri.parse("smsto:"));
			intent.putExtra("sms_body",
					"�������һ������Ӧ�ã�������Ʒ���������Ǻ������Ա�����ģ����ܸ��㣬������ˣ���Ʒ����ǳ�׼ȷ��http://www.itheima.com");
			
			startActivity(intent);
		}
	}
}
