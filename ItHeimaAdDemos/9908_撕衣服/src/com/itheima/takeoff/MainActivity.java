package com.itheima.takeoff;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView iv_pre;
	Bitmap copyBitmap  ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv_pre = (ImageView) findViewById(R.id.iv_pre);
		
		
		//1.得到原图
		Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pre);
		
		//2.得到一份拷贝
		copyBitmap= Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
		
		
		//3.创建画板
		Canvas canvas = new Canvas(copyBitmap);
		
		
		//4.开始作画，参照原图，使用1：1的比例作画
		canvas.drawBitmap(srcBitmap, new Matrix(), new Paint());
		
		//5.让imageView显示拷贝好的图片
		iv_pre.setImageBitmap(copyBitmap);
		 
		
		iv_pre.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {//判断当前的动作
				case MotionEvent.ACTION_DOWN: //按下的动作
//					event.getRawX()
					try {
						int downX = (int) event.getX();
						int downY = (int) event.getY();
						for (int i = -4; i < 5; i++) {
							for (int j = -4; j < 5; j++) {
								//为了要实现出来触摸显示一个圆形，必须对当前的i 和 j进行判定
								if(Math.sqrt(i*i +j*j) <= 4 ){
									//让指定的坐标点的颜色变成透明的，以便能看到下面的那一张图片
									copyBitmap.setPixel(downX+i, downY+j, Color.TRANSPARENT);
									//修改完毕之后，要记得显示最新的图片
									iv_pre.setImageBitmap(copyBitmap);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case MotionEvent.ACTION_MOVE: //移动的动作
					try {
						int moveX = (int) event.getX();
						int moveY = (int) event.getY();
						for (int i = -4; i < 5; i++) {
							for (int j = -4; j < 5; j++) {
								if(Math.sqrt(i*i +j*j) <= 4 ){
									//让指定的坐标点的颜色变成透明的，以便能看到下面的那一张图片
									copyBitmap.setPixel(moveX+i, moveY+j, Color.TRANSPARENT);
									//修改完毕之后，要记得显示最新的图片
									iv_pre.setImageBitmap(copyBitmap);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
				case MotionEvent.ACTION_UP: //弹起的动作
					
					break;

				default:
					break;
				}
				return true;
			}
		});
	}

}
