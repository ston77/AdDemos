package com.itheima.querycontacts.utils;

import java.util.ArrayList;
import java.util.List;

import com.itheima.querycontacts.domain.ContactInfo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;

public class ContactsUtil {
	
	/**
	 * ��ȡ���е���ϵ������
	 * @param context ������
	 * @return ����һ��list����<ContactInfo>
	 */
	public static List<ContactInfo> readContacts(Context context){
		//����һ�����������ڴ洢��ϵ������
		List<ContactInfo> list = new ArrayList<ContactInfo>();
		
		//1.��ȡ�����ݽ����߶���
		ContentResolver resolver = context .getContentResolver();
		
		//2�����ѯraw_contact���uri
		Uri contactUri = Uri.parse("content://com.android.contacts/raw_contacts");
		//��ѯdata���uri
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		//3.��ѯ��ϵ�˵�ID
		Cursor cursor = resolver.query(contactUri, new String[]{"contact_id" }, null, null, null);
		while(cursor.moveToNext()){
			//ִ����һ�д��룬����ֻ��ѯ������ϵ�˵�ID��������������ȡ����ϵ�˵�������Ϣ�����磺�������绰�����䣬
			//��ô��Ӧ�ð�id�ŵ���������ȥ��ѯ�ˣ� data
			String id= cursor.getString(cursor.getColumnIndex("contact_id"));
			System.out.println("id==="+id);
			if(id ==null){
				continue ;//������ǰѭ��
			}
			
			//����һ����ϵ�˶���
			ContactInfo info = new ContactInfo();
			
			//������ϵ�˵�ID��ȥdata���ѯ���ݣ������������ϵ�˵����ݲ�ѯ������
			/**
			 * �ڲ�ѯ��ϵ�˵��������͵�ʱ��ϵͳ���������ȥ��ѯdata������ȥ��ѯ��view_data��
			 * ���ԣ� ָ����miemtype,����д��mimetype_id .���ҷ��ص�����Ӧ����String
			 */
			Cursor dataCursor = resolver.query(dataUri, new String[]{"data1", "mimetype"}, "raw_contact_id=?", new String[]{id}, null);
			while(dataCursor.moveToNext()){
				String data = dataCursor.getString(dataCursor.getColumnIndex("data1"));
				System.out.println("data==="+data);
				String mimetype= dataCursor.getString(dataCursor.getColumnIndex("mimetype"));
				System.out.println("mimetype==="+mimetype);
				
				if(mimetype.equals("vnd.android.cursor.item/name")){
					info.setName(data);
				}else if(mimetype.equals("vnd.android.cursor.item/phone_v2")){
					info.setNumber(data);
				}else if(mimetype.equals("vnd.android.cursor.item/email_v2")){
					info.setEmail(data);
				}else if(mimetype.equals("vnd.android.cursor.item/im")){
					info.setIm(data);
				}
			}
			dataCursor.close();
			
			//ѭ����һ����ϵ�ˣ���Ӧ�÷�װ�������С�
			list.add(info);
		}
		cursor.close();
		
		return list;
		
	}

}
