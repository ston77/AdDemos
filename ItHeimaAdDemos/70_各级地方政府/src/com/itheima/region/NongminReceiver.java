package com.itheima.region; 

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NongminReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//获取广播传输的初始数据
		System.out.println("我是农民部门，我收到的指令是："+getResultData());
		System.out.println("啊，感谢我的太阳~~...");
	}

}
