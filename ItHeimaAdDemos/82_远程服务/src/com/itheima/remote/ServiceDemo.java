package com.itheima.remote;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ServiceDemo extends Service {
	
	/**
	 * 改变继承的关系，原来是继承了Biner 实现了IService接口
	 * 但是显示为了满足远程调用，所以应该让其继承IService.stub
	 */
	private class  MyBinder extends IService.Stub{
		@Override
		public void callMethodInService() {
			methodInService();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind--");
		return new MyBinder();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("onCreate--");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy--");
	}
	
	public void methodInService(){
		System.out.println("我是服务中的方法，我被调用了。。");
	}

}
