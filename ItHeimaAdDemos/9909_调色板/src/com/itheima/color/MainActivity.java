package com.itheima.color;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	Paint paint;
	Canvas canvas ;
	ImageView iv ;
	Bitmap srcBitmap , copyBitmap ;
	Matrix matrix ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv  = (ImageView) findViewById(R.id.iv);
		
		 srcBitmap = BitmapFactory.decodeFile("/mnt/sdcard/tp1.jpg");
		
		//1.得到原图的拷贝
		 copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
		//2.定义画板，指定拷贝图片
		 canvas = new Canvas(copyBitmap);
		
		//3.定义画笔
		paint = new Paint();
		//4.定义矩阵
		matrix = new Matrix();
		
		//5.开始作画
		canvas.drawBitmap(srcBitmap, matrix, paint);
		
		iv.setImageBitmap(copyBitmap);
		
		
		SeekBar seekBar = 	(SeekBar) findViewById(R.id.seekBar1);
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				float number = seekBar.getProgress()/50.0f;
				Toast.makeText(MainActivity.this, "当前的色彩比例是："+number, 0).show();
				
				//定义颜色矩阵
				ColorMatrix cm = new ColorMatrix();
				cm.set(new float[] {
				1*number, 0, 0, 0, 0, //操作红色  青红
				0, 1, 0, 0, 0, //绿色  紫绿
				0, 0, 1, 0, 0,//蓝色 黄蓝
				0, 0, 0, 1, 0 //透明度
				});
				//给画笔添加颜色矩阵
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				
				//在画板上再次作画，这次作画将会带上颜色矩阵
				canvas.drawBitmap(srcBitmap, matrix, paint);
				
				//如果已经作画好了，就直接显示这张图片了。
				iv.setImageBitmap(copyBitmap);
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
			}
		});
	}


}
