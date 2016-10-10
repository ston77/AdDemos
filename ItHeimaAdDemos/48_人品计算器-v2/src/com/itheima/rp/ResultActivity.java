package com.itheima.rp;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 显示结果界面
 */
public class ResultActivity extends Activity {
	TextView tv_result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_result);

		tv_result = (TextView) findViewById(R.id.tv_result);
		
		ImageView iv = (ImageView) findViewById(R.id.iv);

		// 由于上一个界面传递过来了结果，所以在此要记得获取传递的数据
		Intent intent = getIntent();

		String name = intent.getStringExtra("name");

		int checkId = intent.getIntExtra("gender", 0);
		
		Bitmap bitmap =intent.getParcelableExtra("bitmap");
		
		iv.setImageBitmap(bitmap);
		
		// 把名字的所有ascII值给相加 余 100 就可以。
		byte[] bytes =null;
		try {
			switch (checkId) {
			case R.id.rb_male://选中了男性
				bytes = name.getBytes();//UTF-8编码
				break;
			case R.id.rb_female://选中女性
				
				bytes = name.getBytes("GBK");
				break;
			case R.id.rb_unkonw://选中了未知
				bytes = name.getBytes("iso8859-1");
				
				break;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		int number = 0;
		for (byte b : bytes) {
			number += Math.abs(b & 0xff); // 取绝对值 把byte类型的数值提升成int类型 -128 ~ 127
		}
		int result = number % 100;
		Toast.makeText(this, "人品得分是：" + result, 0).show();
		String str = "";
		if (result > 80) {
			str = "你就是当代的活雷锋，人品爆表!";
		} else if (result > 60) {
			str = "你的人品还不错，继续努力";
		} else if (result > 40) {
			str = "偷鸡摸狗的事，经常做吧，人品一般";
		} else if (result > 20) {
			str = "天啊， 我不该跟你谈人品。";
		} else {
			str = "你的就是个渣..";
		}

		String resultStr = "姓名：" + name + "\r\n人品得分：" + result + "\r\n评价："
				+ str;
		
		tv_result.setText(resultStr);
	}
}
