package com.itheima.fragment01;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ���Ǳ�����ص���Ƭ��һ��Ҫ��ʾ����������ȥ
 *
 */
public class SaveFragment extends Fragment{

	//�������Ƶ�ǰ����Ƭ��ʾ�������ݣ��е�����activity����onCreate����
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//View.inflate(context, resource, root)
		//����һ��view����ʵ����ָ���õ�ǰ����Ƭ����ʾ�������
		return inflater.inflate(R.layout.save, null);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("onCreate---");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		System.out.println("onStart---");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		System.out.println("onResume---");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		System.out.println("onPause---");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		System.out.println("onStop---");
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy---");
	}
	
	
}
