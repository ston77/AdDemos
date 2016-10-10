package com.itheima.smsprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	//��Ӷ���
	public void insert(View v) {
		ContentResolver resolver = getContentResolver();
		//������������ͨ���鿴�ϲ�Ӧ���е�provider�е�TelephonyProvider ���Ӧ����
//		�嵥�ļ���Դ����
		Uri uri = Uri.parse("content://sms");
		ContentValues values = new ContentValues();
		values.put("address", "110");
		values.put("date", System.currentTimeMillis());
		values.put("type", 1);
		values.put("body", "���ã��𾴵�����������ϲ���ٻ�����񽱣�ϣ�������Ժ�������У����ں͸��ڷ������̹���·��");
		
		//�������ŵ���ӷ���
		resolver.insert(uri, values);
	}

	//ɾ������
	public void delete(View v) {
		ContentResolver resolver = getContentResolver();
		//������������ͨ���鿴�ϲ�Ӧ���е�provider�е�TelephonyProvider ���Ӧ����
		//�嵥�ļ���Դ����
		Uri uri = Uri.parse("content://sms");
		resolver.delete(uri, "address=?", new String[]{"110"});
		Toast.makeText(this, "ɾ����Ϣ�ɹ�", 0).show();
	}
}
