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
		
		
		
		//注册广播接受者
		
		//创建广播接受者的对象
		receiver= new ScreenStatusReceiver();
		
		
		//指定该广播接受者收听什么样的动作广播
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		
		//注册一个广播接受者
		registerReceiver(receiver, filter);
		
		
		
		
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//解除注册广播接受者
		unregisterReceiver(receiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
