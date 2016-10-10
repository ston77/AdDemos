package com.itheima.screenstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenStatusReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
			System.out.println("屏幕被点亮了。。。");
		}else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
			System.out.println("屏幕被锁定了。。。");
			
		}
	}

}
