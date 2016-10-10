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
	 * ���������״̬����������������ֹͣ�����յ���Ϣ��
	 */
	class MyConn implements ServiceConnection{
		/**
		 * ��������ܹ��ɹ����ϣ���ô�������������ã������Ĳ���service���Ƿ��񷵻ص��ڲ����� MyBinder
		 */
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//��ȡ�������ڲ����صĴ������ ����binder�н�����
			binder = (MyBinder) service;
			
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {}
	}

	//�󶨷���
	public void bind(View v) {
		Intent service = new Intent(this , ServcieDemo.class);
		/**
		 * ��һ��������intent����
		 * �ڶ���������servcieConnection  ���ڼ�������ǰ��״̬
		 * ������������BIND_AUTO_CREATE �����Զ�������Ȼ��󶨡�
		 */
		bindService(service, new MyConn(),  BIND_AUTO_CREATE);
	}

	//���÷���ķ���
	public void call(View v) {
		//binder.��ʿ����("������", 1000000);
		//ͨ���ڲ�������󣬵����ڲ� ���еķ�����ʵ�����ǵ����˷����еķ���
		binder.callMethodInService("������", 1000000);
	}

	//����󶨷���
	public void unbind(View v) {
	}
}
