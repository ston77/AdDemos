package com.itheima.smshelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText et_content ; 
	SharedPreferences sp ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_content = (EditText) findViewById(R.id.et_content);
		sp = getSharedPreferences("sms", 0);
		
		//获取到以前保存过的数据，如果没有，则显示一个空字符串
		String content =sp.getString("content", "");
		et_content.setText(content);
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		//当界面销毁的时候，存储当前的短信内容
		
		String content = et_content.getText().toString();
		Editor editor = sp.edit();
		editor.putString("content", content);
		editor.commit();
		
	}

	
	public void selectContact(View v){
		Intent intent = new Intent(this,ContactsActivity.class);
		//startActivity(intent);//启动一个新的界面，但是不需要返回结果
		
		//1. 启动下一个界面，然后等待结果的返回
		startActivityForResult(intent,1);
	}

	public void select(View v){
		
		Intent intent = new Intent(this,SmsListActivity.class);
		//startActivity(intent);//启动一个新的界面，但是不需要返回结果
		
		//1. 启动下一个界面，然后等待结果的返回
		startActivityForResult(intent,2);
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//为了区分到底显示是哪一个请求回来了，所以有必要判断一下requestCode.
		/*switch (requestCode) {
		
		case 1://联系人回来
			String contact = data.getStringExtra("contact");
			et_contact.setText(contact);
			
			break;
		case 2: //短信模板回来
			//4.获取到下一个界面返回的数据
			String sms = data.getStringExtra("sms");
			et_content.setText(sms);
			break;

		default:
			break;
		}*/
	}
	
}
