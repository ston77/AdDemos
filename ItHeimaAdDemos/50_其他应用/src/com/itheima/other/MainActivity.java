package com.itheima.other;

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

	//显式启动04应用的界面01
	public void click01(View v) {
		
		
		//由于是隐式手法注册的界面，所以在其他应用中是不可被打开的。
		Intent intent = new Intent();
		intent.setClassName("com.itheima.openstyle", "com.itheima.openstyle.MainActivity");
		startActivity(intent);
	}

	//隐式启动04应用的界面02
	public void click02(View v) {
		Intent intent = new Intent();
		
		//指定跳转到什么动作的界面
		intent.setAction("com.itheima.openstyle.action.OPEN");
		
		//由于分类是默认的分类，所以可以不用写。
		intent.addCategory("android.intent.category.DEFAULT");
		startActivity(intent);
	}
}
