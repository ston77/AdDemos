package com.itheima.sdcard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 1.����������
 */
public class SdcardReceiver extends BroadcastReceiver {

	//���ù㲥�������������˹㲥֮�󣬾ͻ�������������
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "�洢���ѱ��γ�.΢��ͷ��ͼƬ����Ƶ�������ȹ��ܽ���ʱ������.", 0).show();
	}

}
