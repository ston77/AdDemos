package com.itheima.ccav;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	//�����ť֮�󣬷�������һ���㲥
	public void send(View v){
		//��������---startActivity
		//���͹㲥--- sendbroadcast
		
		//����һ��intent��ͼ����
		Intent intent  = new Intent();
		
		//ָ����ͼ�Ķ�����ʵ���Ͼ���ָ�������㲥������Ƶ��
		intent.setAction("com.itheima.ccav.action.TV");
		
		//ָ���㲥��������
		intent.putExtra("key", "��������3��룬ϰ��ƽ��ϯ����������°�����ͳ,....");
		
		//����һ���㲥 ������㲥
		sendBroadcast(intent);
	}


}
