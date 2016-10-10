package com.itheima.life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
	

	//当界面被创建的时候调用。
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		System.out.println("onCreate---");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("onRestart----");
	}
	
	//当界面可见的时候，调用该方法
	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("onStart----");
	}
	
	// 继续：代表的是，当界面可以获取到焦点，可以与用户进行交互事件
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("onResume----");
	}
	
	
	//暂停  界面失去了焦点
	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("onPause----");
	}
	
	
	
	//当界面不再可见的时候调用
	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("onStop----");
	}
	
	
	
	
	//当界面被销毁的时候调用
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy---");
	}

	

}
