package com.itheima.region;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShengReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//获取广播传输的初始数据
		System.out.println("我是省级部门，我收到的指令是："+getResultData());
		
		//终止广播的传输
		abortBroadcast();
		//setResultData("主席讲话：每人奖励7斤土豆");
	}

}
