package com.itheima.ccav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//��ȡ�㲥���ݹ���������
		String value = intent.getStringExtra("key");
		System.out.println("�յ��Զ���Ĺ㲥�������ǣ�"+value);
	}

}
