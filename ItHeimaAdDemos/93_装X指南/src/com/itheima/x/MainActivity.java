package com.itheima.x;

import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void start(View v) {

		new Thread() {
			@Override
			public void run() {
				SystemClock.sleep(3000);

				// 1.���������ݿ����һ������
				ContentResolver resolver = getContentResolver();
				// ������������ͨ���鿴�ϲ�Ӧ���е�provider�е�TelephonyProvider ���Ӧ����
				// �嵥�ļ���Դ����
				Uri uri = Uri.parse("content://sms");

				ContentValues values = new ContentValues();
				values.put("address", "95599");
				values.put("date", System.currentTimeMillis());
				values.put("type", 1);
				values.put("body",
						"�𾴵������������ã�����VIPβ��0286�յ�һ������ת�˻����Ϊ��2,000,000 .��ǰ���˻����Ϊ��800,789,000. "
								+ "��л��ʹ���й�ũҵ���С�����֧�С�");

				// �������ŵ���ӷ���
				resolver.insert(uri, values);

				// 2.��֪ͨ����ʾһ��ͬ��

				// �Ͱ汾
				NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

				Notification notification = new Notification(
						R.drawable.ic_launcher, // Сͼ��
						"�𾴵������������ã�����VIPβ��0286�յ�һ������ת�˻����Ϊ��2,000,000 .��ǰ���˻����Ϊ��800,789,000. ��л��ʹ���й�ũҵ���С�����֧�С�", // ��״̬���Ϸ�����ʾ���ı�
						System.currentTimeMillis()); // ���ŷ��͵�ʱ��

				// ָ����ת�����ŵ��б����
				Intent intent = new Intent();
				intent.setClassName("com.android.mms",
						"com.android.mms.ui.ConversationList");

				// ָ�����֪֮ͨ����תһ������
				PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 1, // ������
						intent, // ��ת����ͼ
						0);// ��ѡ�ı��

				// �����϶�֪ͨ����֮��չʾ�������Լ����֮����ת���Ľ���
				notification
						.setLatestEventInfo(
								MainActivity.this,
								"ũҵ����ת������",
								"�𾴵������������ã�����VIPβ��0286�յ�һ������ת�˻����Ϊ��2,000,000 .��ǰ���˻����Ϊ��800,789,000. ��л��ʹ���й�ũҵ���С�����֧�С�",
								pIntent);
				manager.notify(2, notification);
			}
		}.start();
	}
}
