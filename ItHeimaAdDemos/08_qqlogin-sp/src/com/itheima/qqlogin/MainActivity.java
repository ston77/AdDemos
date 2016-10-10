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
		
		//初始化
		ed_number = (EditText) findViewById(R.id.ed_number);
		ed_pwd = (EditText) findViewById(R.id.ed_pwd);
		
		
		//将QQ 号码和密码 回显 回来 
		sp = getSharedPreferences("config", 0);
		
		String number = sp.getString("number", "");
		String pwd = sp.getString("pwd", "");
		ed_number.setText(number);
		ed_pwd.setText(pwd);
		
	}

	//登录的逻辑 --- 想办法 保存数据
	public void login(View v){
		
		String number = ed_number.getText().toString().trim();
		String pwd = ed_pwd.getText().toString().trim();
		
		if(TextUtils.isEmpty(number)||TextUtils.isEmpty(pwd)){
			//弹个 土司 提示 
			Toast.makeText(this, "对不起, QQ 号码和密码不能为空 ", 0).show();
			return;
		}
		
		//走到这里, 要保存 数据 
		
		try {
			
			//使用 sharedPreferences保存数据, 保存到 xml 文件中的 
			
//			getSharedPreferences("config", Context.MODE_PRIVATE);
			
			Editor editor = sp.edit();
			editor.putString("number", number);
			editor.putString("pwd", pwd);
			//提交, 那么数据就会 由 内存中 写到 xml 文件中去了 
			//editor.commit();  
			editor.apply();
			
			
			Toast.makeText(this, "保存成功  ", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "保存失败   ", 0).show();
		}
	}

}
