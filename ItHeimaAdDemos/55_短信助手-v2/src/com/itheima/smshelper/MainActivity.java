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
		
		//��ȡ����ǰ����������ݣ����û�У�����ʾһ�����ַ���
		String content =sp.getString("content", "");
		et_content.setText(content);
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		//���������ٵ�ʱ�򣬴洢��ǰ�Ķ�������
		
		String content = et_content.getText().toString();
		Editor editor = sp.edit();
		editor.putString("content", content);
		editor.commit();
		
	}

	
	public void selectContact(View v){
		Intent intent = new Intent(this,ContactsActivity.class);
		//startActivity(intent);//����һ���µĽ��棬���ǲ���Ҫ���ؽ��
		
		//1. ������һ�����棬Ȼ��ȴ�����ķ���
		startActivityForResult(intent,1);
	}

	public void select(View v){
		
		Intent intent = new Intent(this,SmsListActivity.class);
		//startActivity(intent);//����һ���µĽ��棬���ǲ���Ҫ���ؽ��
		
		//1. ������һ�����棬Ȼ��ȴ�����ķ���
		startActivityForResult(intent,2);
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//Ϊ�����ֵ�����ʾ����һ����������ˣ������б�Ҫ�ж�һ��requestCode.
		/*switch (requestCode) {
		
		case 1://��ϵ�˻���
			String contact = data.getStringExtra("contact");
			et_contact.setText(contact);
			
			break;
		case 2: //����ģ�����
			//4.��ȡ����һ�����淵�ص�����
			String sms = data.getStringExtra("sms");
			et_content.setText(sms);
			break;

		default:
			break;
		}*/
	}
	
}
