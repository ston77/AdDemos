package com.itheima.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class Backdoor extends ContentProvider {

	/**
	 * ����Ӧ�ó���һ��װ���ͻ�ѳ����е������ṩ�߿����������Ϊ��������ݵİ�ȫ�ԣ� ���Ա�������õ�uri���������һЩƥ��������һ�¡�
	 */

	// ����һ��uri��ƥ������ָ������������ݹ�����uri��һ��û��ƥ�䣬�����ص�intֵ -1
	static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

	static {
		// һ��ʼ�͸����ƥ����Ԥ��һЩƥ�����������ݹ�����uri
		// ƥ����authority �� path ����ô�����غ����ƥ����
		//���ݿ������п����ж��ű�Ϊ�˹淶ƥ�����pathһ�㶼д���������ݱ���������ж�
		//��ǰ���ʵ����ĸ�������� ������ж��ű����һ������Щ���б���¶��������ô���ƥ�����
		//��Ӧ��д��������ˡ�����ע�ⷵ�ص�code����һ����
		matcher.addURI("com.itheima.db.BANK", "account", 200);
//		matcher.addURI("com.itheima.db.BANK", "stu", 201);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// ʹ��uriƥ��ȥȥ���˴��ݹ�����uri������ƥ��ɹ���������200�����򽫷��� -1
		int code = matcher.match(uri);
		if (code == 200) {
			System.out.println("delete---");
			Db db = new Db(getContext()); 
			SQLiteDatabase data = db.getWritableDatabase();
			data.delete("account", selection, selectionArgs);
			
			// ���ⷢ��һ��֪ͨ�����������ˣ����uri��Ӧ�������Ѿ������˸ı䡣����ڶ�����������null,
			//��ô�ڴ�ָ����������ݹ۲��߽����յ����֪ͨ�������null,������ǲ���ֱ�ӱ����ĳһ�����ݹ۲��ߣ�
			//�����Ƿ�����һ��֪ͨ������й������֪ͨ����ô����֪���¼��ķ���
			getContext().getContentResolver().notifyChange(uri, null);
		} else {
			throw new IllegalArgumentException("������󣬹�����~~");
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

		// ʹ��uriƥ��ȥȥ���˴��ݹ�����uri������ƥ��ɹ���������200�����򽫷��� -1
		int code = matcher.match(uri);
		if (code == 200) {
			Db db = new Db(getContext()); 
			SQLiteDatabase data = db.getWritableDatabase();
			data.insert("account", null, values);
			System.out.println("insert---");
			// ���ⷢ��һ��֪ͨ�����������ˣ����uri��Ӧ�������Ѿ������˸ı䡣����ڶ�����������null,
			//��ô�ڴ�ָ����������ݹ۲��߽����յ����֪ͨ�������null,������ǲ���ֱ�ӱ����ĳһ�����ݹ۲��ߣ�
			//�����Ƿ�����һ��֪ͨ������й������֪ͨ����ô����֪���¼��ķ���
			getContext().getContentResolver().notifyChange(uri, null);
		} else {
			throw new IllegalArgumentException("������󣬹�����~~");
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
		// ʹ��uriƥ��ȥȥ���˴��ݹ�����uri������ƥ��ɹ���������200�����򽫷��� -1
		int code = matcher.match(uri);
		if (code == 200) {
			System.out.println("query---");
			Db db = new Db(getContext());
			SQLiteDatabase data = db.getReadableDatabase();
			return data.query("account", projection, selection, selectionArgs, null, null, sortOrder);
		} else {
			throw new IllegalArgumentException("������󣬹�����~~");
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub

		// ʹ��uriƥ��ȥȥ���˴��ݹ�����uri������ƥ��ɹ���������200�����򽫷��� -1
		int code = matcher.match(uri);
		if (code == 200) {
			Db db = new Db(getContext());
			SQLiteDatabase data = db.getWritableDatabase();
			data.update("account", values, selection, selectionArgs);
			// ���ⷢ��һ��֪ͨ�����������ˣ����uri��Ӧ�������Ѿ������˸ı䡣����ڶ�����������null,
			//��ô�ڴ�ָ����������ݹ۲��߽����յ����֪ͨ�������null,������ǲ���ֱ�ӱ����ĳһ�����ݹ۲��ߣ�
			//�����Ƿ�����һ��֪ͨ������й������֪ͨ����ô����֪���¼��ķ���
			getContext().getContentResolver().notifyChange(uri, null);
		} else {
			throw new IllegalArgumentException("������󣬹�����~~");
		}

		return 0;
	}

}
