package com.itheima.htmlviewer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.itheima.utils.StreamTool;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int SUCCESS = 0;   //成功 

	protected static final int ERROR = 1;  //出现 错误 

	TextView tv_html;
	
	EditText ed_path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv_html = (TextView) findViewById(R.id.tv_html);
		ed_path = (EditText) findViewById(R.id.ed_path);
	}

	Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case SUCCESS:
				
				String value = (String) msg.obj;
				tv_html.setText(value);
				break;
			case ERROR:  //出现错误 
				Toast.makeText(MainActivity.this, "对不起, 出错了 .", 0).show();
				System.out.println("对不起, 出错了");
				break;

			default:
				break;
			}
			
		};
	};
	
	
	String path;
	//点击查看 html 页面的 源代码内容 
	public void getHtmlContent(View v){
		path = ed_path.getText().toString().trim();
		
		if(TextUtils.isEmpty(path)){
			Toast.makeText(this, "路径不能为空 ....", 0).show();
			return;
		}
		
		//联网 
		new Thread(){
			
			public void run() {
				
				try {
					URL url = new URL(path);
					
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					//get方式请求
					conn.setRequestMethod("GET");
					
					//超时 时间
					conn.setConnectTimeout(5000);
					
					int code = conn.getResponseCode();
					
					String type = conn.getContentType();
					System.out.println("type :" + type);
					
					if(code==200){
						
						InputStream in = conn.getInputStream();
						
						//将 流的数据 转换成 字符数据  
						//自己 动手 弄 
						String value = StreamTool.decodeStream(in);
						
						//使用handler   发消息 
						
						Message msg = Message.obtain();
						msg.what =SUCCESS;
						msg.obj = value;
						handler.sendMessage(msg);
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = Message.obtain();
					msg.what =ERROR;
					handler.sendMessage(msg);
				}
				
			};
			
		}.start();
		
		
	}
	
}
