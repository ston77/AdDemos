package com.itheima.loadimage;


import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
      Bitmap bitmap =   BitmapFactory.decodeFile("/mnt/sdcard/big.jpg"); 
      
      //显示位图图像
      iv.setImageBitmap(bitmap);
    }


    
}
