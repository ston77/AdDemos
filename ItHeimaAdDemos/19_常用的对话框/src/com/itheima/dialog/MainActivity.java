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

	//��ͨ�Ի���
	public void create01(View v){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("ȷ��ɾ��");
		builder.setMessage("��, ��ȷ��Ҫɾ����? ���ܷ��ص�");
		
		//���õ��ȷ�ϵ�Ҫ�ɵ��¶� 
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Toast.makeText(MainActivity.this, "�������ȷ��", 0).show();
				
			}
		});
		
		//���õ��ȡ�� ��Ҫ�ɵ��¶� 
		builder.setNegativeButton("ȡ��", null);
		
//		AlertDialog dialog = builder.create();
//		dialog.show();
		
		// �����ſ��� ��ʾ  �Ի��� 
		builder.show();
		
	}
	
	//��ѡ�Ի���
	public void create02(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final String[] items ={"��ë��","���޻�","��dota","ҹ����"};
		builder.setSingleChoiceItems(items, -1, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Toast.makeText(MainActivity.this, items[which]+"��������  ... �� �� : " + which +"��", 0).show();
			}
		});
		
		//��ʾ�Ի��� 
		builder.show();
	}
	
	String[] items = {"����","����","����","��ɽ","��ɽ"};
	boolean[] checkedItems ={true,true,false,false,false};
	//��ѡ�Ի���
	public void create03(View v){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				
				Toast.makeText(MainActivity.this, "ѡ����  :" +items[which]+", ״̬��  : " + isChecked , 0).show();
				checkedItems[which]=isChecked;
			}
		});
		
		builder.show();
		
	}
	
	//��ʵʱ���ȵĶԻ���
	public void create04(View v){
		
		final ProgressDialog dialog = ProgressDialog.show(this, null, "����,����ƴ�� ������....");
		
		//�� �����߳���˯    3 ���� 
		
		new Thread(){
			
			public void run() {
				
				SystemClock.sleep(3000);
				
				dialog.dismiss();
				
			};
			
		}.start();
		
	}
	
	//��ʵʱ���ȵĶԻ���
	public void create05(View v){
		
	}
	
	
}
