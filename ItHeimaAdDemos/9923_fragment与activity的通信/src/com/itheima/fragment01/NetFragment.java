package com.itheima.fragment01;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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
		View view = inflater.inflate(R.layout.net, null);
		view.findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText et = (EditText) getActivity().findViewById(R.id.et);
				Toast.makeText(getActivity(), "��ť������ˡ�����"+et.getText().toString(), 0).show();
			}
		});
		
		return view ; 
	}
	
/*	
	public void btn(View v){
		Toast.makeText(getActivity(), "��ť������ˡ�����", 0).show();
		
	}*/
}
