package com.itheima.fish;

import com.itheima.heimapay.IService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	IService binder; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	class MyConn implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//ǿ��ת���õ�����֧�������е��ڲ��������
			binder = IService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}

	public void bind(View v) {
		Intent service = new Intent();
		service.setAction("com.itheima.heimapay.SAFEPAY");
		bindService(service, new MyConn(), BIND_AUTO_CREATE);
	}

	public void buy(View v) {
		try {
			int code = binder.callSafePay("zhangsan", "10088766", 4000);
			String str = null;
			switch (code) {
			case 200: //֧���ɹ�
				str="��������������100���ڵ��Ѿ���ֵ�ɹ�.";
				break;
			case 404:
				str="�˺Ż����������.";
				break;
			case 250:
				str="��ǰ�˻������㡣";
				break;
			}
			Toast.makeText(this, str, 0).show();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
