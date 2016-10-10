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

	//完成文件的上传
	public void upload(View v){
		String path = ed_path.getText().toString().trim();
		
		if(TextUtils.isEmpty(path)){
			Toast.makeText(this, "路径不能为空", 0).show();
			return;
		}
		
		//http://188.188.4.100:8080/day11_upload/upload
		//要将 路径对应的文件上传到服务器了 
		//有开源的框架 ...  到 github上去找 
		
		File file = new File(path);
		
		if(!file.exists()||file.length()<=0){
			
			//如果文件不存在, 或者 文件 存在,但是 文件 是空文件, 那么 都 不上传, 那么要给用户一个友好的提示 
			Toast.makeText(this, "文件不存在....", 0).show();
			return;
		}
		
		RequestParams params = new RequestParams();
		// Content-Type: multipart/form-data
		
		params.addBodyParameter("file", file);
		HttpUtils utils = new HttpUtils();
		
		String url = "http://188.188.4.100:8080/day11_upload/upload";
		//发送 post 的请求
		// 上传的地址
		// 上传的数据
		// 回调 
		utils.send(HttpRequest.HttpMethod.POST, url,params, new RequestCallBack<String>() {

			//上传成功 
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				System.out.println("上传成功 ...");
			}

			//上传失败 
			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("上传失败 ...");
				
			}
			
			@Override
			public void onStart() {
				System.out.println("开始上传 ...");
				super.onStart();
			}
			
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				System.out.println("在上传过程中... ...");
				pb.setMax((int) total);
				pb.setProgress((int) current);
				super.onLoading(total, current, isUploading);
			}
			
		});
		
		
	}
	
}
