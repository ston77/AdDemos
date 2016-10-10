package com.itheima.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	CheckBox cbx;
	SeekBar sb;
	
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ʼ���ؼ� 
		cbx = (CheckBox) findViewById(R.id.cb);
		sb = (SeekBar) findViewById(R.id.sb);
		
		sp = getSharedPreferences("config", 0);
		
		//���� sp �е�ֵ 
		boolean isChecked = sp.getBoolean("isChecked", false);
		cbx.setChecked(isChecked);
		
		cbx.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				//�� checkbox��״̬ ���� �仯��ʱ��, ��������ͻᱻ �ص� 
				//ʹ��sharedPreferences ��������
				Editor edit = sp.edit();
				edit.putBoolean("isChecked", isChecked);
				edit.apply();
//				edit.commit();
			}
		});
		
		int progress = sp.getInt("progress", 0);
		sb.setProgress(progress);
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			//ֹͣ �϶� ʱ ������ 
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
				int progress = seekBar.getProgress();
				
				Editor edit = sp.edit();
				edit.putInt("progress", progress);
				edit.apply();
				
			}
			
			//��ʼ �϶� ʱ ������ 
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			// ���� �����仯ʱ ���� 
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
			}
		});
		
	}


}
