package com.itheima.ceshouji;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import com.itheima.ceshouji.domain.Product;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int SUCCESS = 0;   //成功  
	protected static final int ERROR = 1;   // 连接错误, 失败 
	EditText ed_phonenumber;
	TextView tv_result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed_phonenumber = (EditText) findViewById(R.id.ed_phonenumber);
		tv_result = (TextView) findViewById(R.id.tv_result);
	}
	String path;
	
	private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case SUCCESS:
				
				Product p = (Product) msg.obj;
				
				tv_result.setText(p.getPhonenum()+"\n"+p.getLocation() +"\n"+p.getPhoneJx());
				
				break;
			case ERROR:
				
				Toast.makeText(MainActivity.this, "出错了.... ", 0).show();
				System.out.println("出错了");
				break;

			default:
				break;
			}
			
			
		};
	};
	
	
	//测试 手机号码 
	public void ceyice(View v){
		
		//获得手机号码 
		String number = ed_phonenumber.getText().toString().trim();
		
		//获得 发请求的地址 
		String ip = getResources().getString(R.string.ip);
		
		//http://www.096.me/api.php?phone=13691689238&mode=xml
		path = ip+"?phone="+number+"&mode=xml";
		
//		发请求联网
		new Thread(){
			
			public void run() {
				
				try {
					URL url = new URL(path);
					
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					//设置请求方式
					conn.setRequestMethod("GET");
					
					//设置超时 时间 
					conn.setConnectTimeout(5000);
					
					int code = conn.getResponseCode();
					if(code==200){
						
						//服务器成功的处理了请求 
						
						InputStream in = conn.getInputStream();
						
						//由于返回的是 xml 格式的数据, 所以这里 要解析 xml 了, 使用 pull解析器 
						
						XmlPullParser parser = Xml.newPullParser();
						
						parser.setInput(in, "gbk");
/*
 * 
<?xml version="1.0" encoding="gbk"?>
<smartresult>
	<product type="mobile">
	    <phonenum>13691689238</phonenum>
	    <location>广东深圳移动神州行卡</location>
	    <phoneJx>名虽可得，利则难获，艺界发展，可望成功 凶带吉</phoneJx>
	</product>
</smartresult>
 * 						
 */
						Product p = new Product();
						int type = parser.getEventType();
						
						while(type!=XmlPullParser.END_DOCUMENT){
							
							if(type==XmlPullParser.START_TAG){
								
								if("product".equals(parser.getName())){
									String value = parser.getAttributeValue(0);
									p.setType(value);
								}else if("phonenum".equals(parser.getName())){
									String phonenum = parser.nextText();
									p.setPhonenum(phonenum);
								}else if("location".equals(parser.getName())){
									String location = parser.nextText();
									p.setLocation(location);
								}else if("phoneJx".equals(parser.getName())){
									String phoneJx = parser.nextText();
									p.setPhoneJx(phoneJx);
								}
								
							}
							type=parser.next();
						}
						
						//解析 xml 文件完成, 那么就 handler 发消息,通知 UI 界面更新 ,显示数据
						Message msg = Message.obtain();
						msg.what=SUCCESS;
						msg.obj = p;
						handler.sendMessage(msg);
						
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
