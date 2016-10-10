package com.itheima.openbr;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.tv).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "打开浏览器", 0).show();
				//真的打开浏览器的界面
				
				/* <intent-filter>
	                <action android:name="android.intent.action.VIEW" />
	                <category android:name="android.intent.category.DEFAULT" />
	                <category android:name="android.intent.category.BROWSABLE" />
	                <data android:scheme="http" />
	                <data android:scheme="https" />
	                <data android:scheme="about" />
	                <data android:scheme="javascript" />
	            </intent-filter>*/
				Intent intent =new Intent();
				intent.setAction("android.intent.action.VIEW");//指定动作
				intent.addCategory("android.intent.category.DEFAULT");
				intent.addCategory("android.intent.category.BROWSABLE");
				intent.setData(Uri.parse("http://www.itcast.cn"));
				
				startActivity(intent);
				
			}
		});
	}

}
