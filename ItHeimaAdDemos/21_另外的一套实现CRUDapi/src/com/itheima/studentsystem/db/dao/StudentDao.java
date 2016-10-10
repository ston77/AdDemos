package com.itheima.studentsystem.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.studentsystem.db.StudentDbOpenHelper;
/*
 * 
 *   使用 谷歌 工程师 提供的另外一套 api 实现 CRUD操作 
 * 
 */
public class StudentDao {
	
	
	StudentDbOpenHelper helper;
	
	public StudentDao(Context context){
		helper = new StudentDbOpenHelper(context);
	}
	
	
	//如果做 增删改 操作, 那么 就 getWritableDatabase .,表示 获得一个 可写的数据库 
	public boolean insert(String name, String sex){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		// insert into students values();
		
		//执行 sql 语句 
		db.execSQL("insert into students values(null,?,?)", new Object[]{name,sex});
		
		// table: 具体要操作的表 
		// nullColumnHack: 插入数据时, 哪些 列为空 
		//values : 具体的 字段名称 对应的值 
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("sex", sex);
		
		//翻源码, 发现是  通过 拼接 sql 语句 来实现的 .
		
		long count = db.insert("students", null, values);
		//释放资源 
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
		
		//获得一个可读的数据库 
		SQLiteDatabase db = helper.getReadableDatabase();
		
//		db.execSQL("select * from students where name=?", new Object[]{name});
		
		//执行 查询 语句 
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
