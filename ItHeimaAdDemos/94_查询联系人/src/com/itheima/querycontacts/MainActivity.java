package com.itheima.querycontacts;

import android.app.Activity;
import android.content.ContentResolver;
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

	
	public void query(View v){
		//1.��ȡ�����ݽ����߶���
		ContentResolver resolver = getContentResolver();
		
		//2�����ѯraw_contact���uri
		Uri contactUri = Uri.parse("content://com.android.contacts/raw_contacts");
		//��ѯdata���uri
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		//3.��ѯ��ϵ�˵�ID
		Cursor cursor = resolver.query(contactUri, new String[]{"contact_id" }, null, null, null);
		while(cursor.moveToNext()){
			//ִ����һ�д��룬����ֻ��ѯ������ϵ�˵�ID��������������ȡ����ϵ�˵�������Ϣ�����磺�������绰�����䣬
			//��ô��Ӧ�ð�id�ŵ���������ȥ��ѯ�ˣ� data
			int id= cursor.getInt(cursor.getColumnIndex("contact_id"));
			System.out.println("id==="+id);
			
			//������ϵ�˵�ID��ȥdata���ѯ���ݣ������������ϵ�˵����ݲ�ѯ������
			/**
			 * �ڲ�ѯ��ϵ�˵��������͵�ʱ��ϵͳ���������ȥ��ѯdata������ȥ��ѯ��view_data��
			 * ���ԣ� ָ����miemtype,����д��mimetype_id .���ҷ��ص�����Ӧ����String
			 */
			Cursor dataCursor = resolver.query(dataUri, new String[]{"data1", "mimetype"}, "raw_contact_id=?", new String[]{id+""}, null);
			while(dataCursor.moveToNext()){
				String data = dataCursor.getString(dataCursor.getColumnIndex("data1"));
				System.out.println("data==="+data);
				String mimetype= dataCursor.getString(dataCursor.getColumnIndex("mimetype"));
				System.out.println("mimetype==="+mimetype);
			}
			dataCursor.close();
			
					
		}
		
		cursor.close();
		
		
		
	}
}
