package com.itheima.painter;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

/**
 *  ��ν������Ϳѻ��ʵ���Ͼ�����һ��bitmap��������Ȼ���������õ�bitmap��ʾ��iamgeView�ϣ�
 *  ���ǲ�����ֱ����imageView��������
 *
 */
public class MainActivity extends Activity  implements OnClickListener{
	Paint paint ; 
	Bitmap bitmap ;
	Canvas canvas ; 
	ImageView iv ; 
	float density ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
		
		Display display = manager.getDefaultDisplay();
		
		DisplayMetrics metrics = new DisplayMetrics();
		
		display.getMetrics(metrics);
		
		
		density = metrics.density;
		int dpi = metrics.densityDpi;
		System.out.println("density ==="+density +"---dpi="+dpi);
		
		
		//����һ������
		paint = new Paint();
		//Ĭ�ϻ��ʾ���5�Ŀ��
		paint.setStrokeWidth(5);
		
		//����һ���հ׵�λͼ���� 300*300�Ŀ�ߣ�������32λ��λͼͼ��
		bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
		//��������
		canvas = new Canvas(bitmap);
		//�Ȼ�һ���ɫ�ı���
		canvas.drawColor(Color.WHITE);
		
		
		findViewById(R.id.red).setOnClickListener(this);
		findViewById(R.id.green).setOnClickListener(this);
		findViewById(R.id.blue).setOnClickListener(this);
		findViewById(R.id.yellow).setOnClickListener(this);
		findViewById(R.id.purple).setOnClickListener(this);
		
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			//ֹͣ�ƶ�
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				//�õ���ǰseekbar�Ľ���
				int progress = seekBar.getProgress();
				paint.setStrokeWidth(progress);
				Toast.makeText(MainActivity.this, "��ǰ�Ļ��ʴ�ϸֵΪ��"+progress, 0).show();
			}
			
			//��ʼ�ƶ�
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			//�����ƶ�
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
			}
		});
		
		//Ϊ����imageview�������������imageView���д����¼�����
		iv = (ImageView) findViewById(R.id.iv);
		
		iv.setOnTouchListener(new OnTouchListener() {
			float startX ; 
			float startY ; 
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch (event.getAction()) {//�жϵ�ǰ�Ķ�����
				
				case MotionEvent.ACTION_DOWN: //���µĶ���
					System.out.println("donw-----");
					//��ȡ����ǰ���µ������
					startX = event.getX() /*/density*/;
					startY = event.getY() /*/density*/;
					break;
				case MotionEvent.ACTION_MOVE: //�ƶ��Ķ���
					float stopX = event.getX()/*/density*/;
					float stopY = event.getY()/*/density*/;
					//��ʼ������������������������bitmap��
					canvas.drawLine(startX, startY, stopX, stopY, paint);
					//��ʾͼƬ
					iv.setImageBitmap(bitmap);
					
					startX = stopX;
					startY = stopY;
					System.out.println("move-----");
					break;
				case MotionEvent.ACTION_UP: //����Ķ���
					
					System.out.println("up-----");
					break;
				}
				//�������true,���������������¼����˽�����ϵͳ��Ҫ������¼������´����ˣ�����Ҫ�Լ���������¼�
				//�����false:�����ʱ���¼��Խ��������´��ݣ����滹��һЩ������Ҳ��Ҫ��׽������¼�
				return true;
			}
		});
	
	}

	@Override
	public void onClick(View v) {
		String str = "";
		switch (v.getId()) {
		case R.id.red:
			str = "��ɫ";
			paint.setColor(Color.RED);
			break;
		case R.id.green:
			str = "��ɫ";
			paint.setColor(Color.GREEN);
			
			break;
		case R.id.blue:
			str = "��ɫ";
			paint.setColor(Color.BLUE);
			
			break;
		case R.id.yellow:
			str = "��ɫ";
			paint.setColor(Color.YELLOW);
			
			break;
		case R.id.purple:
			str = "��ɫ";
			paint.setColor(0xFFFF00FF);
			
			break;
		}
		
		Toast.makeText(this, "��ǰ�Ļ�����ɫ�ǣ�"+str, 0).show();
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//�����ǰѡ�е�itemid ��r.id.save ,����
		if (item.getItemId() == R.id.save){
			Toast.makeText(this, "����ͼƬ", 0).show();
			
			//����bitmap
			try {
				OutputStream stream = new FileOutputStream("/mnt/sdcard/"+System.currentTimeMillis()+".jpg");
				//����λͼ��ָ����·�����£� 
				//����һ�� ��������ͣ�������������ľ�ȷ�������������������·��
				bitmap.compress(CompressFormat.JPEG, 100, stream);
				Toast.makeText(this, "����ͼƬ�ɹ�", 0).show();
				
				
				//Ҫ����ͼ����ʾ���õ�ͼƬ�������ڴ˷���һ���㲥����ƭϵͳ��������Ϊ����SD�������ؽ����ˣ���һ��ɨ��SD��
				
				Intent intent = new Intent();
				//SD�������صĶ���
				intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
				Uri data = Uri.fromFile(Environment.getExternalStorageDirectory()); 
				intent.setData(data);
				sendBroadcast(intent);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
