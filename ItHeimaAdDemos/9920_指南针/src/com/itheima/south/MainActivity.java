package com.itheima.south;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 final TextView tv = (TextView)findViewById(R.id.tv);

		SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor sensor = manager.getDefaultSensor(3);

		manager.registerListener(new SensorEventListener() {

			@Override
			public void onSensorChanged(SensorEvent event) {
				// values[0]: �ֻ���Y������������ļн� . 0=North, 90=East, 180=South,
				// 270=West
				int value = (int) event.values[0];
				String str = "";

				if (value == 0) {
					str = "��������" + value;
				} else if (value == 90) {
					str = "��������" + value;
				} else if (value == 180) {
					str = "���Ϸ���" + value;
				} else if (value == 270) {
					str = "��������" + value;
				} else if (value > 0 && value < 90) {

					str = "��������" + value;
				} else if (value > 90 && value < 180) {

					str = "���Ϸ���" + value;
				} else if (value > 180 && value < 270) {

					str = "���Ϸ���" + value;
				} else if (value > 270 && value < 360) {
					str = "��������" + value;
				}
				
				tv.setText(str);

			}

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {

			}
		}, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

}
