package com.itheima.bind;

import java.io.FileDescriptor;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.WindowManager;
import android.widget.Toast;

public class ServcieDemo extends Service {

	/**
	 * 内部招生老师，内部代理对象
	 */
	 class MyBinder extends Binder {
		/**
		 * 内部人员中固有的方法，它的作用就让别人来访问它，然后它自己去访问服务中的方法。 通过迂回的手段达到从外部类调用服务中的方法效果。
		 * 
		 * @param name
		 * @param money
		 */
		public void callMethodInService(String name, int money) {
			methodInService(name, money);
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
