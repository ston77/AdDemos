package com.itheima.bootcomplete;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;

//1.����������
public class BootCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("�������������...");
		
		//һ��������ɣ���ֱ����ת��������
		Intent in = new Intent(context,MainActivity.class);
		//����ʹ�õ��ǹ㲥�����ߵ�������ȥ�������棬�����������û��ջ�ĸ��������������ʱ��Ҫָ��
//		���������activity�����һ���¿���������ջ��
		in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(in);
	}

}
