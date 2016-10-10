package com.itheima.service01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void start(View v) {
		
		Intent service = new Intent(this,ServiceDemo.class);
		//�����������񷵻صĲ��Ƕ��󣬶������������(������ ���ȫ·����ַ)
		//���Ծ�û�а취ֱ�ӵ��÷����еķ�����
		startService(service);
	}

	public void call(View v) {
		//�������Լ������ķ������û�о���ϵͳ�ӹ������������İ�����һЩ����ֵ����null,
		ServiceDemo demo = new ServiceDemo();
		demo.methodInService();
	}

	public void stop(View v) {
		Intent service = new Intent(this,ServiceDemo.class);
		stopService(service);
	}
}
