package com.itheima.ipcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * 监听向外呼叫电话的广播接收者
 */
public class OutGoingCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//获取向外呼叫的电话号码  获取广播里面的数据
		String number = getResultData() ;
		System.out.println("向外呼叫电话了..."+number);
		
		if(number.startsWith("0")){
			
			
			SharedPreferences sp = context.getSharedPreferences("ip", 0);
			String ipNumber = sp.getString("number", "");
			number = ipNumber+number; //拼接IP号码前缀
			
			setResultData(null);//禁止呼叫。
			
			//设置当前新的拨的电话号码  ，给广播设置新的数据
			//setResultData(number);
			//abortBroadcast();//终止广播的传输
		}
	}

}
