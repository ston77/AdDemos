package com.itheima.launchmode;

import java.io.Serializable;

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
		
	/*	Intent intent = new Intent();
		//bundle实际上就是把一堆散落的零部件打包起来，然后把这个塑料袋传递给下一个界面
		Bundle value = new Bundle();
		value.putString("name", name);
		value.putInt("number",10086);
		intent.putExtra("bundle", value);
		
		
		
		Bundle data = intent.getBundleExtra("bundle");
		data.getString(key)*/
	}

	public void click01(View v){
		startActivity(new Intent(this, FirstActivity.class));
	}
	public void click02(View v){
		
		startActivity(new Intent(this, SecondActivity.class));
	}
}
