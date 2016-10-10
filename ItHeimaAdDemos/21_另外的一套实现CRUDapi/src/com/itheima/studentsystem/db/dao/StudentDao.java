package com.itheima.studentsystem.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.studentsystem.db.StudentDbOpenHelper;
/*
 * 
 *   ʹ�� �ȸ� ����ʦ �ṩ������һ�� api ʵ�� CRUD���� 
 * 
 */
public class StudentDao {
	
	
	StudentDbOpenHelper helper;
	
	public StudentDao(Context context){
		helper = new StudentDbOpenHelper(context);
	}
	
	
	//����� ��ɾ�� ����, ��ô �� getWritableDatabase .,��ʾ ���һ�� ��д�����ݿ� 
	public boolean insert(String name, String sex){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		// insert into students values();
		
		//ִ�� sql ��� 
		db.execSQL("insert into students values(null,?,?)", new Object[]{name,sex});
		
		// table: ����Ҫ�����ı� 
		// nullColumnHack: ��������ʱ, ��Щ ��Ϊ�� 
		//values : ����� �ֶ����� ��Ӧ��ֵ 
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("sex", sex);
		
		//��Դ��, ������  ͨ�� ƴ�� sql ��� ��ʵ�ֵ� .
		
		long count = db.insert("students", null, values);
		//�ͷ���Դ 
		db.close();
		
		if(count==-1){
			return false;
		}else{
			return true;
		}
		
	}
	public boolean delete(String name){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		//delete from student where name=?
//		db.execSQL("delete from students where name=?",new Object[]{name});	
		
		int count = db.delete("students", "name=?", new String[]{name});
		
		db.close();
		if(count>0){
			return true;
		}else{
			return false;
		}
		
	}
	public boolean update(String name, String sex){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		// update students set sex=? where name=?;
//		db.execSQL("update students set sex=? where name=?", new Object[]{sex,name});
		ContentValues values = new ContentValues();
		values.put("sex", sex);
		int count = db.update("students", values, "name=?", new String[]{name});
		db.close();
		
		if(count<=0){
			return false;
		}else{
			return true;
		}
		
	}
	public String findOne(String name){
		
		//���һ���ɶ������ݿ� 
		SQLiteDatabase db = helper.getReadableDatabase();
		
//		db.execSQL("select * from students where name=?", new Object[]{name});
		
		//ִ�� ��ѯ ��� 
//		Cursor cursor = db.rawQuery("select sex,id from students where name=?",new String[]{name});
		
		// s...f...w...g..h..o
		Cursor cursor = db.query("students", new String[]{"sex"}, "name=?", new String[]{name}, null, null, null);
		
		String sex = null;
		if(cursor.moveToNext()){
			sex = cursor.getString(0);
		}
		
		return sex;
	}
}
