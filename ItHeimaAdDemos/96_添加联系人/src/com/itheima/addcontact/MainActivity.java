package com.itheima.addcontact;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText et_name ,et_phone ,et_email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_name = (EditText) findViewById(R.id.name);
		et_phone = (EditText) findViewById(R.id.phone);
		et_email = (EditText) findViewById(R.id.email);
	}

	public void save(View v){
		//���һ����ϵ�˼�¼
		ContentResolver resolver = getContentResolver();
		//2�����ѯraw_contact���uri
		Uri contactUri = Uri.parse("content://com.android.contacts/raw_contacts");
		//��ѯdata���uri
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		
		//���ڲ�֪����ǰ��contact_id�Ѿ��ߵ��˶��٣����Բ���äĿֱ�ӱ�д��Ӧ��ȥ��ѯ
		//�ñ��е�_id�У�Ȼ�������У�ȡ��һ���Ϳ����ˡ� 
		Cursor cursor = resolver.query(contactUri, new String[]{"_id"}, null, null, "_id desc");
		cursor.moveToFirst();
		int id = cursor.getInt(0)+1; //��ԭ����id������+1 ���γ�����������ӵ�id
		
		ContentValues values = new ContentValues();
		values.put("contact_id", id);
		resolver.insert(contactUri, values);
		
		
		//��data�������������
		//�������
		ContentValues nameVal = new ContentValues();
		nameVal.put("data1", et_name.getText().toString()); //�������
		nameVal.put("raw_contact_id", id); //��������������˭��uri
		nameVal.put("mimetype", "vnd.android.cursor.item/name"); //ָ����ӵ���������ʲô����
		resolver.insert(dataUri, nameVal);//�����¼
		
		//��ӵ绰
		ContentValues phoneVal = new ContentValues();
		phoneVal.put("data1", et_phone.getText().toString());
		phoneVal.put("raw_contact_id", id);
		phoneVal.put("mimetype", "vnd.android.cursor.item/phone_v2");
		resolver.insert(dataUri, phoneVal);
		
		//�������
		ContentValues emailVal = new ContentValues();
		emailVal.put("data1", et_email.getText().toString());
		emailVal.put("raw_contact_id", id);
		emailVal.put("mimetype", "vnd.android.cursor.item/email_v2");
		resolver.insert(dataUri, emailVal);
		
	
		Toast.makeText(this, "��ӳɹ�", 0).show();
	}
}
