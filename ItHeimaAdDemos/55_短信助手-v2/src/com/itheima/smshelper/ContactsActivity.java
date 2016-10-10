package com.itheima.smshelper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactsActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		
		ListView lv = (ListView) findViewById(R.id.lv);
		
		String [] objects = {
				"1375672348234",
				"1375672348235",
				"1375672348236",
				"1375672348237",
				"1375672348238",
				"1375672348239",
				"1375672348230",
				"13756723482312",
				"13756723482322",
				"13756723482332",
				"13756723482342",
				
				
		};
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, objects));
	}
}
