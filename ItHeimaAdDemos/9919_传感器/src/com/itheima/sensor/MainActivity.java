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
		
		//1.�õ�������������
		SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
		
		//2.�õ����ߴ���������
		Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		//3.����һ�������������ݡ�
		manager.registerListener(new SensorEventListener() {
			
			//���ݷ����˸ı�
			@Override
			public void onSensorChanged(SensorEvent event) {
				float value = event.values[0]; //�õ���ǰ������ֵ
				tv.setText("��ǰ��������---����"+value);
			}
			
			//���ȷ����˸ı�
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
