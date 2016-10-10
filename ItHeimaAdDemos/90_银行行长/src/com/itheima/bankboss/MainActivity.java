package com.itheima.bankboss;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void insert(View v) {
		// ����Ӧ��ͨ�������ṩ�� content provider ��¶����
		// ��Ӧ������������ṩ��---���ݽ�����

		// 1.ͨ�������ĵõ����ݽ�����
		ContentResolver resolver = getContentResolver();

		// 2. Ҫ���������ṩ�ߣ�����Ҫָ������
		// ���������ṩ��ʡ���˿������������Ҳ��path·������������Ϊ���ܹ�ƥ��ɹ�
		// �����������������path·����
		Uri uri = Uri.parse("content://com.itheima.db.BANK/account");

		ContentValues values = new ContentValues();
		values.put("name", "zhangsan");
		values.put("money", 5000);

		// 3.���������ṩ���е���ӷ���
		resolver.insert(uri, values);

	}

	public void delete(View v) {

		// 1.ͨ�������ĵõ����ݽ�����
		ContentResolver resolver = getContentResolver();

		// 2. Ҫ���������ṩ�ߣ�����Ҫָ������
		// ���������ṩ��ʡ���˿������������Ҳ��path·������������Ϊ���ܹ�ƥ��ɹ�
		// �����������������path·����
		Uri uri = Uri.parse("content://com.itheima.db.BANK/account");
		resolver.delete(uri, "name=?", new String[] { "zhangsan" });
	}

	public void update(View v) {

		// 1.ͨ�������ĵõ����ݽ�����
		ContentResolver resolver = getContentResolver();

		// 2. Ҫ���������ṩ�ߣ�����Ҫָ������
		// ���������ṩ��ʡ���˿������������Ҳ��path·������������Ϊ���ܹ�ƥ��ɹ�
		// �����������������path·����
		Uri uri = Uri.parse("content://com.itheima.db.BANK/account");

		//������º������
		ContentValues values = new ContentValues();
		values.put("money", 500);

		resolver.update(uri, values, "name=?", new String[]{"zhangsan"});
	}

	public void query(View v) {
		
		// 1.ͨ�������ĵõ����ݽ�����
		ContentResolver resolver = getContentResolver();

		// 2. Ҫ���������ṩ�ߣ�����Ҫָ������
		// ���������ṩ��ʡ���˿������������Ҳ��path·������������Ϊ���ܹ�ƥ��ɹ�
		// �����������������path·����
		Uri uri = Uri.parse("content://com.itheima.db.BANK/account");
		
		Cursor cursor =resolver.query(uri, null, null, null, null);
		while(cursor.moveToNext()){
			int money = cursor.getInt(cursor.getColumnIndex("money")); 
			System.out.println("mone==="+money);
		}
		cursor.close();
	}
}
