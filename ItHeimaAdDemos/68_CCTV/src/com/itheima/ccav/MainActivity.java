package com.itheima.ccav;

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

	
	//点击按钮之后，发布出来一条广播
	public void send(View v){
		//启动界面---startActivity
		//发送广播--- sendbroadcast
		
		//定义一个intent意图对象
		Intent intent  = new Intent();
		
		//指定意图的动作：实际上就是指定这条广播所处的频道
		intent.setAction("com.itheima.ccav.action.TV");
		
		//指定广播带上数据
		intent.putExtra("key", "昨天下午3点半，习近平主席会见了美国奥巴马总统,....");
		
		//发布一条广播 ，无序广播
		sendBroadcast(intent);
	}


}
