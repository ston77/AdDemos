package com.itheima.region;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShengReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//��ȡ�㲥����ĳ�ʼ����
		System.out.println("����ʡ�����ţ����յ���ָ���ǣ�"+getResultData());
		
		//��ֹ�㲥�Ĵ���
		abortBroadcast();
		//setResultData("��ϯ������ÿ�˽���7������");
	}

}
