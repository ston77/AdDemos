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

	protected static final int SUCCESS = 0;   //�ɹ�  
	protected static final int ERROR = 1;   // ���Ӵ���, ʧ�� 
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
				
				Toast.makeText(MainActivity.this, "������.... ", 0).show();
				System.out.println("������");
				break;

			default:
				break;
			}
			
			
		};
	};
	
	
	//���� �ֻ����� 
	public void ceyice(View v){
		
		//����ֻ����� 
		String number = ed_phonenumber.getText().toString().trim();
		
		//��� ������ĵ�ַ 
		String ip = getResources().getString(R.string.ip);
		
		//http://www.096.me/api.php?phone=13691689238&mode=xml
		path = ip+"?phone="+number+"&mode=xml";
		
//		����������
		new Thread(){
			
			public void run() {
				
				try {
					URL url = new URL(path);
					
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					//��������ʽ
					conn.setRequestMethod("GET");
					
					//���ó�ʱ ʱ�� 
					conn.setConnectTimeout(5000);
					
					int code = conn.getResponseCode();
					if(code==200){
						
						//�������ɹ��Ĵ��������� 
						
						InputStream in = conn.getInputStream();
						
						//���ڷ��ص��� xml ��ʽ������, �������� Ҫ���� xml ��, ʹ�� pull������ 
						
						XmlPullParser parser = Xml.newPullParser();
						
						parser.setInput(in, "gbk");
/*
 * 
<?xml version="1.0" encoding="gbk"?>
<smartresult>
	<product type="mobile">
	    <phonenum>13691689238</phonenum>
	    <location>�㶫�����ƶ������п�</location>
	    <phoneJx>����ɵã������ѻ��ս緢չ�������ɹ� �״���</phoneJx>
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
						
						//���� xml �ļ����, ��ô�� handler ����Ϣ,֪ͨ UI ������� ,��ʾ����
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
