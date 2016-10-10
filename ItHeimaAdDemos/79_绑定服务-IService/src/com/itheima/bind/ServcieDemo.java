package com.itheima.bind;

import java.io.FileDescriptor;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.widget.Toast;

public class ServcieDemo extends Service {

	/**
	 * 内部招生老师，内部代理对象
	 * 因为把自己的内部人员私有了，所以对外暴露有一个功能接口，让自己的内部类去实现
	 * 这个功能接口。
	 */
	private class MyBinder extends Binder implements IService{
		/**
		 * 内部人员中固有的方法，它的作用就让别人来访问它，然后它自己去访问服务中的方法。 通过迂回的手段达到从外部类调用服务中的方法效果。
		 * 
		 * @param name
		 * @param money
		 */
		public void callMethodInService(String name, int money) {
			methodInService(name, money);
		}

		public void 硕士招生(String name, int money) {
			if (money <= 100000) {
				Toast.makeText(ServcieDemo.this, name + ",你的钱不够.", 0).show();
			} else {
				Toast.makeText(ServcieDemo.this, name + "先生,您的中国人民大学硕士录取通知书已经办妥了..", 0)
						.show();
			}
		}

		public void 博士招生(String name, int money) {
			if (money <= 100000) {
				Toast.makeText(ServcieDemo.this, name + ",你的钱不够.", 0).show();
			} else {
				Toast.makeText(ServcieDemo.this, name + "先生,您的中国人民大学博士录取通知书已经办妥了..", 0)
						.show();
			}
		}
	}

	/**
	 * 如果服务成功绑定上了，那么就返回一个通讯频道， 返回一个内部人员，内部代理对象
	 */
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind");
		// 返回内部代理对象
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		super.onCreate();

		System.out.println("onCreate---");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy---");
	}

	/**
	 * 录取通知书 服务中的内部方法
	 */
	public void methodInService(String name, int money) {
		if (money <= 100000) {
			Toast.makeText(this, name + ",你的钱不够.", 0).show();
		} else {
			Toast.makeText(this, name + "先生,您的中国人民大学本科录取通知书已经办妥了.2222.", 0).show();
		}
	}
}
