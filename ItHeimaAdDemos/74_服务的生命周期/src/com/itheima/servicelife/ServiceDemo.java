package com.itheima.servicelife;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		System.out.println("onStart");
	}
	
	//每次一调用startService方法，都会执行我，启动服务的意思
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy");
	}

}
