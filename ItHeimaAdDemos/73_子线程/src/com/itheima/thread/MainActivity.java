package com.itheima.thread;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new Thread(){
			public void run() {
				while(true){
					System.out.println("检测是否有新的设备被接入了.2222..");
					SystemClock.sleep(500);
					//Thread.sleep(1000);
				}
			};
		}.start();
		
	}

}
