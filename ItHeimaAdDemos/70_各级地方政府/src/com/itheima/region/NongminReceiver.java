package com.itheima.region; 

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NongminReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//��ȡ�㲥����ĳ�ʼ����
		System.out.println("����ũ���ţ����յ���ָ���ǣ�"+getResultData());
		System.out.println("������л�ҵ�̫��~~...");
	}

}
