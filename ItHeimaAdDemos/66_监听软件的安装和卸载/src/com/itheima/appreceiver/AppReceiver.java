package com.itheima.appreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if(intent.getAction().equals("android.intent.action.PACKAGE_ADDED")){ 
			//���򱻰�װ��  ��ȡ������ʲô�������װ����ж���ˣ�������getData();
			System.out.println("���������װ��..."+intent.getData());
		}else if(intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")){
			System.out.println("�������ж����..."+intent.getData());
		}
		
		
	}

}
