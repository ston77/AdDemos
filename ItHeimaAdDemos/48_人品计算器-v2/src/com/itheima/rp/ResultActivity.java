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
 * ��ʾ�������
 */
public class ResultActivity extends Activity {
	TextView tv_result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_result);

		tv_result = (TextView) findViewById(R.id.tv_result);
		
		ImageView iv = (ImageView) findViewById(R.id.iv);

		// ������һ�����洫�ݹ����˽���������ڴ�Ҫ�ǵû�ȡ���ݵ�����
		Intent intent = getIntent();

		String name = intent.getStringExtra("name");

		int checkId = intent.getIntExtra("gender", 0);
		
		Bitmap bitmap =intent.getParcelableExtra("bitmap");
		
		iv.setImageBitmap(bitmap);
		
		// �����ֵ�����ascIIֵ����� �� 100 �Ϳ��ԡ�
		byte[] bytes =null;
		try {
			switch (checkId) {
			case R.id.rb_male://ѡ��������
				bytes = name.getBytes();//UTF-8����
				break;
			case R.id.rb_female://ѡ��Ů��
				
				bytes = name.getBytes("GBK");
				break;
			case R.id.rb_unkonw://ѡ����δ֪
				bytes = name.getBytes("iso8859-1");
				
				break;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}


		int number = 0;
		for (byte b : bytes) {
			number += Math.abs(b & 0xff); // ȡ����ֵ ��byte���͵���ֵ������int���� -128 ~ 127
		}
		int result = number % 100;
		Toast.makeText(this, "��Ʒ�÷��ǣ�" + result, 0).show();
		String str = "";
		if (result > 80) {
			str = "����ǵ����Ļ��׷棬��Ʒ����!";
		} else if (result > 60) {
			str = "�����Ʒ����������Ŭ��";
		} else if (result > 40) {
			str = "͵���������£��������ɣ���Ʒһ��";
		} else if (result > 20) {
			str = "�찡�� �Ҳ��ø���̸��Ʒ��";
		} else {
			str = "��ľ��Ǹ���..";
		}

		String resultStr = "������" + name + "\r\n��Ʒ�÷֣�" + result + "\r\n���ۣ�"
				+ str;
		
		tv_result.setText(resultStr);
	}
}
