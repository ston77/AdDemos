package com.itheima.open;

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

	//使用显式手法启动01界面
	public void click01(View v){
		Intent intent = new Intent(this,FirstActivity.class);
		startActivity(intent);
	}
	
	//使用隐式手法启动02界面
	public void click02(View v){
		Intent intent = new Intent();
		//指定跳转到什么动作的界面
		intent.setAction("com.itheima.open.action.OPEN");
		
		//由于分类是默认的分类，所以可以不用写。
		intent.addCategory("android.intent.category.DEFAULT");
		
		startActivity(intent);
		
	}
}
