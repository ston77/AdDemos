package com.itheima.phonelistener;

import java.io.File;
import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneService extends Service {
	MediaRecorder mRecorder;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// 当服务成功创建的时候，就要求马上去获取系统的电话状态：
		// 1.获取到电话的管理者
		TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		// 2.监听电话的状态，如果电话的状态发生了改变，那么myListener将会被调用
		manager.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}

	class MyListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE: // 空闲中
				System.out.println("当前没有电话呼叫，空闲中。。");
				stopRecording();
				break;
			case TelephonyManager.CALL_STATE_RINGING: // 响铃中
				System.out.println("来电了，响铃中。。");

				break;
			case TelephonyManager.CALL_STATE_OFFHOOK: // 通话中
				System.out.println("来电了，正在通话中...");
				startRecording();
				break;
			}
		}
	}

	//开始录制声音
	private void startRecording() {
		//1.创建录音机
		mRecorder = new MediaRecorder();
		//2. 设置录制的音频来源
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		//3.录制的音频格式
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		
		//4.录制的文件保存位置
		mRecorder.setOutputFile("/mnt/sdcard/"+System.currentTimeMillis()+".3gp");
		
		//5.设置音频的编码格式
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			System.out.println("prepare() failed");
		}

		mRecorder.start();
	}

	//停止录制声音
	private void stopRecording() {
		if(mRecorder == null){
			return;
		}
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
