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
		
		//初始化
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
//			File file = new File("info.txt");
//			File file = new File("/data/data/com.itheima.qqlogin/info.txt");
			
			//通过 api 去动态 获得应用程序私有的文件夹 
			//保存 到 应用程序的  /data/data/com.itheima.qqlogin/files文件夹里了
			File file = new File(getFilesDir(),"info.txt");
			
			//保存 到 应用程序的  /data/data/com.itheima.qqlogin/cache文件夹里了
//			File file = new File(getCacheDir(),"info.txt");
			
			String value = number+"#"+pwd;
			OutputStream out = new FileOutputStream(file);
			
			out.write(value.getBytes());
			out.close();
			Toast.makeText(this, "保存成功  ", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "保存失败   ", 0).show();
		}
		
	}

}
