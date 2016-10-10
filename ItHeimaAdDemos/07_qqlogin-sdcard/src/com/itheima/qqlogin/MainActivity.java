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
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.Formatter;
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
			
			//���� �� ������ sd ����, ���е�Ӧ�ó��� �����Է��ʵ�  /mnt/sdcard
			
			//   /mnt/sdcard/emlulated0
			//   /mnt/sdcard/emlulated1
			
//			File file = new File("/mnt/sdcard/info.txt");
			
			//���Ҫ�������ݵ�sd ����, ��ô sd ���� �� �������ֻ���.. 
			// �ж�sd ���Ƿ� ����, ��� sd ��������, Ӧ�ø��û���ʾ 
			
			//��ô�ж�sd �� �Ƿ���� ��?
			
//			if(!Environment.getExternalStorageState().equals("mounted")){
			if(!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
				Toast.makeText(this, "�Բ���, sd �� ������, ����sd ����״̬   ", 0).show();
				//�������, ����Ҫ���û���ʾ sd �������� 
				return;
			}
			
			//������ sd ���� ���� �Ƿ� 
			
			long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
			
			String size = Formatter.formatFileSize(this, freeSpace);
			Toast.makeText(this, "���ô洢 �ռ� �� : " + size, 0).show();
			
			//ͬѧ��һ��Ҫ���� ѧϰ��,��������. 
			
			//��̬�Ļ�� sd ����·�� 
			File file = new File(Environment.getExternalStorageDirectory(),"info.txt");
			//
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
