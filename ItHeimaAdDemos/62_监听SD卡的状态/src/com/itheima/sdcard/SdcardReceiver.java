package com.itheima.sdcard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 1.购买收音机
 */
public class SdcardReceiver extends BroadcastReceiver {

	//当该广播接收者收听到了广播之后，就会调用这个方法。
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "存储卡已被拔出.微信头像、图片、视频、声音等功能将暂时不可用.", 0).show();
	}

}
