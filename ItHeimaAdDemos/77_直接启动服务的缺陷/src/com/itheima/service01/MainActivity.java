package com.itheima.service01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void start(View v) {
		
		Intent service = new Intent(this,ServiceDemo.class);
		//由于启动服务返回的不是对象，而是组件的名字(包名， 类的全路径地址)
		//所以就没有办法直接调用服务中的方法。
		startService(service);
	}

	public void call(View v) {
		//由于是自己创建的服务对象，没有经过系统加工，所以上下文包含的一些属性值就是null,
		ServiceDemo demo = new ServiceDemo();
		demo.methodInService();
	}

	public void stop(View v) {
		Intent service = new Intent(this,ServiceDemo.class);
		stopService(service);
	}
}
