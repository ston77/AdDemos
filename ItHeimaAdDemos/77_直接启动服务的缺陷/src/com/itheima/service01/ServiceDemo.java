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
	 * ���Ƿ����е��ڲ�����
	 */
	public void methodInService(){
		//this-=--null.
		Toast.makeText(this, "���Ƿ���ķ������ұ�������.22222", 0).show();
//		System.out.println("���Ƿ����еķ������ұ�������...");
	}
}
