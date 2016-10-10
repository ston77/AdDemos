package com.itheima.ob;

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
		
		Uri uri = Uri.parse("content://com.itheima.db.BANK/account");
		
		//ע��һ�����ݹ۲��ߣ������۲�ָ����uri�� ������uri�����ݷ����˸ı䣬��ô����õ�֪ͨ
		getContentResolver().registerContentObserver(
				uri, //�۲�ָ����uri
				true,  // �����true, ����ֻҪǰ���uri�ܹ�ƥ��ɹ�����ô�ͻ��յ�֪ͨ�������false�����ʱ��
						//Ҫ���е�·������ȫƥ��
				new ContentObserver(new Handler()) {
			
			//һ�����uri��Ӧ�����ݷ����˸ı䣬��ô����������ᱻ����.
			@Override
			public void onChange(boolean selfChange) {
				System.out.println("���˰��������г�����͵Ǯ�ˡ�����");
			}
		});
	}


}
