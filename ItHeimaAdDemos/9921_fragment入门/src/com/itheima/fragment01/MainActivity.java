package com.itheima.fragment01;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void show(View v) {
		// 1.得到fragment的管理者
		FragmentManager manager = getFragmentManager();

		// 2.打开事务
		FragmentTransaction tr = manager.beginTransaction();
		ShowFragment fragment = new ShowFragment();

		// 3.让指定的fragment显示到指定的容器里面去。
		tr.replace(R.id.container, fragment);

		// 4.提交事务
		tr.commit();

	}

	public void save(View v) {
		// 1.得到fragment的管理者
		FragmentManager manager = getFragmentManager();

		// 2.打开事务
		FragmentTransaction tr = manager.beginTransaction();
		SaveFragment fragment = new SaveFragment();

		// 3.让指定的fragment显示到指定的容器里面去。
		tr.replace(R.id.container, fragment);

		// 4.提交事务
		tr.commit();
	}

	public void net(View v) {

		// 1.得到fragment的管理者
		FragmentManager manager = getFragmentManager();

		// 2.打开事务
		FragmentTransaction tr = manager.beginTransaction();
		NetFragment fragment = new NetFragment();

		// 3.让指定的fragment显示到指定的容器里面去。
		tr.replace(R.id.container, fragment);

		// 4.提交事务
		tr.commit();
	}
}
