package com.itheima.logcat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.v(TAG, "verbose ...");
		Log.i(TAG, "INFO ...");
		Log.d(TAG, "debug ...");
		Log.w(TAG, "warning ");
		Log.e(TAG, "error ...");
		Log.wtf(TAG, "wtf  what the fuck  ...");
		
	}


}
