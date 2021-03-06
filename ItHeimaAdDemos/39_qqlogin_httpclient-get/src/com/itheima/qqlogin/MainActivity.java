package com.itheima.qqlogin;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
					
					number =URLEncoder.encode(number, "UTF-8");
					path = path+"?number="+number+"&password="+password;
					
					//相当于打开了一个 浏览器客户端
					HttpClient client = new DefaultHttpClient();
					
					//get方式的请求
					HttpGet get = new HttpGet(path);
					
					//获得的响应 对象 
					HttpResponse response = client.execute(get);
					
					//获得状态行对象,然后再 获得状态行中的状态码
					int code = response.getStatusLine().getStatusCode();
					
					if(code==200){
						
						//获得响应体,  获得响应体中的 流的数据 
						//接下来的代码跟之前一样的
						InputStream in = response.getEntity().getContent();
						
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
