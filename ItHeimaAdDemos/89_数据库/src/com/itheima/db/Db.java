package com.itheima.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Db extends SQLiteOpenHelper {

	public Db(Context context) {
		super(context, "bank.db", null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table account (_id integer primary key autoincrement ," +
				" name varchar , money integer)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
