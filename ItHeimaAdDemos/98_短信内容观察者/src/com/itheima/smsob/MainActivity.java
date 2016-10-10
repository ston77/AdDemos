package com.itheima.smsob;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.database.ContentObserver;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//����uri·��
		Uri uri = Uri.parse("content://sms");
		//ע��һ�����ݹ۲��ߣ��۲�������ݿ��Ƿ����˸ı�
		getContentResolver().registerContentObserver(uri, true, new MyObserver(new Handler()));
	}

	
	class MyObserver extends ContentObserver{

		public MyObserver(Handler handler) {
			super(handler);
		}
		
		
		@Override
		public void onChange(boolean selfChange, Uri uri) {
			super.onChange(selfChange, uri);
			//uri���������²����ļ�¼��Ӧ��uri���������uri����ȥ��ѯ��ָ���ļ�¼����
			//ʹ�����ݹ۲��ߵ��ַ���ȥ���Ż�����������
			System.out.println("����һ�����ţ�"+uri);
		}
		
	}

}
