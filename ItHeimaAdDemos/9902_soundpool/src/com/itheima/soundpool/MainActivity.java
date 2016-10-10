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
				3,  //����ܷŶ��ٵ���Ƶ�ļ�
				 AudioManager.STREAM_MUSIC,  // �洢����������ʲô����
				0); //û������Ӱ�죬��0
		
		//������Ƶ�ļ����������档
		id = pool.load(this, R.raw.shoot, 1);
	}
	
	
	public void shoot(View v){
		pool.play(id, 1, 1, 0, 0, 1.0f);
	}


}
