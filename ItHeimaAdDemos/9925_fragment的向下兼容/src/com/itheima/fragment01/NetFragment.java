package com.itheima.fragment01;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ����������ص���Ƭ��һ��Ҫ��ʾ����������ȥ
 *
 */
public class NetFragment extends Fragment{

	//�������Ƶ�ǰ����Ƭ��ʾ�������ݣ��е�����activity����onCreate����
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//View.inflate(context, resource, root)
		//����һ��view����ʵ����ָ���õ�ǰ����Ƭ����ʾ�������
		return inflater.inflate(R.layout.net, null);
	}
}
