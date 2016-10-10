package com.itheima.surface;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * surfaceViewʹ�õ���˫����Ļ��ƣ����Կ��Ե�λʱ���ڸ��´����Ļ���
 * 
 * �߳�0�� ��ʾ����---�������� ---��ʾ����
 * �߳�1�� ��������----��ʾ����---��������
 * 
 * ���������߳��������UI  ProgressBar ������
 *
 */
public class MainActivity extends Activity {

	SurfaceView sv ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		sv	= (SurfaceView) findViewById(R.id.sv);
		
		new Thread(){
			public void run() {
				SystemClock.sleep(1000);
				//��ȡ��surfaceview�Ŀ�����
				SurfaceHolder holder = sv.getHolder();
				int radius = 5; 
				
				for (int i = 0; i < 10; i++) {
					//���ڽ���û��ʾ�����Ի����ǿյġ�
					Canvas canvas = holder.lockCanvas();
					canvas.drawColor(Color.BLACK);
					Paint paint = new Paint();
					paint.setColor(Color.RED);
					radius +=i;
					canvas.drawCircle(100, 100, radius, paint);
					//��������
					holder.unlockCanvasAndPost(canvas);
					SystemClock.sleep(100);
				}
			};
		}.start();
		
	}

	
	public void update(View v){
		new Thread(){
			public void run() {
				//��ȡ��surfaceview�Ŀ�����
				SurfaceHolder holder = sv.getHolder();
				int radius = 5; 
				
				for (int i = 0; i < 10; i++) {
					Canvas canvas = holder.lockCanvas();
					canvas.drawColor(Color.BLACK);
					Paint paint = new Paint();
					paint.setColor(Color.RED);
					radius +=i;
					canvas.drawCircle(100, 100, radius, paint);
					//��������
					holder.unlockCanvasAndPost(canvas);
					SystemClock.sleep(100);
				}
			};
		}.start();
	}
}
