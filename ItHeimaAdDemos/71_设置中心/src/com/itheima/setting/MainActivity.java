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
		
		//初始化控件 
		cbx = (CheckBox) findViewById(R.id.cb);
		sb = (SeekBar) findViewById(R.id.sb);
		
		sp = getSharedPreferences("config", 0);
		
		//回显 sp 中的值 
		boolean isChecked = sp.getBoolean("isChecked", false);
		cbx.setChecked(isChecked);
		
		cbx.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				//当 checkbox的状态 发生 变化的时候, 这个方法就会被 回调 
				//使用sharedPreferences 保存起来
				Editor edit = sp.edit();
				edit.putBoolean("isChecked", isChecked);
				edit.apply();
//				edit.commit();
			}
		});
		
		int progress = sp.getInt("progress", 0);
		sb.setProgress(progress);
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			//停止 拖动 时 被调用 
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
				int progress = seekBar.getProgress();
				
				Editor edit = sp.edit();
				edit.putInt("progress", progress);
				edit.apply();
				
			}
			
			//开始 拖动 时 被调用 
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			// 进度 发生变化时 调用 
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
			}
		});
		
	}


}
