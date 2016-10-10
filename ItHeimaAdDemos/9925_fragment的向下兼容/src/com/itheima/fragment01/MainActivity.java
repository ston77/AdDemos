package com.itheima.fragment01;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * 为了要向下兼容，继承关系应该改成继承fragmentActivity
 *
 */
public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void show(View v) {
		// 1.得到fragment的管理者
		FragmentManager manager = getSupportFragmentManager();

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
		FragmentManager manager =  getSupportFragmentManager();

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
		FragmentManager manager = getSupportFragmentManager();

		// 2.打开事务
		FragmentTransaction tr = manager.beginTransaction();
		NetFragment fragment = new NetFragment();

		// 3.让指定的fragment显示到指定的容器里面去。
		tr.replace(R.id.container, fragment);

		// 4.提交事务
		tr.commit();
	}
}
