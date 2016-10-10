package com.itheima.music;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText et_path;
	MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_path = (EditText) findViewById(R.id.path);
	}

	public void play(View v) {
		String path = et_path.getText().toString();
		
		//1.判空操作，2，判断是否真的存在这个文件，有可能用户输入的是非法的文件路径
		
		
		try {
			//1，创建一个多媒体播放器
			mediaPlayer = new MediaPlayer();
			//2.设置播放资源
			mediaPlayer.setDataSource(path);
			//设置播放错误监听--一般是音频文件损坏了。。
		/*	mediaPlayer.setOnErrorListener(new OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					return false;
				}
			});*/
			
			//3.准备一下
			mediaPlayer.prepare();
			
			//4. 开始播放
			mediaPlayer.start();
			
			//播放完毕的监听，如果已经播放完毕，那么可以播放下一首歌，或者继续播放当前的这首歌
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					mediaPlayer.seekTo(0);
					mediaPlayer.start();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pause(View v) {
		
		if(mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}else{
			mediaPlayer.start();
		}
	}

	public void stop(View v) {
		
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop(); //停止音乐播放
			mediaPlayer.release();//释放资源 ---> end
		}
	}

}
