package com.itheima.launchmode;

import java.io.Serializable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class FirstActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
	/*	Intent intent = new Intent();
		//bundleʵ���Ͼ��ǰ�һ��ɢ����㲿�����������Ȼ���������ϴ����ݸ���һ������
		Bundle value = new Bundle();
		value.putString("name", name);
		value.putInt("number",10086);
		intent.putExtra("bundle", value);
		
		
		
		Bundle data = intent.getBundleExtra("bundle");
		data.getString(key)*/
	}

	public void click01(View v){
		startActivity(new Intent(this, FirstActivity.class));
	}
	public void click02(View v){
		
		startActivity(new Intent(this, SecondActivity.class));
	}
}
