package com.itheima.multiui;

import android.app.Activity;
import android.os.Bundle;

public class SecondActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//指定显示什么布局，什么UI
		setContentView(R.layout.activity_second);
	}

}

