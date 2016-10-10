package com.itheima.rp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ������Ʒ�Ľ���
 */
public class CalcActivity extends Activity {
	EditText et_name;
	TextView tv_result ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_calc);
		et_name = (EditText) findViewById(R.id.name);
		
		tv_result = (TextView) findViewById(R.id.tv_result);
	}
	
	
	public void calc (View v){
		String name = et_name.getText().toString(); 
		
		//�����ֵ�����ascIIֵ�����  �� 100 �Ϳ��ԡ�
		
		byte [] bytes = name.getBytes(); 
		int number =0;
		for (byte b : bytes) {
			number += Math.abs(b&0xff); //ȡ����ֵ  ��byte���͵���ֵ������int����   -128  ~ 127
		}
		int result  = number % 100; 
		Toast.makeText(this, "��Ʒ�÷��ǣ�"+result, 0).show();
		String str="";
		if(result > 80){
			str="����ǵ����Ļ��׷棬��Ʒ����!";
		}else if(result > 60){
			str="�����Ʒ����������Ŭ��";
		}else if(result > 40){
			str="͵���������£��������ɣ���Ʒһ��";
		}else if(result > 20){
			str="�찡�� �Ҳ��ø���̸��Ʒ��";
		}else{
			str="��ľ��Ǹ���..";
		}
		
		tv_result.setText("������"+name+"\r\n��Ʒ�÷֣�"+result+"\r\n���ۣ�"+str);
		
	}

}
