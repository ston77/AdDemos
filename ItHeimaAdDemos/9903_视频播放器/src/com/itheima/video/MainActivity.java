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
		
		//1.找到播放视频的控件
		VideoView vv = (VideoView) findViewById(R.id.vv);
		
		//2.指定播放的视频源
		vv.setVideoPath("/mnt/sdcard/oppo.3gp");
		
		MediaController controller = new MediaController(this);
		controller.setAnchorView(vv);
		
		//设置视频的控制器  暂停、播放、快进、快退
		vv.setMediaController(controller);
		
		//3.开始播放
		vv.start();
	}


}
