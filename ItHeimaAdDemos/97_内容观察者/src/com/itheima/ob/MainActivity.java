package com.itheima.ob;

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
		
		Uri uri = Uri.parse("content://com.itheima.db.BANK/account");
		
		//注册一个内容观察者，让他观察指定的uri， 如果这个uri的数据发生了改变，那么将会得到通知
		getContentResolver().registerContentObserver(
				uri, //观察指定的uri
				true,  // 如果是true, 代表只要前面的uri能够匹配成功，那么就会收到通知，如果是false代表的时候
						//要所有的路径都完全匹配
				new ContentObserver(new Handler()) {
			
			//一旦这个uri对应的数据发生了改变，那么这个方法将会被调用.
			@Override
			public void onChange(boolean selfChange) {
				System.out.println("来人啊，银行行长又来偷钱了。。。");
			}
		});
	}


}
