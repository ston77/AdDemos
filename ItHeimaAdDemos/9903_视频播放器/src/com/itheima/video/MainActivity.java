package com.itheima.video;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//1.�ҵ�������Ƶ�Ŀؼ�
		VideoView vv = (VideoView) findViewById(R.id.vv);
		
		//2.ָ�����ŵ���ƵԴ
		vv.setVideoPath("/mnt/sdcard/oppo.3gp");
		
		MediaController controller = new MediaController(this);
		controller.setAnchorView(vv);
		
		//������Ƶ�Ŀ�����  ��ͣ�����š����������
		vv.setMediaController(controller);
		
		//3.��ʼ����
		vv.start();
	}


}
