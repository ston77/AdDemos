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
				// values[0]: 手机的Y轴与正北方向的夹角 . 0=North, 90=East, 180=South,
				// 270=West
				int value = (int) event.values[0];
				String str = "";

				if (value == 0) {
					str = "正北方向：" + value;
				} else if (value == 90) {
					str = "正东方向：" + value;
				} else if (value == 180) {
					str = "正南方向：" + value;
				} else if (value == 270) {
					str = "正西方向：" + value;
				} else if (value > 0 && value < 90) {

					str = "东北方向：" + value;
				} else if (value > 90 && value < 180) {

					str = "东南方向：" + value;
				} else if (value > 180 && value < 270) {

					str = "西南方向：" + value;
				} else if (value > 270 && value < 360) {
					str = "西北方向：" + value;
				}
				
				tv.setText(str);

			}

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {

			}
		}, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

}
