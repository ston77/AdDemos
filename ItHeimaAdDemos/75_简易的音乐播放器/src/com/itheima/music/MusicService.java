package com.itheima.music;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
	
	MediaPlayer mediaPlayer; //定义一个多媒体播放器
	
	public  static String PATH = "";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		try {
			//1.创建一个多媒体播放器
			mediaPlayer = new MediaPlayer();
			
			//2. 设置播放的资源路径
			mediaPlayer.setDataSource(PATH);
			
			//3. 缓冲一下
			mediaPlayer.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int code = intent.getIntExtra("code", 1); 
		switch (code) {
		case 1: //开始播放
			//如果当前不是在播放，那么久让其播放
			if(!mediaPlayer.isPlaying()){
				mediaPlayer.start();//播放音乐
			}
			
			
			break;
		case 2://暂停动作
			
			if(mediaPlayer.isPlaying()){
				mediaPlayer.pause(); //如果当前正在播放，那么现在就应该暂停了
			}
			break;
		case 3: //停止播放
			
			if(mediaPlayer.isPlaying()){
				mediaPlayer.stop();
				mediaPlayer.reset();//重置资源
			}
			break;
		}
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		if(mediaPlayer !=null){
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

}
