package com.itheima.studentsystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDbOpenHelper extends SQLiteOpenHelper {

	public StudentDbOpenHelper(Context context) {
		super(context, "student.db", null, 3);
	}

	
	// sqlite数据库 最终在 存储数据时, 实际上都是  字符串. 
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("create table students (_id integer primary key autoincrement, name varchar(30), sex varchar(10))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
