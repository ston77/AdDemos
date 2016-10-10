package com.itheima.picviewer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {

	EditText ed_path;
	ImageView iv_pic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed_path = (EditText) findViewById(R.id.ed_path);
		iv_pic = (ImageView) findViewById(R.id.iv_pic);
	}
	
	String path;
	
	//handler 翻译过来表示处理器 
	Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			
			Bitmap bitmap = (Bitmap) msg.obj;
			iv_pic.setImageBitmap(bitmap);
		};
		
	};
	
	
	//点击查看图片
	public void getPic(View v){
		
		//http://www.itheima.com/uploads/2015/08/198x57.png
		path = ed_path.getText().toString();
		
		new Thread(){
			
			public void run() {
				
				try {
					
					//构建一个 url对象的实例 
					URL url = new URL(path);
					
					//使用当前的url 与服务器建立  器连接 
					HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
					
					//设置请求的方式
					conn.setRequestMethod("GET");
					
					// 200, 302 , 304, 404, 500 
					int code = conn.getResponseCode();
					
					if(code==200){
						
						//进来则表示 , 成功的 接受到了服务器的 响应的数据,  服务器 响应成功
						
						//这个 in 就代表着 返回的 图片数据 
						InputStream in = conn.getInputStream();
						
						//如何将流的数据转换成一个 图片呢?
						
						// 这个 bitmap 就是代表着 一张图片 .
						 Bitmap bitmap = BitmapFactory.decodeStream(in);
						
						 //要 将图片 显示 到 ImageView中 
//						 iv_pic.setImageBitmap(bitmap);
						 
						 
						 //通过handler 发消息 --Message
						 
						 Message msg = new Message();
						 msg.obj = bitmap;
						 handler.sendMessage(msg);
						 
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			};
			
		}.start();
		
	}
	
}
