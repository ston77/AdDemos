package com.itheima.fragment01;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	FragmentManager manager;

	FragmentTransaction tr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 1.得到fragment的管理者
		manager = getFragmentManager();

		// 2.打开事务 事务只能打开一次，提交一次，不能重复提交
		tr = manager.beginTransaction();

		ShowFragment fragment = new ShowFragment();

		// 3.让指定的fragment显示到指定的容器里面去。
		tr.replace(R.id.container, fragment);

		// 4.提交事务
		tr.commit();

	}

	public void show(View v) {
		// 2.打开事务 事务只能打开一次，提交一次，不能重复提交
		tr = manager.beginTransaction();

		ShowFragment fragment = new ShowFragment();

		// 3.让指定的fragment显示到指定的容器里面去。
		tr.replace(R.id.container, fragment);

		// 4.提交事务
		tr.commit();

	}

	public void save(View v) {
		/*
		 * // 1.得到fragment的管理者 FragmentManager manager = getFragmentManager();
		 */

		// 2.打开事务
		FragmentTransaction tr = manager.beginTransaction();
		SaveFragment fragment = new SaveFragment();

		// 3.让指定的fragment显示到指定的容器里面去。
		tr.replace(R.id.container, fragment);

		// 4.提交事务
		tr.commit();
	}

	public void net(View v) {

		/*
		 * // 1.得到fragment的管理者 FragmentManager manager = getFragmentManager();
		 */

		// 2.打开事务
		FragmentTransaction tr = manager.beginTransaction();
		NetFragment fragment = new NetFragment();

		// 3.让指定的fragment显示到指定的容器里面去。
		tr.replace(R.id.container, fragment);

		// 4.提交事务
		tr.commit();
	}
}
