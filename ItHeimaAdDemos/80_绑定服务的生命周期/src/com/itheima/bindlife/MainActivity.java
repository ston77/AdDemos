package com.itheima.bindlife;

import com.itheima.bindlife.ServiceDemo.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {
	MyBinder binder;
	MyConn conn ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	class MyConn implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = (MyBinder) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}

	public void bind(View v) {
		Intent service =new Intent(this , ServiceDemo.class);
		conn = new MyConn() ; 
		bindService(service, conn, BIND_AUTO_CREATE);
	}

	public void call(View v) {
		binder.callMethodInService();
	}

	public void unbind(View v) {
		//����󶨷���Ҫ��ʹ��֮ǰ�󶨷����conn����
		unbindService(conn);
	}
}
