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


	//发送通知
	public void send(View v){
		/*
		 * 高版本
		 * //1.得到通知管理者
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		//2.定义一个notification的构建器，通过这个构建器去定义通知的一些属性
		 Notification noti = new Notification.Builder(this)
         .setContentTitle("这是标题")
         .setContentText("这是文本内容")
         .setSmallIcon(R.drawable.ic_launcher)
         .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
         .build();
		
		//2.发送一个通知 ， 指定通知的id，还有通知的对象
		manager.notify(1, noti);*/
		
		
		//低版本
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		Notification notification = new Notification(
				R.drawable.ic_launcher,  //小图标
				"您有一条新的未读消息",  // 在状态栏上翻动显示的文本
				System.currentTimeMillis()); //短信发送的时间
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel://10086"));
		
		
		//指定点击通知之后，跳转一个界面
		PendingIntent pIntent = PendingIntent.getActivity(
				this, 
				1, //请求码
				intent, //跳转的意图
				0);//可选的标记
		
		//设置拖动通知下来之后，展示的内容以及点击之后跳转到的界面
		notification.setLatestEventInfo(
				this, 
				"这是标题", 
				"这是文本", 
				pIntent);
		manager.notify(2, notification);
		
		
	}
}
