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


    //����02����
    public void start(View v){
    	
    	//�������� -----startActivity
    	
    	//����һ����ͼ��ָ����ת����һ������
    	Intent intent =new Intent(this ,SecondActivity.class);
    	
    	
    	//����ͼ����ָ���Ľ���
    	startActivity(intent);
    	
    }
    
}
