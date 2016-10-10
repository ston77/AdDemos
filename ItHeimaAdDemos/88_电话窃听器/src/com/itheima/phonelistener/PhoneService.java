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

		// ������ɹ�������ʱ�򣬾�Ҫ������ȥ��ȡϵͳ�ĵ绰״̬��
		// 1.��ȡ���绰�Ĺ�����
		TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		// 2.�����绰��״̬������绰��״̬�����˸ı䣬��ômyListener���ᱻ����
		manager.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}

	class MyListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE: // ������
				System.out.println("��ǰû�е绰���У������С���");
				stopRecording();
				break;
			case TelephonyManager.CALL_STATE_RINGING: // ������
				System.out.println("�����ˣ������С���");

				break;
			case TelephonyManager.CALL_STATE_OFFHOOK: // ͨ����
				System.out.println("�����ˣ�����ͨ����...");
				startRecording();
				break;
			}
		}
	}

	//��ʼ¼������
	private void startRecording() {
		//1.����¼����
		mRecorder = new MediaRecorder();
		//2. ����¼�Ƶ���Ƶ��Դ
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		//3.¼�Ƶ���Ƶ��ʽ
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		
		//4.¼�Ƶ��ļ�����λ��
		mRecorder.setOutputFile("/mnt/sdcard/"+System.currentTimeMillis()+".3gp");
		
		//5.������Ƶ�ı����ʽ
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			System.out.println("prepare() failed");
		}

		mRecorder.start();
	}

	//ֹͣ¼������
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
