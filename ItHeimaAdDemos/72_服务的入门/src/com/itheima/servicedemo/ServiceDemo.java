package com.itheima.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

/**
 * 
 * service实际上就是一个长期运行在后台，没有界面的activity..
 * 
 */
public class ServiceDemo extends Service {
	
	boolean flag ; 

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	//服务第一次被创建的时候调用
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("onCreate---");
		new Thread(){
			public void run() {
				flag = true;
				while(flag){
					System.out.println("检测是否有新的设备被接入了..."+Thread.currentThread().getName());
					SystemClock.sleep(500);
					//Thread.sleep(1000);
				}
			};
		}.start();
		
	}
	
	
	//服务被销毁的时候调用
	@Override
	public void onDestroy() {
		super.onDestroy();
		flag = false;
		System.out.println("onDestroy---");
	}

}
