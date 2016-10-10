package com.itheima.gov;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("我是恩恩主席的芹菜大臣，我收到的指令是："+getResultData());
	}

}
