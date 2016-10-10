package com.itheima.fragment01;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * Ϊ��Ҫ���¼��ݣ��̳й�ϵӦ�øĳɼ̳�fragmentActivity
 *
 */
public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void show(View v) {
		// 1.�õ�fragment�Ĺ�����
		FragmentManager manager = getSupportFragmentManager();

		// 2.������
		FragmentTransaction tr = manager.beginTransaction();
		ShowFragment fragment = new ShowFragment();

		// 3.��ָ����fragment��ʾ��ָ������������ȥ��
		tr.replace(R.id.container, fragment);

		// 4.�ύ����
		tr.commit();

	}

	public void save(View v) {
		// 1.�õ�fragment�Ĺ�����
		FragmentManager manager =  getSupportFragmentManager();

		// 2.������
		FragmentTransaction tr = manager.beginTransaction();
		SaveFragment fragment = new SaveFragment();

		// 3.��ָ����fragment��ʾ��ָ������������ȥ��
		tr.replace(R.id.container, fragment);

		// 4.�ύ����
		tr.commit();
	}

	public void net(View v) {

		// 1.�õ�fragment�Ĺ�����
		FragmentManager manager = getSupportFragmentManager();

		// 2.������
		FragmentTransaction tr = manager.beginTransaction();
		NetFragment fragment = new NetFragment();

		// 3.��ָ����fragment��ʾ��ָ������������ȥ��
		tr.replace(R.id.container, fragment);

		// 4.�ύ����
		tr.commit();
	}
}
