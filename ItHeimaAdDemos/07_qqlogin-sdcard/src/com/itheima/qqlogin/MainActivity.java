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
		
		//初始化
		ed_number = (EditText) findViewById(R.id.ed_number);
		ed_pwd = (EditText) findViewById(R.id.ed_pwd);
		
		
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
			
			//保存 到 公共的 sd 卡上, 所有的应用程序 都可以访问的  /mnt/sdcard
			
			//   /mnt/sdcard/emlulated0
			//   /mnt/sdcard/emlulated1
			
//			File file = new File("/mnt/sdcard/info.txt");
			
			//如果要保存数据到sd 卡上, 那么 sd 必须 得 挂载在手机上.. 
			// 判断sd 卡是否 可用, 如果 sd 卡不可用, 应该给用户提示 
			
			//怎么判断sd 卡 是否可用 呢?
			
//			if(!Environment.getExternalStorageState().equals("mounted")){
			if(!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
				Toast.makeText(this, "对不起, sd 卡 不可用, 请检查sd 卡的状态   ", 0).show();
				//如果进来, 则需要给用户提示 sd 卡不可用 
				return;
			}
			
			//还会检查 sd 卡的 可用 是否够 
			
			long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
			
			String size = Formatter.formatFileSize(this, freeSpace);
			Toast.makeText(this, "可用存储 空间 是 : " + size, 0).show();
			
			//同学们一定要具有 学习的,抄的能力. 
			
			//动态的获得 sd 卡的路径 
			File file = new File(Environment.getExternalStorageDirectory(),"info.txt");
			//
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
