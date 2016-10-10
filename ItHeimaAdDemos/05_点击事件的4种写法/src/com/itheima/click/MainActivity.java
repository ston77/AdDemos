package com.itheima.click;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/*
 * 
 *   点击事件的写法 :
 *   	1.  直接写 onclick 
 *   	2.  写匿名内部类    
 *   	3.  让 activity去实现 onclickListener接口
 *   	4.  内部类 
 * 	
 * 
 * 		谷歌 建议的 2,3,4 种 写法. 
 */


//第三种写法:让类直接去　实现　
public class MainActivity extends Activity implements View.OnClickListener{

	
	Button btn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        //初始化按钮的动作 
		btn = (Button) findViewById(R.id.bt2);
        
		/*btn.setOnClickListener(new OnClickListener() {
			 
			 // 匿名内部类 
			@Override
			public void onClick(View v) {
				System.out.println("按钮二   第二  种写法..");
				
			}
		});*/
		
		btn.setOnClickListener(new MyOnClickListener());
    }

    
    //第四种 方式, 内部类 
    private class MyOnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    

    // 第一种写法: 
    public void anniuyi(View v){
    
    	
    	System.out.println("按钮一 第一种写法..");
    }


	@Override
	public void onClick(View v) {
		
		int id = v.getId();
		switch (id) {
		case R.id.bt2:
			
			break;

		default:
			break;
		}
	}
    
}
