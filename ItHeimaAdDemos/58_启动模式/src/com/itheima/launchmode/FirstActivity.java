package com.itheima.launchmode;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class FirstActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
	}

	public void click01(View v){
		startActivity(new Intent(this, FirstActivity.class));
	}
	public void click02(View v){
		
		startActivity(new Intent(this, SecondActivity.class));
	}
}
