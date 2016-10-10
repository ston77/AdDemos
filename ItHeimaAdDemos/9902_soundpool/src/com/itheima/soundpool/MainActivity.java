package com.itheima.soundpool;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	int id ; 
	SoundPool pool ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pool = new SoundPool(
				3,  //最多能放多少道音频文件
				 AudioManager.STREAM_MUSIC,  // 存储的流类型是什么类型
				0); //没有生命影响，给0
		
		//加载音频文件到池子里面。
		id = pool.load(this, R.raw.shoot, 1);
	}
	
	
	public void shoot(View v){
		pool.play(id, 1, 1, 0, 0, 1.0f);
	}


}
