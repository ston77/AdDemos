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
	 * 获取所有的联系人数据
	 * @param context 上下文
	 * @return 返回一个list集合<ContactInfo>
	 */
	public static List<ContactInfo> readContacts(Context context){
		//定义一个容器，用于存储联系人数据
		List<ContactInfo> list = new ArrayList<ContactInfo>();
		
		//1.获取到内容解析者对象
		ContentResolver resolver = context .getContentResolver();
		
		//2定义查询raw_contact表的uri
		Uri contactUri = Uri.parse("content://com.android.contacts/raw_contacts");
		//查询data表的uri
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		//3.查询联系人的ID
		Cursor cursor = resolver.query(contactUri, new String[]{"contact_id" }, null, null, null);
		while(cursor.moveToNext()){
			//执行这一行代码，仅仅只查询到了联系人的ID，但是如果还想获取到联系人的其他信息，比如：姓名、电话、邮箱，
			//那么久应该把id放到其他表中去查询了， data
			String id= cursor.getString(cursor.getColumnIndex("contact_id"));
			System.out.println("id==="+id);
			if(id ==null){
				continue ;//跳过当前循环
			}
			
			//创建一个联系人对象
			ContactInfo info = new ContactInfo();
			
			//根据联系人的ID，去data表查询数据，把数据这个联系人的数据查询出来。
			/**
			 * 在查询联系人的数据类型的时候，系统并不是真的去查询data表，而是去查询了view_data表，
			 * 所以， 指定的miemtype,不能写成mimetype_id .并且返回的类型应该是String
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
			
			//循环完一个联系人，就应该封装到集合中。
			list.add(info);
		}
		cursor.close();
		
		return list;
		
	}

}
