package com.itheima.multiui;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //启动02界面
    public void start(View v){
    	
    	//启动界面 -----startActivity
    	
    	//定义一个意图，指定跳转到哪一个界面
    	Intent intent =new Intent(this ,SecondActivity.class);
    	
    	
    	//打开意图里面指定的界面
    	startActivity(intent);
    	
    }
    
}
