package com.itheima.other;

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

	//��ʽ����04Ӧ�õĽ���01
	public void click01(View v) {
		
		
		//��������ʽ�ַ�ע��Ľ��棬����������Ӧ�����ǲ��ɱ��򿪵ġ�
		Intent intent = new Intent();
		intent.setClassName("com.itheima.openstyle", "com.itheima.openstyle.MainActivity");
		startActivity(intent);
	}

	//��ʽ����04Ӧ�õĽ���02
	public void click02(View v) {
		Intent intent = new Intent();
		
		//ָ����ת��ʲô�����Ľ���
		intent.setAction("com.itheima.openstyle.action.OPEN");
		
		//���ڷ�����Ĭ�ϵķ��࣬���Կ��Բ���д��
		intent.addCategory("android.intent.category.DEFAULT");
		startActivity(intent);
	}
}
