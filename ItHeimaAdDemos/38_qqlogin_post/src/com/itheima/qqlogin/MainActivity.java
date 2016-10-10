package com.itheima.qqlogin;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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

	protected static final int SUCCESS = 0;
	protected static final int ERROR = 1;
	EditText ed_number;
	EditText ed_password;
	TextView tv_result;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //初始化 控件 
        ed_number = (EditText) findViewById(R.id.ed_number);
        ed_password = (EditText) findViewById(R.id.ed_password);
        tv_result = (TextView) findViewById(R.id.tv_result);
    }

    
    String path;
    String number;
    String password;
    
    Handler handler = new Handler(){
    	
    	public void handleMessage(android.os.Message msg) {
    		
    		switch (msg.what) {
				case SUCCESS:
					String value = (String) msg.obj;
					tv_result.setText(value);
					break;
				case ERROR:
					
					//弹 土司 
					Toast.makeText(MainActivity.this, "对不起, 俺出错了. ", 0).show();
					break;
	
				default:
					break;
			}
    	};
    };
    
    //登录的 方法 
    public void login(View v){
    	
		path = getResources().getString(R.string.ip);
    	
		number = ed_number.getText().toString().trim();
		password = ed_password.getText().toString().trim();
		
//		判断 是否为空 
		if(TextUtils.isEmpty(number)||TextUtils.isEmpty(password)){
			Toast.makeText(this, "号码或密码不能为空", 0).show();
			return;
		}
		
		//发请求 
		new Thread(){
			public void run() {
				
				//  http://188.188.4.100:8080/day06_android/login?number=110&password=123
				try {
					
					/*number= URLEncoder.encode(number, "UTF-8");
					path = path+"?number="+number+"&password="+password;*/
					//http://188.188.4.100:8080/day06_android/login
					URL url = new URL(path);
					
					
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					conn.setConnectTimeout(5000);
					
					//使用 post 方式 
					conn.setRequestMethod("POST");
					
					//number=%E5%B0%8F%E9%8A%AE&password=sdfds 
					//post请求时, 中文数据也需要进行 URL 编码
					
					//这里需要 将 number的中文值, 进行 url 编码
					number = URLEncoder.encode(number, "UTF-8");
					System.out.println(number);
					String params = "number="+number+"&password="+password;
					
					//设置必要的请求的头的信息
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length", params.length()+"");
					
					//将参数 params 以流的形式 写给 服务器 
					
					//加一个 标志, 表示 要向 服务器 写数据了
					conn.setDoOutput(true);
					conn.getOutputStream().write(params.getBytes());
					
					int code = conn.getResponseCode();
					
					if(code==200){
						InputStream in = conn.getInputStream();
						String value = StreamTool.decodeStream(in);
						
						//发消息
						Message msg = Message.obtain();
						msg.what = SUCCESS;
						msg.obj = value;
						handler.sendMessage(msg);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = Message.obtain();
					msg.what = ERROR;
					handler.sendMessage(msg);
				}
				
				
			};
			
		}.start();
		
		
    }

    
}
