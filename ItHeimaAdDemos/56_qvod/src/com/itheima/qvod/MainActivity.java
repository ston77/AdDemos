package com.itheima.qvod;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("oncreate---");
	}

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("onStart---正在播放视频，声音很大");
	};
	
	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("onStop---暂停视频的播放。。。");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy---");
	}

}
