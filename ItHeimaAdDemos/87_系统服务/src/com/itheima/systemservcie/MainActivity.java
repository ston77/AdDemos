package com.itheima.systemservcie;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		System.out.println("---------");
		LayoutInflater LayoutInflater =
                (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View item = View.inflate(this, R.layout.activity_main, null);
	}


}
