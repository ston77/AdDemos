package com.itheima.jixiongchesi;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		Context.getFilesDir();
		
		getCacheDir();
		getResources();
		AssetManager manager = getAssets();
		try {
			InputStream in = manager.open("phone.xml");
			
			//使用pull 解析器解析 xml 文件了 
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
