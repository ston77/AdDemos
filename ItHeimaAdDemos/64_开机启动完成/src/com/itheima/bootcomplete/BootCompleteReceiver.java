package com.itheima.bootcomplete;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;

//1.购买收音机
public class BootCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("开机启动完成了...");
		
		//一旦开机完成，将直接跳转到主界面
		Intent in = new Intent(context,MainActivity.class);
		//由于使用的是广播接收者的上下文去启动界面，这个上下文是没有栈的概念，所以在启动的时候要指定
//		启动的这个activity存放在一个新开启的任务栈中
		in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(in);
	}

}
