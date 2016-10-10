package com.itheima.open;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	//ʹ����ʽ�ַ�����01����
	public void click01(View v){
		Intent intent = new Intent(this,FirstActivity.class);
		startActivity(intent);
	}
	
	//ʹ����ʽ�ַ�����02����
	public void click02(View v){
		Intent intent = new Intent();
		//ָ����ת��ʲô�����Ľ���
		intent.setAction("com.itheima.open.action.OPEN");
		
		//���ڷ�����Ĭ�ϵķ��࣬���Կ��Բ���д��
		intent.addCategory("android.intent.category.DEFAULT");
		
		startActivity(intent);
		
	}
}
