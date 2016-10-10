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
		
		//回显以前保存的IP号码
		String number  = sp.getString("number", ""); 
		et_number.setText(number);
	}
	
	
	/**
	 * 保存设置IP号码
	 * @param v
	 */
	public void save(View v){
		
		String number = et_number.getText().toString();
		
		//使用sp保存IP号码
		Editor editor = sp.edit();
		editor.putString("number", number);
		editor.commit();
		Toast.makeText(this, "设置成功", 0).show();
		
	}


}
