package com.itheima.qqlogin;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText ed_number;
	EditText ed_pwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ʼ��
		ed_number = (EditText) findViewById(R.id.ed_number);
		ed_pwd = (EditText) findViewById(R.id.ed_pwd);
		
		try {
			File file = new File(getCacheDir(),"info.txt");
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String value = br.readLine();   // 1111#123213
			String number = value.split("#")[0];
			String pwd = value.split("#")[1];
			
			ed_number.setText(number);
			ed_pwd.setText(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	//��¼���߼� --- ��취 ��������
	public void login(View v){
		
		String number = ed_number.getText().toString().trim();
		String pwd = ed_pwd.getText().toString().trim();
		
		if(TextUtils.isEmpty(number)||TextUtils.isEmpty(pwd)){
			//���� ��˾ ��ʾ 
			Toast.makeText(this, "�Բ���, QQ ��������벻��Ϊ�� ", 0).show();
			return;
		}
		
		//�ߵ�����, Ҫ���� ���� 
		
		try {
//			File file = new File("info.txt");
//			File file = new File("/data/data/com.itheima.qqlogin/info.txt");
			
			//ͨ�� api ȥ��̬ ���Ӧ�ó���˽�е��ļ��� 
			//���� �� Ӧ�ó����  /data/data/com.itheima.qqlogin/files�ļ�������
			File file = new File(getFilesDir(),"info.txt");
			
			//���� �� Ӧ�ó����  /data/data/com.itheima.qqlogin/cache�ļ�������
//			File file = new File(getCacheDir(),"info.txt");
			
			String value = number+"#"+pwd;
			OutputStream out = new FileOutputStream(file);
			
			out.write(value.getBytes());
			out.close();
			Toast.makeText(this, "����ɹ�  ", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "����ʧ��   ", 0).show();
		}
		
	}

}
