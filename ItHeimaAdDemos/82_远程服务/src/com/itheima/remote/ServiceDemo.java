package com.itheima.remote;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ServiceDemo extends Service {
	
	/**
	 * �ı�̳еĹ�ϵ��ԭ���Ǽ̳���Biner ʵ����IService�ӿ�
	 * ������ʾΪ������Զ�̵��ã�����Ӧ������̳�IService.stub
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
		System.out.println("���Ƿ����еķ������ұ������ˡ���");
	}

}
