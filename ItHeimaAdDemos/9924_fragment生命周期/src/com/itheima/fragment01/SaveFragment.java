package com.itheima.fragment01;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 这是保存相关的碎片，一会要显示到容器里面去
 *
 */
public class SaveFragment extends Fragment{

	//用来控制当前的碎片显示生命内容，有点类似activity里面onCreate方法
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//View.inflate(context, resource, root)
		//返回一个view对象，实际上指，让当前的碎片，显示这个布局
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
