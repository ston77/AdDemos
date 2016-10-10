package com.itheima.heimapay;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ServiceDemo extends Service {
	
	private class MyBinder extends IService.Stub{

		@Override
		public int callSafePay(String username, String pwd, int money) {
			return safePay(username, pwd, money);
		}
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind---");
		return new MyBinder();
	}
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("onCreate---");
	}

	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("onDestroy---");
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("onUnbind---");
		return super.onUnbind(intent);
	}
	
	/**
	 * 
	 * ��ȫ֧���ķ���
	 * @param username
	 * @param pwd
	 * @param money
	 * @return ����֧���ɹ�����״̬�룬 404�� �˺Ż���������� �� 200 ��֧���ɹ�  �� 250 �� ����
	 */
	public int safePay(String username , String pwd , int money){
		if("zhangsan".equals(username) && "10086".equals(pwd)){
			//����˺����붼�ԣ�������ȥ֧��
			if(money < 500){ //����֧��
				return 200;
			}else { //������˼�����㡣
				return 250;
			}
		}
		return 404; //�˺Ż����������
	}
}
