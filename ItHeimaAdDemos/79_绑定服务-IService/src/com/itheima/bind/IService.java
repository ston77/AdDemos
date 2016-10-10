package com.itheima.bind;

/**
 * 这是中国人民大学对外暴露的接口，供广大高考落榜生使用，他们只知道服务中有这么一个方法，但是到底是哪个一具体的类
 * 去实现了这个方法，他们不知道，这么做的好处就是把内部的实现给隐藏起来了。
 */
public interface IService {
	
	/**
	 * 内部方法
	 * @param name
	 * @param money
	 */
	public void callMethodInService(String name , int money);
}
