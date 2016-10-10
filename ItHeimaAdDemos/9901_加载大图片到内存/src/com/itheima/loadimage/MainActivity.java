package com.itheima.loadimage;


import java.io.IOException;

import android.media.ExifInterface;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        ImageView iv = (ImageView) findViewById(R.id.iv);
        
        //iv ---- jpg---bitmapfactory---bitmap
        //创建位图图像 
        //显示大图的时候抛出来了内存溢出的异常，看起来图片也不大，为什么会抛出异常呢?
        //> 原因是Android系统计算位图的时候，使用32位去表示  argb  8 + 8 + 8 + 8 =32  4个字节的大小
        //定义一个解析图片的选项对象
        Options opts  = new Options();
        //设置图片的采样率，宽度是原来的1/4像素个数 ，高度也是原来的1/4像素个数 ，那么解析的到的图片将是原图的1/16
       
        /*
         * 一般这个采样率都不会直接编写，而是取当前图片的分辨率和 屏幕的分辨率的宽高比例 
         */
        
        
        //1.得到屏幕的分辨率
        //得到系统的窗口服务
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        
        //得到当前显示相关的对象
        Display display = manager.getDefaultDisplay();
        
       int height =  display.getHeight();
        int width = display.getWidth();
        System.out.println("屏幕的分辨率是："+width+"---height="+height);
        //2.得到图片的分辨率
        String imgWidth  = null;
        try {
			//获取到文件里面保存的头额外信息
			ExifInterface exif = new ExifInterface("/mnt/sdcard/big.jpg");
			
			String imgHeight =  exif.getAttribute(ExifInterface.TAG_IMAGE_LENGTH) ; //获取到图片的高度
			imgWidth = exif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH) ; //获取到图片的宽度 
			System.out.println("图片的分辨率是："+imgWidth+"-----height="+imgHeight);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       int w =  Integer.parseInt(imgWidth) / width ; 
//        imghe
        //一般是去宽高比例的中间值，或者取最大的那一个值
        opts.inSampleSize = 4 ; 
      Bitmap bitmap =   BitmapFactory.decodeFile("/mnt/sdcard/big.jpg" , opts); 
      
      //显示位图图像
      iv.setImageBitmap(bitmap);
    }


    
}
