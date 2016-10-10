package com.itheima.notification;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	//����֪ͨ
	public void send(View v){
		/*
		 * �߰汾
		 * //1.�õ�֪ͨ������
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		//2.����һ��notification�Ĺ�������ͨ�����������ȥ����֪ͨ��һЩ����
		 Notification noti = new Notification.Builder(this)
         .setContentTitle("���Ǳ���")
         .setContentText("�����ı�����")
         .setSmallIcon(R.drawable.ic_launcher)
         .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
         .build();
		
		//2.����һ��֪ͨ �� ָ��֪ͨ��id������֪ͨ�Ķ���
		manager.notify(1, noti);*/
		
		
		//�Ͱ汾
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		Notification notification = new Notification(
				R.drawable.ic_launcher,  //Сͼ��
				"����һ���µ�δ����Ϣ",  // ��״̬���Ϸ�����ʾ���ı�
				System.currentTimeMillis()); //���ŷ��͵�ʱ��
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel://10086"));
		
		
		//ָ�����֪֮ͨ����תһ������
		PendingIntent pIntent = PendingIntent.getActivity(
				this, 
				1, //������
				intent, //��ת����ͼ
				0);//��ѡ�ı��
		
		//�����϶�֪ͨ����֮��չʾ�������Լ����֮����ת���Ľ���
		notification.setLatestEventInfo(
				this, 
				"���Ǳ���", 
				"�����ı�", 
				pIntent);
		manager.notify(2, notification);
		
		
	}
}
