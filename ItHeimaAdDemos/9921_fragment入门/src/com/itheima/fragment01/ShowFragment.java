package com.itheima.fragment01;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 这是显示相关的碎片，一会要显示到容器里面去
 *
 */
public class ShowFragment extends Fragment{

	//用来控制当前的碎片显示生命内容，有点类似activity里面onCreate方法
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//View.inflate(context, resource, root)
		//返回一个view对象，实际上指，让当前的碎片，显示这个布局
		return inflater.inflate(R.layout.show, null);
	}
}
