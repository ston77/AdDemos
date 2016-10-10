package com.itheima.gov;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	//发布有序广播
	public void send(View v){
		//发送广播--sendBroadcast
		//发送有序广播---send有序broadcast
		
		Intent intent= new Intent();
		intent.setAction("com.itheima.gov.PATATO");
		
		/*sendBroadcast(intent);*/
		
		sendOrderedBroadcast(
				intent,  //意图对象，用于指定action动作
				null, //receiverPermission 接收这条广播需具备什么权限， null
				new MyReceiver(), //resultReceiver 最终的结果接受者 广播最终一定会传给这个广播接受者
				null, //scheduler :handler对象，处理广播的分发
				0,  //scheduler ： 初始代码
				"主席讲话：每人奖励10斤土豆", //initialData : 初始数据
				null);// initialExtras: 额外的数据，如果觉得传一个字符串的初始数据还不够，可以通过bundle来指定其他数据
		
	}
}
