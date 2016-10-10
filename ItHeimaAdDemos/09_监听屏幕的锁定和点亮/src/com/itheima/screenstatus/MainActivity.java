package com.itheima.screenstatus;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;

public class MainActivity extends Activity {
	
	ScreenStatusReceiver receiver  ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		//ע��㲥������
		
		//�����㲥�����ߵĶ���
		receiver= new ScreenStatusReceiver();
		
		
		//ָ���ù㲥����������ʲô���Ķ����㲥
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		
		//ע��һ���㲥������
		registerReceiver(receiver, filter);
		
		
		
		
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//���ע��㲥������
		unregisterReceiver(receiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
