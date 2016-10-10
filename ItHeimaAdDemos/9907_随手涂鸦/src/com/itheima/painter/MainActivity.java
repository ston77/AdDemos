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
 *  所谓的随手涂鸦，实际上就是在一个bitmap上作画，然后把这个画好的bitmap显示到iamgeView上，
 *  我们并不是直接在imageView上作画，
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
		
		
		//定义一个画笔
		paint = new Paint();
		//默认画笔就是5的宽度
		paint.setStrokeWidth(5);
		
		//创建一个空白的位图对象 300*300的宽高，并且是32位的位图图像
		bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
		//创建画板
		canvas = new Canvas(bitmap);
		//先画一遍白色的背景
		canvas.drawColor(Color.WHITE);
		
		
		findViewById(R.id.red).setOnClickListener(this);
		findViewById(R.id.green).setOnClickListener(this);
		findViewById(R.id.blue).setOnClickListener(this);
		findViewById(R.id.yellow).setOnClickListener(this);
		findViewById(R.id.purple).setOnClickListener(this);
		
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			//停止移动
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				//得到当前seekbar的进度
				int progress = seekBar.getProgress();
				paint.setStrokeWidth(progress);
				Toast.makeText(MainActivity.this, "当前的画笔粗细值为："+progress, 0).show();
			}
			
			//开始移动
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			//正在移动
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
			}
		});
		
		//为了在imageview上作画，必须对imageView进行触摸事件监听
		iv = (ImageView) findViewById(R.id.iv);
		
		iv.setOnTouchListener(new OnTouchListener() {
			float startX ; 
			float startY ; 
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch (event.getAction()) {//判断当前的动作，
				
				case MotionEvent.ACTION_DOWN: //按下的动作
					System.out.println("donw-----");
					//获取到当前按下的坐标点
					startX = event.getX() /*/density*/;
					startY = event.getY() /*/density*/;
					break;
				case MotionEvent.ACTION_MOVE: //移动的动作
					float stopX = event.getX()/*/density*/;
					float stopY = event.getY()/*/density*/;
					//开始作画，画线条，把线条画到bitmap上
					canvas.drawLine(startX, startY, stopX, stopY, paint);
					//显示图片
					iv.setImageBitmap(bitmap);
					
					startX = stopX;
					startY = stopY;
					System.out.println("move-----");
					break;
				case MotionEvent.ACTION_UP: //弹起的动作
					
					System.out.println("up-----");
					break;
				}
				//如果返回true,代表的是这个触摸事件到此结束，系统不要把这个事件再向下传递了，我们要自己处理这个事件
				//如果是false:代表的时候事件仍将继续往下传递，下面还有一些监听器也需要捕捉到这个事件
				return true;
			}
		});
	
	}

	@Override
	public void onClick(View v) {
		String str = "";
		switch (v.getId()) {
		case R.id.red:
			str = "红色";
			paint.setColor(Color.RED);
			break;
		case R.id.green:
			str = "绿色";
			paint.setColor(Color.GREEN);
			
			break;
		case R.id.blue:
			str = "蓝色";
			paint.setColor(Color.BLUE);
			
			break;
		case R.id.yellow:
			str = "黄色";
			paint.setColor(Color.YELLOW);
			
			break;
		case R.id.purple:
			str = "紫色";
			paint.setColor(0xFFFF00FF);
			
			break;
		}
		
		Toast.makeText(this, "当前的画笔颜色是："+str, 0).show();
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//如果当前选中的itemid 是r.id.save ,保存
		if (item.getItemId() == R.id.save){
			Toast.makeText(this, "保存图片", 0).show();
			
			//保存bitmap
			try {
				OutputStream stream = new FileOutputStream("/mnt/sdcard/"+System.currentTimeMillis()+".jpg");
				//保存位图到指定的路径底下： 
				//参数一： 保存的类型，参数二：保存的精确质量、参数三：保存的路径
				bitmap.compress(CompressFormat.JPEG, 100, stream);
				Toast.makeText(this, "保存图片成功", 0).show();
				
				
				//要想让图库显示画好的图片，可以在此发送一个广播，欺骗系统，让它以为现在SD卡被挂载进来了，再一次扫描SD卡
				
				Intent intent = new Intent();
				//SD卡被挂载的动作
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
