package com.itheima.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	//普通对话框
	public void create01(View v){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("确认删除");
		builder.setMessage("亲, 您确定要删除吗? 不能返回的");
		
		//设置点击确认的要干的事儿 
		builder.setPositiveButton("确认", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Toast.makeText(MainActivity.this, "您点击了确认", 0).show();
				
			}
		});
		
		//设置点击取消 的要干的事儿 
		builder.setNegativeButton("取消", null);
		
//		AlertDialog dialog = builder.create();
//		dialog.show();
		
		// 这样才可以 显示  对话框 
		builder.show();
		
	}
	
	//单选对话框
	public void create02(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final String[] items ={"玩毛线","弹棉花","打dota","夜生活"};
		builder.setSingleChoiceItems(items, -1, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Toast.makeText(MainActivity.this, items[which]+"被点中了  ... 是 第 : " + which +"个", 0).show();
			}
		});
		
		//显示对话框 
		builder.show();
	}
	
	String[] items = {"深圳","广州","惠州","佛山","中山"};
	boolean[] checkedItems ={true,true,false,false,false};
	//多选对话框
	public void create03(View v){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				
				Toast.makeText(MainActivity.this, "选中了  :" +items[which]+", 状态是  : " + isChecked , 0).show();
				checkedItems[which]=isChecked;
			}
		});
		
		builder.show();
		
	}
	
	//无实时进度的对话框
	public void create04(View v){
		
		final ProgressDialog dialog = ProgressDialog.show(this, null, "姐们,正在拼命 加载中....");
		
		//让 在子线程中睡    3 秒钟 
		
		new Thread(){
			
			public void run() {
				
				SystemClock.sleep(3000);
				
				dialog.dismiss();
				
			};
			
		}.start();
		
	}
	
	//有实时进度的对话框
	public void create05(View v){
		
	}
	
	
}
