package com.itheima.click;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/*
 * 
 *   ����¼���д�� :
 *   	1.  ֱ��д onclick 
 *   	2.  д�����ڲ���    
 *   	3.  �� activityȥʵ�� onclickListener�ӿ�
 *   	4.  �ڲ��� 
 * 	
 * 
 * 		�ȸ� ����� 2,3,4 �� д��. 
 */


//������д��:����ֱ��ȥ��ʵ�֡�
public class MainActivity extends Activity implements View.OnClickListener{

	
	Button btn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        //��ʼ����ť�Ķ��� 
		btn = (Button) findViewById(R.id.bt2);
        
		/*btn.setOnClickListener(new OnClickListener() {
			 
			 // �����ڲ��� 
			@Override
			public void onClick(View v) {
				System.out.println("��ť��   �ڶ�  ��д��..");
				
			}
		});*/
		
		btn.setOnClickListener(new MyOnClickListener());
    }

    
    //������ ��ʽ, �ڲ��� 
    private class MyOnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    

    // ��һ��д��: 
    public void anniuyi(View v){
    
    	
    	System.out.println("��ťһ ��һ��д��..");
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
