package com.itheima.music;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText et_path;
	MediaPlayer mediaPlayer;
	ProgressDialog dialog  ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_path = (EditText) findViewById(R.id.path);
		
		dialog = new ProgressDialog(this);
		dialog.setMessage("����ƴ��������...");
	}

	public void play(View v) {
		String path = et_path.getText().toString();
		
		//1.�пղ�����2���ж��Ƿ���Ĵ�������ļ����п����û�������ǷǷ����ļ�·��
		
		
		try {
			//1������һ����ý�岥����
			mediaPlayer = new MediaPlayer();
			//2.���ò�����Դ
			mediaPlayer.setDataSource(path);
			//���ò��Ŵ������--һ������Ƶ�ļ����ˡ���
		/*	mediaPlayer.setOnErrorListener(new OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					return false;
				}
			});*/
			
			//3.׼��һ�� �����������߳�׼���ġ�
//			mediaPlayer.prepare();
			//��ʼ�첽���壬�����߳�����׼��
			dialog.show();
			mediaPlayer.prepareAsync(); 
			
			//ע��һ��������Բ��ŵļ�����
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					dialog.dismiss();
					//4. ��ʼ����
					mediaPlayer.start();
					
					//������ϵļ���������Ѿ�������ϣ���ô���Բ�����һ�׸裬���߼������ŵ�ǰ�����׸�
					mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
						
						@Override
						public void onCompletion(MediaPlayer mp) {
							mediaPlayer.seekTo(0);
							mediaPlayer.start();
						}
					});
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
			mediaPlayer.stop(); //ֹͣ���ֲ���
			mediaPlayer.release();//�ͷ���Դ ---> end
		}
	}

}
