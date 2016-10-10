package com.itheima.qqlogin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
        
        //��ʼ�� �ؼ� 
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
					
					//�� ��˾ 
					Toast.makeText(MainActivity.this, "�Բ���, ��������. ", 0).show();
					break;
	
				default:
					break;
			}
    	};
    };
    
    //��¼�� ���� 
    public void login(View v){
    	
		path = getResources().getString(R.string.ip);
    	
		number = ed_number.getText().toString().trim();
		password = ed_password.getText().toString().trim();
		
//		�ж� �Ƿ�Ϊ�� 
		if(TextUtils.isEmpty(number)||TextUtils.isEmpty(password)){
			Toast.makeText(this, "��������벻��Ϊ��", 0).show();
			return;
		}
		
		//������ 
		new Thread(){
			public void run() {
				
				//  http://188.188.4.100:8080/day06_android/login?number=110&password=123
				try {
					
					HttpClient client = new DefaultHttpClient();
					
					// http://188.188.4.100:8080/day06_android/login
					HttpPost post = new HttpPost(path);
					
					//������ֵ�� 
					NameValuePair pair1=new BasicNameValuePair("number", number);
					NameValuePair pair2=new BasicNameValuePair("password", password);
					
					//��������ֵ�� �ŵ� list ��ȥ, Ȼ���� �� list ���뵽 ʵ���� 
					List<NameValuePair> list = new ArrayList<NameValuePair>();
					
					list.add(pair1);
					list.add(pair2);
					
					//���ﴫ �� UTF-8 �Ϳ��Խ����� ��������ȥ ��
					post.setEntity(new UrlEncodedFormEntity(list,"UTF-8"));
					
					HttpResponse response = client.execute(post);
					
					//���״̬�ж���,Ȼ���� ���״̬���е�״̬��
					int code = response.getStatusLine().getStatusCode();
					
					if(code==200){
						
						//�����Ӧ��,  �����Ӧ���е� �������� 
						//�������Ĵ����֮ǰһ����
						InputStream in = response.getEntity().getContent();
						
						String value = StreamTool.decodeStream(in);
						
						//����Ϣ
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
