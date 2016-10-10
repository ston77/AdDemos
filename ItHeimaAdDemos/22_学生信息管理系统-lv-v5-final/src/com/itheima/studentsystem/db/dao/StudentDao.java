package com.itheima.studentsystem.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.studentsystem.db.StudentDbOpenHelper;
import com.itheima.studentsystem.domain.Student;

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
//		db.execSQL("insert into students values(null,?,?)", new Object[]{name,sex});
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("sex", sex);
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
		if(count==0){
			return false;
		}else{
			return true;
		}
		
	}
	public void update(String name, String sex){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		// update students set sex=? where name=?;
//		db.execSQL("update students set sex=? where name=?", new Object[]{sex,name});
		ContentValues values = new ContentValues();
		values.put("sex", sex);
		db.update("students", values, "name=?", new String[]{name});
		
		db.close();
	}
	public String findOne(String name){
		
		//获得一个可读的数据库 
		SQLiteDatabase db = helper.getReadableDatabase();
		
//		db.execSQL("select * from students where name=?", new Object[]{name});
		
		//执行 查询 语句 
//		Cursor cursor = db.rawQuery("select sex from students where name=?",new String[]{name});
		Cursor cursor = db.query("students", new String[]{"sex"}, "name=?", new String[]{name}, null, null, null);
		
		String sex = null;
		if(cursor.moveToNext()){
			sex = cursor.getString(0);
		}
		
		cursor.close();
		db.close();
		return sex;
	}


	//查询 返回所有的学生的信息
	public List<Student> getAll() {
		
		//查询 所有的学生的信息
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.query("students", new String[]{"_id","name","sex"}, null,null, null, null, null);
		
		List<Student> list = new ArrayList<Student>();
				
		Student student = null;
		while(cursor.moveToNext()){
			
			int id= cursor.getInt(0);
			String name = cursor.getString(1);
			String sex = cursor.getString(2);
			
			student = new Student(id, name, sex);
			
			list.add(student);
		}
		
		//释放资源 
		cursor.close();
		db.close();
		
		return list;
	}
}
