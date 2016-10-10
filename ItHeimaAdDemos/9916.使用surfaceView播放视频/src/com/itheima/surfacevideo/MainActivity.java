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
					//获取到当前的播放位置
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
				//应该在这个地方去播放视频了。
				try {
					int position = sp.getInt("position", 0);
					mediaPlayer = new MediaPlayer();
					mediaPlayer.setDataSource("/mnt/sdcard/oppo.3gp");
					mediaPlayer.prepare();
					//指定播放的画面显示到holder里面去，实际上就是显示到surfaceView里面去
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
