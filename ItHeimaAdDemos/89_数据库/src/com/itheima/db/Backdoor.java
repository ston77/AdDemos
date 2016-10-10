package com.itheima.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class Backdoor extends ContentProvider {

	/**
	 * 由于应用程序一安装，就会把程序中的内容提供者口令发布出来，为了提高数据的安全性， 所以必须对来访的uri（口令），做一些匹配规则过滤一下。
	 */

	// 定义一个uri的匹配器，指定里面如果传递过来的uri，一旦没有匹配，将返回的int值 -1
	static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

	static {
		// 一开始就给这个匹配器预设一些匹配规则，如果传递过来的uri
		// 匹配了authority 和 path ，那么将返回后面的匹配码
		//数据库里面有可能有多张表，为了规范匹配规则，path一般都写表名，根据表的名字来判定
		//当前访问的是哪个表的数据 ，如果有多张表，并且还想把这些所有表都暴露出来，那么这个匹配规则
		//就应该写多条语句了。并且注意返回的code不能一样。
		matcher.addURI("com.itheima.db.BANK", "account", 200);
//		matcher.addURI("com.itheima.db.BANK", "stu", 201);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// 使用uri匹配去去过滤传递过来的uri口令，如果匹配成功，将返回200，否则将返回 -1
		int code = matcher.match(uri);
		if (code == 200) {
			System.out.println("delete---");
			Db db = new Db(getContext()); 
			SQLiteDatabase data = db.getWritableDatabase();
			data.delete("account", selection, selectionArgs);
			
			// 对外发布一个通知，告诉其他人，这个uri对应的数据已经发生了改变。如果第二个参数不是null,
			//那么在此指定的这个内容观察者将会收到这个通知，如果是null,代表的是不会直接报告给某一个内容观察者，
			//仅仅是发出来一个通知，如果有关心这个通知，那么久能知道事件的发生
			getContext().getContentResolver().notifyChange(uri, null);
		} else {
			throw new IllegalArgumentException("口令错误，滚犊子~~");
		}
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		// 使用uri匹配去去过滤传递过来的uri口令，如果匹配成功，将返回200，否则将返回 -1
		int code = matcher.match(uri);
		if (code == 200) {
			Db db = new Db(getContext()); 
			SQLiteDatabase data = db.getWritableDatabase();
			data.insert("account", null, values);
			System.out.println("insert---");
			// 对外发布一个通知，告诉其他人，这个uri对应的数据已经发生了改变。如果第二个参数不是null,
			//那么在此指定的这个内容观察者将会收到这个通知，如果是null,代表的是不会直接报告给某一个内容观察者，
			//仅仅是发出来一个通知，如果有关心这个通知，那么久能知道事件的发生
			getContext().getContentResolver().notifyChange(uri, null);
		} else {
			throw new IllegalArgumentException("口令错误，滚犊子~~");
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		// 使用uri匹配去去过滤传递过来的uri口令，如果匹配成功，将返回200，否则将返回 -1
		int code = matcher.match(uri);
		if (code == 200) {
			System.out.println("query---");
			Db db = new Db(getContext());
			SQLiteDatabase data = db.getReadableDatabase();
			return data.query("account", projection, selection, selectionArgs, null, null, sortOrder);
		} else {
			throw new IllegalArgumentException("口令错误，滚犊子~~");
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub

		// 使用uri匹配去去过滤传递过来的uri口令，如果匹配成功，将返回200，否则将返回 -1
		int code = matcher.match(uri);
		if (code == 200) {
			Db db = new Db(getContext());
			SQLiteDatabase data = db.getWritableDatabase();
			data.update("account", values, selection, selectionArgs);
			// 对外发布一个通知，告诉其他人，这个uri对应的数据已经发生了改变。如果第二个参数不是null,
			//那么在此指定的这个内容观察者将会收到这个通知，如果是null,代表的是不会直接报告给某一个内容观察者，
			//仅仅是发出来一个通知，如果有关心这个通知，那么久能知道事件的发生
			getContext().getContentResolver().notifyChange(uri, null);
		} else {
			throw new IllegalArgumentException("口令错误，滚犊子~~");
		}

		return 0;
	}

}
