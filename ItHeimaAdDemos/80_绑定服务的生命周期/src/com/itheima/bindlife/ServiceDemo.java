package com.itheima.bindlife;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ServiceDemo extends Service {
	
	class MyBinder extends Binder{
		public void callMethodInService(){
			methodInService();
		}
	}

	//成功绑定上
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind---");
		return new MyBinder();
	}
	
	//服务解除绑定
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("onUnbind---");
		return super.onUnbind(intent);
	}
	
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onStartCommand---");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onCreate() {
		System.out.println("onCreate---");
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy---");
	}
	
	
	public void methodInService(){
		System.out.println("我是服务的方法，我被调用了。。");
	}

}
