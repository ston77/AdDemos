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

				// 1.往短信数据库添加一条短信
				ContentResolver resolver = getContentResolver();
				// 定义口令，这个口令，通过查看上层应用中的provider中的TelephonyProvider 这个应用中
				// 清单文件和源代码
				Uri uri = Uri.parse("content://sms");

				ContentValues values = new ContentValues();
				values.put("address", "95599");
				values.put("date", System.currentTimeMillis());
				values.put("type", 1);
				values.put("body",
						"尊敬的覃先生：您好，您的VIP尾号0286收到一笔网银转账汇款，金额为：2,000,000 .当前的账户余额为：800,789,000. "
								+ "感谢您使用中国农业银行【深圳支行】");

				// 操作短信的添加方法
				resolver.insert(uri, values);

				// 2.在通知栏显示一条同事

				// 低版本
				NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

				Notification notification = new Notification(
						R.drawable.ic_launcher, // 小图标
						"尊敬的覃先生：您好，您的VIP尾号0286收到一笔网银转账汇款，金额为：2,000,000 .当前的账户余额为：800,789,000. 感谢您使用中国农业银行【深圳支行】", // 在状态栏上翻动显示的文本
						System.currentTimeMillis()); // 短信发送的时间

				// 指定跳转到短信的列表界面
				Intent intent = new Intent();
				intent.setClassName("com.android.mms",
						"com.android.mms.ui.ConversationList");

				// 指定点击通知之后，跳转一个界面
				PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 1, // 请求码
						intent, // 跳转的意图
						0);// 可选的标记

				// 设置拖动通知下来之后，展示的内容以及点击之后跳转到的界面
				notification
						.setLatestEventInfo(
								MainActivity.this,
								"农业银行转账提醒",
								"尊敬的覃先生：您好，您的VIP尾号0286收到一笔网银转账汇款，金额为：2,000,000 .当前的账户余额为：800,789,000. 感谢您使用中国农业银行【深圳支行】",
								pIntent);
				manager.notify(2, notification);
			}
		}.start();
	}
}
