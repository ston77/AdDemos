package com.itheima.studentsystem.db.dao;

import java.util.ArrayList;
import java.util.List;

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
	public void insert(String name, String sex){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		// insert into students values();
		
		//执行 sql 语句 
		db.execSQL("insert into students values(null,?,?)", new Object[]{name,sex});
		
		//释放资源 
		db.close();
		
	}
	public void delete(String name){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		//delete from student where name=?
		db.execSQL("delete from students where name=?",new Object[]{name});	
		db.close();
	}
	public void update(String name, String sex){
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		// update students set sex=? where name=?;
		db.execSQL("update students set sex=? where name=?", new Object[]{sex,name});
		
		db.close();
	}
	public String findOne(String name){
		
		//获得一个可读的数据库 
		SQLiteDatabase db = helper.getReadableDatabase();
		
//		db.execSQL("select * from students where name=?", new Object[]{name});
		
		//执行 查询 语句 
		Cursor cursor = db.rawQuery("select sex from students where name=?",new String[]{name});
		
		String sex = null;
		if(cursor.moveToNext()){
			sex = cursor.getString(0);
		}
		
		return sex;
	}


	//查询 返回所有的学生的信息
	public List<Student> getAll() {
		
		//查询 所有的学生的信息
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery("select * from students", null);
		
		List<Student> list = new ArrayList<Student>();
				
		Student student = null;
		while(cursor.moveToNext()){
			
			int id= cursor.getInt(0);
			String name = cursor.getString(1);
			String sex = cursor.getString(2);
			
			student = new Student(id, name, sex);
			
			list.add(student);
		}
		
		return list;
	}
}
