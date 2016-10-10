package com.itheima.weather.forecast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itheima.utils.StreamTool;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.JsonReader;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int SUCCESS = 1;
	protected static final int ERROR = 2;
	protected static final int INVALID_CITY_NAME = 3;
	EditText ed_city;
	TextView tv_day01;
	TextView tv_day02;
	TextView tv_day03;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed_city = (EditText) findViewById(R.id.ed_city_name);
		tv_day01 = (TextView) findViewById(R.id.day01); 
		tv_day02 = (TextView) findViewById(R.id.day02); 
		tv_day03 = (TextView) findViewById(R.id.day03); 
	}
	
	Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case SUCCESS:
				
				JSONArray array = (JSONArray) msg.obj;
				
				try {
					tv_day01.setText(array.getString(0));
					tv_day02.setText(array.getString(1));
					tv_day03.setText(array.getString(2));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
			case ERROR:
				
				//������˾ 
				Toast.makeText(MainActivity.this, "�Բ���,�������� .", 0).show();
				
				break;
			case INVALID_CITY_NAME:
				
				//��Ч�� ���� 
				Toast.makeText(MainActivity.this, "��Ч�ĳ���  .", 0).show();
				
				break;

			default:
				break;
			}
			
		};
		
	};
	

	String path;
	String cityname;
	//���������Ϣ
	public void getWeatherInfo(View v){
		
		cityname = ed_city.getText().toString().trim();
		
		if(TextUtils.isEmpty(cityname)){
			Toast.makeText(this, "���в���Ϊ��", 0).show();
			return;
		}
		
		//http://wthrcdn.etouch.cn/weather_mini?city=%E4%B8%8A%E6%B5%B7
		//Ҫ���� ��� ���е����� 
		
		//����
		
		//��ôurl ������? 
		new Thread(){
			
			public void run() {
				
				try {
					String cityName = URLEncoder.encode(cityname, "UTF-8");
					
					path = "http://wthrcdn.etouch.cn/weather_mini?city="+cityName;
					
					URL url = new URL(path);
					
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					conn.setRequestMethod("GET");
					
					conn.setConnectTimeout(5000);
					
					int code = conn.getResponseCode();
					
					if(code==200){
						
						InputStream in = conn.getInputStream();
						
						//���� in ����, ��������� json ��ʽ������ 
						//���  �� in ������ת���� json��ʽ��������?
						//�ȸ蹤��ʦ�Ѿ��� ����json��ʽ��api ���� ������, ����ֻ��Ҫȥ�þͿ�����. 
						//��Ȼ, �� Ҳ���� ,�Լ�ȥ����һЩ��Դ��json �����, ���� ����Ĺ�����, 
						
						//��Ҫ�� ���������� ת����һ���ַ���, Ȼ�� ���ַ��� �������json Object
						
						String value = StreamTool.decodeStream(in);
						
						JSONObject obj = new JSONObject(value);
						
						String descValue = obj.getString("desc");
						if("OK".equals(descValue)){
							
							JSONObject dataObj = obj.getJSONObject("data");
							
							//��� Ԥ����  json����
							JSONArray forcastArray = dataObj.getJSONArray("forecast");
//							String vl1 = forcastArray.getString(0);
							
							Message msg = Message.obtain();
							msg.what=SUCCESS;
							msg.obj = forcastArray;
							handler.sendMessage(msg);
							
						}else{
							
							//˵������ ��Ϣ ���� -- ��Ч�ĳ��� 
							System.out.println("============");
							
							Message msg = Message.obtain();
							msg.what=INVALID_CITY_NAME;  // ctrl+shift+x ---���д 
							handler.sendMessage(msg);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = Message.obtain();
					msg.what=ERROR;
					handler.sendMessage(msg);
				}
				
			};
		}.start();
		
		
	}
}
