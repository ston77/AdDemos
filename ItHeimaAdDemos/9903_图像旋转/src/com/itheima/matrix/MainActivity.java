package com.itheima.matrix;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView iv_src , iv_target;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv_src = (ImageView) findViewById(R.id.iv_src);
		iv_target = (ImageView) findViewById(R.id.iv_target);
	}
	
	public void scale(View v){
		//缩放图片
		
		//1.显示原图
		Bitmap bitmap = BitmapFactory.decodeFile("/mnt/sdcard/tp1.jpg");
		iv_src.setImageBitmap(bitmap);
		
		//2.显示缩放的图片
		//要想显示缩放的图片，并不是直接对原图进行缩放，而是对原图的一份拷贝进行缩放
		
		//a.创建一份原图的拷贝 --根据原图得到一份空白的位图拷贝
		//举个例子：八骏图，要做一付赝品， 宽高必须与之一样，否则人家一眼就看穿了。
		Bitmap copyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		
		
		//b.定义出来一个画板 、画架 ,里面摆放着原来的空白纸张，表名要在这张纸上作画了
		Canvas canvas = new Canvas(copyBitmap);
		
		//c. 创建一个画笔
		Paint paint = new Paint();
		
		//d.创建矩阵  ：1：1的比例图片
		Matrix matrix = new Matrix();
		//以图片的中心为旋转点，旋转90度
		matrix.setRotate(90 , copyBitmap.getWidth()/2 ,copyBitmap.getHeight()/2);

		//e.开始作画： 参照哪一付图开始作画
		canvas.drawBitmap(bitmap, matrix, paint);
		
		//如果以上几个步骤都已经走完了，那么代表着空白纸张上已经有图像
		iv_target.setImageBitmap(copyBitmap);
		
		
		
		
	}


}
