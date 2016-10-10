package com.itheima.ipcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * ����������е绰�Ĺ㲥������
 */
public class OutGoingCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//��ȡ������еĵ绰����  ��ȡ�㲥���������
		String number = getResultData() ;
		System.out.println("������е绰��..."+number);
		
		if(number.startsWith("0")){
			
			
			SharedPreferences sp = context.getSharedPreferences("ip", 0);
			String ipNumber = sp.getString("number", "");
			number = ipNumber+number; //ƴ��IP����ǰ׺
			
			setResultData(null);//��ֹ���С�
			
			//���õ�ǰ�µĲ��ĵ绰����  �����㲥�����µ�����
			//setResultData(number);
			//abortBroadcast();//��ֹ�㲥�Ĵ���
		}
	}

}
