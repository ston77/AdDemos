package com.itheima.ccav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//获取广播传递过来的数据
		String value = intent.getStringExtra("key");
		System.out.println("收到自定义的广播，数据是："+value);
	}

}
