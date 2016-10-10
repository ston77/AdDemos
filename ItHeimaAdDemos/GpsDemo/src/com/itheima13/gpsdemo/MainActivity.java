package com.itheima13.gpsdemo;

import java.util.List;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv_location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��λ��Ϣ
		
		tv_location = (TextView) findViewById(R.id.tv_location);
		
		//��λapi
		LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		//��ȡ��λ���ṩ��ʽ
		List<String> allProviders = lm.getAllProviders();
		for (String prov : allProviders) {
			System.out.println(prov);
		}
		//provider ��λ��ʽ
		// 0 ʱ��Ϳռ�   ���λ�ñ仯 ���Զ�����
		lm.requestLocationUpdates("gps", 0, 0, new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			// location�����ص����
			@Override
			public void onLocationChanged(Location location) {
				// λ�÷����仯 ����
				float accuracy = location.getAccuracy();//��ȷ��
				double latitude = location.getLatitude();//γ��
				double longitude = location.getLongitude();//����
				double altitude = location.getAltitude();//����
				
				String mess = "accuracy:" + accuracy + "\n" + "latitude:" + latitude + "\n" +"longitude:" + 
				longitude + "\n" + "altitude:" + altitude + "\n";
				tv_location.setText(mess);
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
