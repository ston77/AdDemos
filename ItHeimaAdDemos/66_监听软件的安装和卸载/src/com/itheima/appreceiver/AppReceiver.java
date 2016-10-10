package com.itheima.appreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if(intent.getAction().equals("android.intent.action.PACKAGE_ADDED")){ 
			//程序被安装了  获取到底是什么软件被安装或者卸载了，。。。getData();
			System.out.println("有软件被安装了..."+intent.getData());
		}else if(intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")){
			System.out.println("有软件被卸载了..."+intent.getData());
		}
		
		
	}

}
