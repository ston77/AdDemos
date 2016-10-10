package com.itheima.music;

import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText et_path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_path = (EditText) findViewById(R.id.path);
	}

	public void play(View v) {
		
		String path = et_path.getText().toString();
		
		if(TextUtils.isEmpty(path)){
			Toast.makeText(this, "路径不能为空", 0).show();
			return;
		}
		
		File file = new File(path);
		
		if(!file.exists()){
			Toast.makeText(this, "非法的文件路径。", 0).show();
			return ;
		}
		
		//播放音乐..----Service去播放
		MusicService.PATH = path;//指定路径
		
		
		Intent service = new Intent(this ,MusicService.class);
		service.putExtra("code", 1);
		startService(service);
		
	}
	
	
	@Override
	public void onBackPressed() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("提示：");
		dialog.setMessage("是否继续播放音乐?");
		dialog.setPositiveButton("是", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		
		dialog.setNegativeButton("否", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//停止服务，
				stopService(new Intent(MainActivity.this,MusicService.class));
				
				//关闭界面
				finish();
			}
		});
		
		dialog.show();
		
		
	}
	
	public void pause(View v) {
		Intent service = new Intent(this ,MusicService.class);
		service.putExtra("code", 2);
		startService(service);
	}

	public void stop(View v) {
		Intent service = new Intent(this ,MusicService.class);
		service.putExtra("code", 3);
		startService(service);
	}

}
