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
        //����λͼͼ�� 
        //��ʾ��ͼ��ʱ���׳������ڴ�������쳣��������ͼƬҲ����Ϊʲô���׳��쳣��?
        //> ԭ����Androidϵͳ����λͼ��ʱ��ʹ��32λȥ��ʾ  argb  8 + 8 + 8 + 8 =32  4���ֽڵĴ�С
      Bitmap bitmap =   BitmapFactory.decodeFile("/mnt/sdcard/big.jpg"); 
      
      //��ʾλͼͼ��
      iv.setImageBitmap(bitmap);
    }


    
}
