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
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int SUCCESS = 1;  //����ĳ���, �����ж� ������ ������Ϣ
	protected static final int ERROR = 2;
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
	
	//handler ���������ʾ������ 
	Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case SUCCESS:
				Bitmap bitmap = (Bitmap) msg.obj;
				iv_pic.setImageBitmap(bitmap);
				break;
			case ERROR:  //ʧ��
				//����˾ 
				Toast.makeText(MainActivity.this, "�Բ���, ������. ..", 0).show();
				System.out.println("==========������....");
			default:	
				break;
			}
			
		};
		
	};
	
	
	
	//����鿴ͼƬ
	public void getPic(View v){
		
		//http://www.itheima.com/uploads/2015/08/198x57.png
		path = ed_path.getText().toString();
		
		new Thread(){
			
			public void run() {
				
				try {
					
					//����һ�� url�����ʵ�� 
					URL url = new URL(path);
					
					//ʹ�õ�ǰ��url �����������  ������ 
					HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
					
					//��������ķ�ʽ
					conn.setRequestMethod("GET");
					
					//���� ��ʱ�� ʱ�� Ϊ 5 �� 
					conn.setConnectTimeout(5000);
					
					// 200, 302 , 304, 404, 500 
					int code = conn.getResponseCode();
					
					//�õ� ������ ���ص����ݵ� ���� 
					int length = conn.getContentLength();  // 
					String type = conn.getContentType();    //   image/png
					
					System.out.println("length :"+ length);
					System.out.println("type :"+ type);
					
					/*if("text/html".equals(type)){
						
					}*/
					if(code==200){
						
						//�������ʾ , �ɹ��� ���ܵ��˷������� ��Ӧ������,  ������ ��Ӧ�ɹ�
						
						//��� in �ʹ����� ���ص� ͼƬ���� 
						InputStream in = conn.getInputStream();
						
						//��ν���������ת����һ�� ͼƬ��?
						
						// ��� bitmap ���Ǵ����� һ��ͼƬ .
						 Bitmap bitmap = BitmapFactory.decodeStream(in);
						
						 //Ҫ ��ͼƬ ��ʾ �� ImageView�� 
//						 iv_pic.setImageBitmap(bitmap);
						 
						 
						 //ͨ��handler ����Ϣ --Message
						 
						 //����   Message.obtain() ������ �ظ� ���� ��� ��Ϣ, 
						 // �ﵽ�� ���� message. �鿴Դ���� �Ϳ��� �˽⵽ ..
						 Message msg = Message.obtain();
						 msg.obj = bitmap;
						 msg.what=SUCCESS ;
						 handler.sendMessage(msg);
						 
					}else{
						
						//���� �Ļ�,��˵���������� ����ʹ, Ҳ��һ����Ϣ, ֪ͨ ���߳� ���û� ��ʾ 
						Message msg = Message.obtain();
						msg.what=ERROR;
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
