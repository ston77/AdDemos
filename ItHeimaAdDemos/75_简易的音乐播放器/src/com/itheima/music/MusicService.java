package com.itheima.music;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
	
	MediaPlayer mediaPlayer; //����һ����ý�岥����
	
	public  static String PATH = "";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		try {
			//1.����һ����ý�岥����
			mediaPlayer = new MediaPlayer();
			
			//2. ���ò��ŵ���Դ·��
			mediaPlayer.setDataSource(PATH);
			
			//3. ����һ��
			mediaPlayer.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int code = intent.getIntExtra("code", 1); 
		switch (code) {
		case 1: //��ʼ����
			//�����ǰ�����ڲ��ţ���ô�����䲥��
			if(!mediaPlayer.isPlaying()){
				mediaPlayer.start();//��������
			}
			
			
			break;
		case 2://��ͣ����
			
			if(mediaPlayer.isPlaying()){
				mediaPlayer.pause(); //�����ǰ���ڲ��ţ���ô���ھ�Ӧ����ͣ��
			}
			break;
		case 3: //ֹͣ����
			
			if(mediaPlayer.isPlaying()){
				mediaPlayer.stop();
				mediaPlayer.reset();//������Դ
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
