package com.itheima.xutils.download;

import java.io.File;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	ProgressBar pb0;
	String url = "http://188.188.4.100:8080/test.exe";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pb0= (ProgressBar) findViewById(R.id.pb0);
	}

	
	public void download(View v){
		
		HttpUtils utils = new HttpUtils();
		
		// url: ����������
		// target:���ص��ļ� ���浽���� 
		//callback : �� ��, 
		utils.download(url, "/mnt/sdcard/xxx.exe", new RequestCallBack<File>() {
			
			//���� �ɹ� 
			@Override
			public void onSuccess(ResponseInfo<File> responseInfo) {
				Toast.makeText(MainActivity.this, "���سɹ�", 0).show();
			}
			
			//���� ʧ��  
			@Override
			public void onFailure(HttpException error, String msg) {
				
			}
			
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				super.onLoading(total, current, isUploading);
				
				pb0.setMax((int) total);
				pb0.setProgress((int) current);
			}
		});
		
	}

}
