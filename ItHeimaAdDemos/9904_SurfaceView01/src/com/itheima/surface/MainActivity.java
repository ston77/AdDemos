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
 * surfaceView使用的是双缓冲的机制，所以可以单位时间内更新大量的画面
 * 
 * 线程0： 显示画面---计算数据 ---显示画面
 * 线程1： 计算数据----显示画面---计算数据
 * 
 * 可以在子线程里面更新UI  ProgressBar 进度条
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
				//获取到surfaceview的控制器
				SurfaceHolder holder = sv.getHolder();
				int radius = 5; 
				
				for (int i = 0; i < 10; i++) {
					//由于界面没显示，所以画布是空的。
					Canvas canvas = holder.lockCanvas();
					canvas.drawColor(Color.BLACK);
					Paint paint = new Paint();
					paint.setColor(Color.RED);
					radius +=i;
					canvas.drawCircle(100, 100, radius, paint);
					//解锁画布
					holder.unlockCanvasAndPost(canvas);
					SystemClock.sleep(100);
				}
			};
		}.start();
		
	}

	
	public void update(View v){
		new Thread(){
			public void run() {
				//获取到surfaceview的控制器
				SurfaceHolder holder = sv.getHolder();
				int radius = 5; 
				
				for (int i = 0; i < 10; i++) {
					Canvas canvas = holder.lockCanvas();
					canvas.drawColor(Color.BLACK);
					Paint paint = new Paint();
					paint.setColor(Color.RED);
					radius +=i;
					canvas.drawCircle(100, 100, radius, paint);
					//解锁画布
					holder.unlockCanvasAndPost(canvas);
					SystemClock.sleep(100);
				}
			};
		}.start();
	}
}
