package com.itheima.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

/**
 * 
 * serviceʵ���Ͼ���һ�����������ں�̨��û�н����activity..
 * 
 */
public class ServiceDemo extends Service {
	
	boolean flag ; 

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	//�����һ�α�������ʱ�����
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("onCreate---");
		new Thread(){
			public void run() {
				flag = true;
				while(flag){
					System.out.println("����Ƿ����µ��豸��������..."+Thread.currentThread().getName());
					SystemClock.sleep(500);
					//Thread.sleep(1000);
				}
			};
		}.start();
		
	}
	
	
	//�������ٵ�ʱ�����
	@Override
	public void onDestroy() {
		super.onDestroy();
		flag = false;
		System.out.println("onDestroy---");
	}

}
