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
}
