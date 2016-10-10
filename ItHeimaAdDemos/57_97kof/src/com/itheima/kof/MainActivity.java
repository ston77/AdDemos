package com.itheima.kof;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	ImageView iv_people ,iv_boss;
	ProgressBar pb ; 
	int count = 100;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("onCreate----");
		iv_people = (ImageView) findViewById(R.id.iv_people);
		iv_boss = (ImageView) findViewById(R.id.iv_boss);
		
		pb = (ProgressBar) findViewById(R.id.pb);
		pb.setProgress(100);
	}


	
	public void qq(View v){
		iv_people.setImageResource(R.drawable.qq);
		count-=5;
		pb.setProgress(count);
		if(count <=0){
			iv_boss.setImageResource(R.drawable.die);
		}
	}
	public void zq(View v){
		iv_people.setImageResource(R.drawable.zq);
		count-=7;
		pb.setProgress(count);
		if(count <=0){
			iv_boss.setImageResource(R.drawable.die);
		}
		
	}
	public void zj(View v){
		
		iv_people.setImageResource(R.drawable.zj);
		count-=10;
		pb.setProgress(count);
		if(count <=0){
			iv_boss.setImageResource(R.drawable.die);
		}
	}
	

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("onStart----");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("onResume----");
	}
	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("onPause----");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("onRestart----");
	}
	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("onStop----");
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy----");
	}
}
