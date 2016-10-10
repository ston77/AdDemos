package com.itheima.ipcall;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText et_number;
	
	SharedPreferences sp ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_number = (EditText) findViewById(R.id.et_number);
		sp = getSharedPreferences("ip", 0);
		
		//������ǰ�����IP����
		String number  = sp.getString("number", ""); 
		et_number.setText(number);
	}
	
	
	/**
	 * ��������IP����
	 * @param v
	 */
	public void save(View v){
		
		String number = et_number.getText().toString();
		
		//ʹ��sp����IP����
		Editor editor = sp.edit();
		editor.putString("number", number);
		editor.commit();
		Toast.makeText(this, "���óɹ�", 0).show();
		
	}


}
