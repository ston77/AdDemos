package com.itheima.gov;

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

	//��������㲥
	public void send(View v){
		//���͹㲥--sendBroadcast
		//��������㲥---send����broadcast
		
		Intent intent= new Intent();
		intent.setAction("com.itheima.gov.PATATO");
		
		/*sendBroadcast(intent);*/
		
		sendOrderedBroadcast(
				intent,  //��ͼ��������ָ��action����
				null, //receiverPermission ���������㲥��߱�ʲôȨ�ޣ� null
				new MyReceiver(), //resultReceiver ���յĽ�������� �㲥����һ���ᴫ������㲥������
				null, //scheduler :handler���󣬴���㲥�ķַ�
				0,  //scheduler �� ��ʼ����
				"��ϯ������ÿ�˽���10������", //initialData : ��ʼ����
				null);// initialExtras: ��������ݣ�������ô�һ���ַ����ĳ�ʼ���ݻ�����������ͨ��bundle��ָ����������
		
	}
}
