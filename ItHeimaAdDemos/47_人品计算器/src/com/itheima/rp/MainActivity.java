package com.itheima.rp;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        new Thread(){
        	public void run() {
        		
        		SystemClock.sleep(3000);
//        		sleep(3000);
        		Intent intent = new Intent(MainActivity.this , CalcActivity.class);
        		startActivity(intent);
        		//��ת����󣬹رյ�ǰ���档
        		finish();
        	};
        }.start();
        
    }
    
}
