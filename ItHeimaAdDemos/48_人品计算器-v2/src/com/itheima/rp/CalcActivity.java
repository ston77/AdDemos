package com.itheima.rp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 计算人品的界面
 */
public class CalcActivity extends Activity {
	EditText et_name;
	//TextView tv_result ;
	RadioGroup rg_gender;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_calc);
		et_name = (EditText) findViewById(R.id.name);
		
		rg_gender = (RadioGroup) findViewById(R.id.rg_gender);
		
		//tv_result = (TextView) findViewById(R.id.tv_result);
	}
	
	
	public void calc (View v){
		String name = et_name.getText().toString(); 
		//获取到选中的性别控件ID
		int checkId = rg_gender.getCheckedRadioButtonId();
		
		Intent intent = new Intent(this , ResultActivity.class);
		//在跳转界面的同时，顺便传递数据过去
		intent.putExtra("name", name); //姓名
		intent.putExtra("gender", checkId); //选中的性别id
		
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
		
		
		intent.putExtra("bitmap", bitmap);
		startActivity(intent);
	}

}
