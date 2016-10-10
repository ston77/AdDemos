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
			Toast.makeText(this, "·������Ϊ��", 0).show();
			return;
		}
		
		File file = new File(path);
		
		if(!file.exists()){
			Toast.makeText(this, "�Ƿ����ļ�·����", 0).show();
			return ;
		}
		
		//��������..----Serviceȥ����
		MusicService.PATH = path;//ָ��·��
		
		
		Intent service = new Intent(this ,MusicService.class);
		service.putExtra("code", 1);
		startService(service);
		
	}
	
	
	@Override
	public void onBackPressed() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("��ʾ��");
		dialog.setMessage("�Ƿ������������?");
		dialog.setPositiveButton("��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		
		dialog.setNegativeButton("��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//ֹͣ����
				stopService(new Intent(MainActivity.this,MusicService.class));
				
				//�رս���
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
