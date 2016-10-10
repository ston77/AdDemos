package com.itheima.sensor;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView tv = (TextView)findViewById(R.id.tv);
		
		//1.得到传感器管理者
		SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
		
		//2.得到光线传感器对象
		Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		//3.监听一个传感器的数据。
		manager.registerListener(new SensorEventListener() {
			
			//数据发生了改变
			@Override
			public void onSensorChanged(SensorEvent event) {
				float value = event.values[0]; //得到当前的亮度值
				tv.setText("当前的亮度是---》："+value);
			}
			
			//精度发生了改变
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
		}, sensor,  SensorManager.SENSOR_DELAY_NORMAL);
		
		
		
		
		
		
		
		
		/*
		//2
		List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
		StringBuilder sBuilder = new StringBuilder();
		for (Sensor s : sensors) {
			String name = s.getName();
			int type = s.getType();
			sBuilder.append("name=="+name+"--type="+type+"\r\n");
		}
		tv.setText(sBuilder.toString());*/
	}

}
