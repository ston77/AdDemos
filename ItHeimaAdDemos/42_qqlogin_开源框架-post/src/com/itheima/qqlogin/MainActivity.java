package com.itheima.qqlogin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.utils.StreamTool;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
		
		//你可以 选择 自己 进行 url 编码
		AsyncHttpClient client = new AsyncHttpClient();
		
		RequestParams params = new RequestParams();
		params.add("number", number);
		params.add("password", password);
		
		client.post(path, params,new AsyncHttpResponseHandler(){

			//访问 网络时, 服务器成功的处理了客户端的请求时 会被调用
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				
				Toast.makeText(MainActivity.this, "post 登录的结果是 : " + new String(responseBody), 0).show();
			}

			//访问 网络时, 请求失败会调用 
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				
				Toast.makeText(MainActivity.this, "对不起, 服务器无响应 ... ", 0).show();
			}
		});
    }

    
}
