package com.itheima.viewanim;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageView iv = (ImageView)findViewById(R.id.iv);
		
		
		TranslateAnimation anima = new TranslateAnimation(
				0, 300, 
				0, -300);
		
		anima.setDuration(3000);
		anima.setRepeatMode(TranslateAnimation.REVERSE);
		anima.setRepeatCount(TranslateAnimation.INFINITE);
		iv.startAnimation(anima);
		
		
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "nihao....", 0).show();
			}
		});
	}


}
