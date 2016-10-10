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
		
		//1.�õ�ԭͼ�Ŀ���
		 copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
		//2.���廭�壬ָ������ͼƬ
		 canvas = new Canvas(copyBitmap);
		
		//3.���廭��
		paint = new Paint();
		//4.�������
		matrix = new Matrix();
		
		//5.��ʼ����
		canvas.drawBitmap(srcBitmap, matrix, paint);
		
		iv.setImageBitmap(copyBitmap);
		
		
		SeekBar seekBar = 	(SeekBar) findViewById(R.id.seekBar1);
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				float number = seekBar.getProgress()/50.0f;
				Toast.makeText(MainActivity.this, "��ǰ��ɫ�ʱ����ǣ�"+number, 0).show();
				
				//������ɫ����
				ColorMatrix cm = new ColorMatrix();
				cm.set(new float[] {
				1*number, 0, 0, 0, 0, //������ɫ  ���
				0, 1, 0, 0, 0, //��ɫ  ����
				0, 0, 1, 0, 0,//��ɫ ����
				0, 0, 0, 1, 0 //͸����
				});
				//�����������ɫ����
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				
				//�ڻ������ٴ�����������������������ɫ����
				canvas.drawBitmap(srcBitmap, matrix, paint);
				
				//����Ѿ��������ˣ���ֱ����ʾ����ͼƬ�ˡ�
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
