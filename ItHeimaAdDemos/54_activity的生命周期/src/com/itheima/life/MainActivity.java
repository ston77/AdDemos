package com.itheima.life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
	

	//�����汻������ʱ����á�
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		System.out.println("onCreate---");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("onRestart----");
	}
	
	//������ɼ���ʱ�򣬵��ø÷���
	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("onStart----");
	}
	
	// ������������ǣ���������Ի�ȡ�����㣬�������û����н����¼�
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("onResume----");
	}
	
	
	//��ͣ  ����ʧȥ�˽���
	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("onPause----");
	}
	
	
	
	//�����治�ٿɼ���ʱ�����
	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("onStop----");
	}
	
	
	
	
	//�����汻���ٵ�ʱ�����
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy---");
	}

	

}
