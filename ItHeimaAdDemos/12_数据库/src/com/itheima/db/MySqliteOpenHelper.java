package com.itheima.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteOpenHelper extends SQLiteOpenHelper {

	public MySqliteOpenHelper(Context context) {
		
		//context: 应用上下文 对象 
		
		// name: 数据库的名称  
		//游标 工厂  :  Cursor---cursorFactory
		// version: 版本, 数据库的版本 
		
		//初始版本 必须是 大于 零的, 并且 数据库的版本只能 够升级, 不能够 降级 
		super(context, "itheima.db", null, 3);
	}

	// 就是 数据库 文件第一次 创建的时候 会执行的方法 
	@Override
	public void onCreate(SQLiteDatabase db) {

		System.out.println("onCreate ... 被调用了  ...");
		
		//在这里去 在数据库 中创建 表 
		db.execSQL("create table users( _id integer primary key autoincrement, username varchar(30), password varchar(40) )");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("onUpgrade ... 被调用了  ...");

		db.execSQL("alter table users add city varchar(40)");
	}

}
