package com.itheima.upload;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class MainActivity extends Activity {

	EditText ed_path;
	ProgressBar pb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed_path = (EditText) findViewById(R.id.ed_path);
		pb = (ProgressBar) findViewById(R.id.pb);
	}

	//����ļ����ϴ�
	public void upload(View v){
		String path = ed_path.getText().toString().trim();
		
		if(TextUtils.isEmpty(path)){
			Toast.makeText(this, "·������Ϊ��", 0).show();
			return;
		}
		
		//http://188.188.4.100:8080/day11_upload/upload
		//Ҫ�� ·����Ӧ���ļ��ϴ����������� 
		//�п�Դ�Ŀ�� ...  �� github��ȥ�� 
		
		File file = new File(path);
		
		if(!file.exists()||file.length()<=0){
			
			//����ļ�������, ���� �ļ� ����,���� �ļ� �ǿ��ļ�, ��ô �� ���ϴ�, ��ôҪ���û�һ���Ѻõ���ʾ 
			Toast.makeText(this, "�ļ�������....", 0).show();
			return;
		}
		
		RequestParams params = new RequestParams();
		// Content-Type: multipart/form-data
		
		params.addBodyParameter("file", file);
		HttpUtils utils = new HttpUtils();
		
		String url = "http://188.188.4.100:8080/day11_upload/upload";
		//���� post ������
		// �ϴ��ĵ�ַ
		// �ϴ�������
		// �ص� 
		utils.send(HttpRequest.HttpMethod.POST, url,params, new RequestCallBack<String>() {

			//�ϴ��ɹ� 
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				System.out.println("�ϴ��ɹ� ...");
			}

			//�ϴ�ʧ�� 
			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("�ϴ�ʧ�� ...");
				
			}
			
			@Override
			public void onStart() {
				System.out.println("��ʼ�ϴ� ...");
				super.onStart();
			}
			
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				System.out.println("���ϴ�������... ...");
				pb.setMax((int) total);
				pb.setProgress((int) current);
				super.onLoading(total, current, isUploading);
			}
			
		});
		
		
	}
	
}
