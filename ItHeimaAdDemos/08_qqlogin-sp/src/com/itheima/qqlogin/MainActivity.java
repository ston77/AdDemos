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
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ʼ��
		ed_number = (EditText) findViewById(R.id.ed_number);
		ed_pwd = (EditText) findViewById(R.id.ed_pwd);
		
		
		//��QQ ��������� ���� ���� 
		sp = getSharedPreferences("config", 0);
		
		String number = sp.getString("number", "");
		String pwd = sp.getString("pwd", "");
		ed_number.setText(number);
		ed_pwd.setText(pwd);
		
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
			
			//ʹ�� sharedPreferences��������, ���浽 xml �ļ��е� 
			
//			getSharedPreferences("config", Context.MODE_PRIVATE);
			
			Editor editor = sp.edit();
			editor.putString("number", number);
			editor.putString("pwd", pwd);
			//�ύ, ��ô���ݾͻ� �� �ڴ��� д�� xml �ļ���ȥ�� 
			//editor.commit();  
			editor.apply();
			
			
			Toast.makeText(this, "����ɹ�  ", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "����ʧ��   ", 0).show();
		}
	}

}
