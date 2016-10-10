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
	 * �ڲ�������ʦ���ڲ��������
	 * ��Ϊ���Լ����ڲ���Ա˽���ˣ����Զ��Ⱪ¶��һ�����ܽӿڣ����Լ����ڲ���ȥʵ��
	 * ������ܽӿڡ�
	 */
	private class MyBinder extends Binder implements IService{
		/**
		 * �ڲ���Ա�й��еķ������������þ��ñ�������������Ȼ�����Լ�ȥ���ʷ����еķ����� ͨ���ػص��ֶδﵽ���ⲿ����÷����еķ���Ч����
		 * 
		 * @param name
		 * @param money
		 */
		public void callMethodInService(String name, int money) {
			methodInService(name, money);
		}

		public void ˶ʿ����(String name, int money) {
			if (money <= 100000) {
				Toast.makeText(ServcieDemo.this, name + ",���Ǯ����.", 0).show();
			} else {
				Toast.makeText(ServcieDemo.this, name + "����,�����й������ѧ˶ʿ¼ȡ֪ͨ���Ѿ�������..", 0)
						.show();
			}
		}

		public void ��ʿ����(String name, int money) {
			if (money <= 100000) {
				Toast.makeText(ServcieDemo.this, name + ",���Ǯ����.", 0).show();
			} else {
				Toast.makeText(ServcieDemo.this, name + "����,�����й������ѧ��ʿ¼ȡ֪ͨ���Ѿ�������..", 0)
						.show();
			}
		}
	}

	/**
	 * �������ɹ������ˣ���ô�ͷ���һ��ͨѶƵ���� ����һ���ڲ���Ա���ڲ��������
	 */
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind");
		// �����ڲ��������
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
	 * ¼ȡ֪ͨ�� �����е��ڲ�����
	 */
	public void methodInService(String name, int money) {
		if (money <= 100000) {
			Toast.makeText(this, name + ",���Ǯ����.", 0).show();
		} else {
			Toast.makeText(this, name + "����,�����й������ѧ����¼ȡ֪ͨ���Ѿ�������.2222.", 0).show();
		}
	}
}
