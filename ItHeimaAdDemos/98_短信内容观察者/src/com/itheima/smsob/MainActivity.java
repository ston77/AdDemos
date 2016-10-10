package com.itheima.smsob;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.database.ContentObserver;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//定义uri路径
		Uri uri = Uri.parse("content://sms");
		//注册一个内容观察者，观察短信数据库是否发生了改变
		getContentResolver().registerContentObserver(uri, true, new MyObserver(new Handler()));
	}

	
	class MyObserver extends ContentObserver{

		public MyObserver(Handler handler) {
			super(handler);
		}
		
		
		@Override
		public void onChange(boolean selfChange, Uri uri) {
			super.onChange(selfChange, uri);
			//uri：就是最新操作的记录对应的uri，根据这个uri可以去查询到指定的记录数据
			//使用内容观察者的手法，去做优化短信窃听器
			System.out.println("来了一条短信："+uri);
		}
		
	}

}
