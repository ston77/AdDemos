package com.itheima.servicedemo;

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

	//��������
	public void start(View v){
		Intent service = new Intent(this,ServiceDemo.class);
		startService(service);
	}
	//ֹͣ����
	public void stop(View v){
		Intent service = new Intent(this,ServiceDemo.class);
		stopService(service);
		
	}
}
