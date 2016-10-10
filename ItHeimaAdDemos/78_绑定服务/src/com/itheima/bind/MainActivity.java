package com.itheima.bind;


import com.itheima.bind.ServcieDemo.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {
	MyBinder binder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	/**
	 * 监听服务的状态，服务是启动还是停止都会收到信息。
	 */
	class MyConn implements ServiceConnection{
		/**
		 * 如果服务能够成功绑定上，那么这个方法将会调用，启动的参数service就是服务返回的内部对象 MyBinder
		 */
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//获取到服务内部返回的代理对象 ，用binder承接起来
			binder = (MyBinder) service;
			
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {}
	}

	//绑定服务
	public void bind(View v) {
		Intent service = new Intent(this , ServcieDemo.class);
		/**
		 * 第一个参数：intent对象
		 * 第二个参数：servcieConnection  用于监听服务当前的状态
		 * 第三个参数：BIND_AUTO_CREATE 服务自动创建，然后绑定。
		 */
		bindService(service, new MyConn(),  BIND_AUTO_CREATE);
	}

	//调用服务的方法
	public void call(View v) {
		//binder.博士招生("张三丰", 1000000);
		//通过内部代理对象，调用内部 类中的方法，实际上是调用了服务中的方法
		binder.callMethodInService("张三丰", 1000000);
	}

	//解除绑定服务
	public void unbind(View v) {
	}
}
