package com.itheima.gov;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("���Ƕ�����ϯ���۲˴󳼣����յ���ָ���ǣ�"+getResultData());
	}

}
