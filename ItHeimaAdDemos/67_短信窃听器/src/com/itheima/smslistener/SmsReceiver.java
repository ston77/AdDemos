package com.itheima.smslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.test.suitebuilder.annotation.SmallTest;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("����һ��������...");
		
		//�������� ---pdu��ʽ
		
		
		
		 Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
		 
		 for (int i = 0; i < messages.length; i++) {
			byte [] pdu =(byte[]) messages[i];
			SmsMessage sms = SmsMessage.createFromPdu(pdu);
			//��ȡ���ŵ�����
			String body = sms.getMessageBody();
			
			String address = sms.getOriginatingAddress();
			System.out.println("�յ�һ��22222222���ԣ�"+address+"�Ķ��ţ�����22222222�������ǣ�"+body);
		}
		
		
		
		abortBroadcast();
		
		
		
		
		
		
		
		
		
		
		
		
		
	/*	SmsMessage [] messages =getMessagesFromIntent(intent); 
		for (SmsMessage sms : messages) {
			//��ȡ���ŵ�����
			String body = sms.getMessageBody();
			
			String address = sms.getOriginatingAddress();
			System.out.println("�յ�һ�����ԣ�"+address+"�Ķ��ţ����ŵ������ǣ�"+body);
		}*/
		
		
	}
	
	
	public static SmsMessage[] getMessagesFromIntent(
            Intent intent) {
        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
        String format = intent.getStringExtra("format");
        byte[][] pduObjs = new byte[messages.length][];

        for (int i = 0; i < messages.length; i++) {
            pduObjs[i] = (byte[]) messages[i];
        }
        byte[][] pdus = new byte[pduObjs.length][];
        int pduCount = pdus.length;
        SmsMessage[] msgs = new SmsMessage[pduCount];
        for (int i = 0; i < pduCount; i++) {
            pdus[i] = pduObjs[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
        }
        return msgs;
    }

}
