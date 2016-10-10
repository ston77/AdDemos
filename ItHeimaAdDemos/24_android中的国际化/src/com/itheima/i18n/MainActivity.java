package com.itheima.i18n;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv = (TextView) findViewById(R.id.tv);
		tv.setText(R.string.hello_world);
		
		ImageView iv = (ImageView) findViewById(R.id.iv);
		iv.setImageResource(R.drawable.flag);
	}

}
