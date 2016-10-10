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
	 * 安全支付的方法
	 * @param username
	 * @param pwd
	 * @param money
	 * @return 返回支付成功与否的状态码， 404： 账号或者密码错误 ， 200 ：支付成功  ， 250 ： 余额不足
	 */
	public int safePay(String username , String pwd , int money){
		if("zhangsan".equals(username) && "10086".equals(pwd)){
			//如果账号密码都对，就让其去支付
			if(money < 500){ //让其支付
				return 200;
			}else { //不好意思，余额不足。
				return 250;
			}
		}
		return 404; //账号或者密码错误
	}
}
