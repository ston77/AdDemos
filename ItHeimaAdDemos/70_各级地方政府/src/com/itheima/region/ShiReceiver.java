package com.itheima.region; 

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShiReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//��ȡ�㲥����ĳ�ʼ����
		System.out.println("�����м����ţ����յ���ָ���ǣ�"+getResultData());
		
		setResultData("��ϯ������ÿ�˽���5������");
	}

}
