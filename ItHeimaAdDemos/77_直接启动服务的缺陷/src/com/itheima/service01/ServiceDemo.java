package com.itheima.service01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ServiceDemo extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("onCreate");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy");
	}
	
	/**
	 * 这是服务中的内部方法
	 */
	public void methodInService(){
		//this-=--null.
		Toast.makeText(this, "我是服务的方法，我被调用了.22222", 0).show();
//		System.out.println("我是服务中的方法，我被调用了...");
	}
}
