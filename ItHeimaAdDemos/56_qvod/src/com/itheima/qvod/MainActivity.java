package com.itheima.qvod;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("oncreate---");
	}

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("onStart---���ڲ�����Ƶ�������ܴ�");
	};
	
	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("onStop---��ͣ��Ƶ�Ĳ��š�����");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy---");
	}

}
