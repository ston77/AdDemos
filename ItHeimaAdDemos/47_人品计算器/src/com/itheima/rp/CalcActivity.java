package com.itheima.rp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 计算人品的界面
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
		
		//把名字的所有ascII值给相加  余 100 就可以。
		
		byte [] bytes = name.getBytes(); 
		int number =0;
		for (byte b : bytes) {
			number += Math.abs(b&0xff); //取绝对值  把byte类型的数值提升成int类型   -128  ~ 127
		}
		int result  = number % 100; 
		Toast.makeText(this, "人品得分是："+result, 0).show();
		String str="";
		if(result > 80){
			str="你就是当代的活雷锋，人品爆表!";
		}else if(result > 60){
			str="你的人品还不错，继续努力";
		}else if(result > 40){
			str="偷鸡摸狗的事，经常做吧，人品一般";
		}else if(result > 20){
			str="天啊， 我不该跟你谈人品。";
		}else{
			str="你的就是个渣..";
		}
		
		tv_result.setText("姓名："+name+"\r\n人品得分："+result+"\r\n评价："+str);
		
	}

}
