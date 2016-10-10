package com.itheima.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteOpenHelper extends SQLiteOpenHelper {

	public MySqliteOpenHelper(Context context) {
		
		//context: Ӧ�������� ���� 
		
		// name: ���ݿ������  
		//�α� ����  :  Cursor---cursorFactory
		// version: �汾, ���ݿ�İ汾 
		
		//��ʼ�汾 ������ ���� ���, ���� ���ݿ�İ汾ֻ�� ������, ���ܹ� ���� 
		super(context, "itheima.db", null, 3);
	}

	// ���� ���ݿ� �ļ���һ�� ������ʱ�� ��ִ�еķ��� 
	@Override
	public void onCreate(SQLiteDatabase db) {

		System.out.println("onCreate ... ��������  ...");
		
		//������ȥ �����ݿ� �д��� �� 
		db.execSQL("create table users( _id integer primary key autoincrement, username varchar(30), password varchar(40) )");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("onUpgrade ... ��������  ...");

		db.execSQL("alter table users add city varchar(40)");
	}

}
