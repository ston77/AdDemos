package com.itheima.surfacevideo;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity {
	
	MediaPlayer mediaPlayer;
	SharedPreferences sp ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sp = getSharedPreferences("config", 0);
		SurfaceView sv = (SurfaceView) findViewById(R.id.sv);
		
		sv.getHolder().addCallback(new Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if(mediaPlayer != null){
					//��ȡ����ǰ�Ĳ���λ��
					int position = mediaPlayer.getCurrentPosition();
					Editor editor = sp.edit();
					editor.putInt("position", position);
					editor.commit();
					mediaPlayer.release();
					mediaPlayer = null;
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				//Ӧ��������ط�ȥ������Ƶ�ˡ�
				try {
					int position = sp.getInt("position", 0);
					mediaPlayer = new MediaPlayer();
					mediaPlayer.setDataSource("/mnt/sdcard/oppo.3gp");
					mediaPlayer.prepare();
					//ָ�����ŵĻ�����ʾ��holder����ȥ��ʵ���Ͼ�����ʾ��surfaceView����ȥ
					mediaPlayer.setDisplay(holder);
					mediaPlayer.seekTo(position);
					mediaPlayer.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				
			}
		});
	}

}
