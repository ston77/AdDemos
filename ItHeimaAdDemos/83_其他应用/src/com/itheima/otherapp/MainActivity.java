package com.itheima.otherapp;

import com.itheima.remote.IService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

public class MainActivity extends Activity {
	
	IService  binder ;
	MyConn conn ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	class MyConn implements ServiceConnection{

		@Override  
		public void onServiceConnected(ComponentName name, IBinder service) {
			//ת��binder�����ʱ��ʹ��IServcie��stub���ڲ�������ת��
			binder = IService.Stub.asInterface(service);
//			binder = (IService)service; //��������ôǿ������ת��
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}

	public void bind(View v) {
		//��06�еķ���
		Intent service = new Intent();
		service.setAction("com.itheima.remote.SERVICE");
		conn = new MyConn();
		bindService(service, conn, BIND_AUTO_CREATE);
	}

	public void call(View v) {
		try {
			binder.callMethodInService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void ubbind(View v) {
		unbindService(conn);
	}
}
