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
		
		
		//1.�õ�ԭͼ
		Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pre);
		
		//2.�õ�һ�ݿ���
		copyBitmap= Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
		
		
		//3.��������
		Canvas canvas = new Canvas(copyBitmap);
		
		
		//4.��ʼ����������ԭͼ��ʹ��1��1�ı�������
		canvas.drawBitmap(srcBitmap, new Matrix(), new Paint());
		
		//5.��imageView��ʾ�����õ�ͼƬ
		iv_pre.setImageBitmap(copyBitmap);
		 
		
		iv_pre.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {//�жϵ�ǰ�Ķ���
				case MotionEvent.ACTION_DOWN: //���µĶ���
//					event.getRawX()
					try {
						int downX = (int) event.getX();
						int downY = (int) event.getY();
						for (int i = -4; i < 5; i++) {
							for (int j = -4; j < 5; j++) {
								//Ϊ��Ҫʵ�ֳ���������ʾһ��Բ�Σ�����Ե�ǰ��i �� j�����ж�
								if(Math.sqrt(i*i +j*j) <= 4 ){
									//��ָ������������ɫ���͸���ģ��Ա��ܿ����������һ��ͼƬ
									copyBitmap.setPixel(downX+i, downY+j, Color.TRANSPARENT);
									//�޸����֮��Ҫ�ǵ���ʾ���µ�ͼƬ
									iv_pre.setImageBitmap(copyBitmap);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case MotionEvent.ACTION_MOVE: //�ƶ��Ķ���
					try {
						int moveX = (int) event.getX();
						int moveY = (int) event.getY();
						for (int i = -4; i < 5; i++) {
							for (int j = -4; j < 5; j++) {
								if(Math.sqrt(i*i +j*j) <= 4 ){
									//��ָ������������ɫ���͸���ģ��Ա��ܿ����������һ��ͼƬ
									copyBitmap.setPixel(moveX+i, moveY+j, Color.TRANSPARENT);
									//�޸����֮��Ҫ�ǵ���ʾ���µ�ͼƬ
									iv_pre.setImageBitmap(copyBitmap);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
				case MotionEvent.ACTION_UP: //����Ķ���
					
					break;

				default:
					break;
				}
				return true;
			}
		});
	}

}
