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
        //����λͼͼ�� 
        //��ʾ��ͼ��ʱ���׳������ڴ�������쳣��������ͼƬҲ����Ϊʲô���׳��쳣��?
        //> ԭ����Androidϵͳ����λͼ��ʱ��ʹ��32λȥ��ʾ  argb  8 + 8 + 8 + 8 =32  4���ֽڵĴ�С
        //����һ������ͼƬ��ѡ�����
        Options opts  = new Options();
        //����ͼƬ�Ĳ����ʣ������ԭ����1/4���ظ��� ���߶�Ҳ��ԭ����1/4���ظ��� ����ô�����ĵ���ͼƬ����ԭͼ��1/16
       
        /*
         * һ����������ʶ�����ֱ�ӱ�д������ȡ��ǰͼƬ�ķֱ��ʺ� ��Ļ�ķֱ��ʵĿ�߱��� 
         */
        
        
        //1.�õ���Ļ�ķֱ���
        //�õ�ϵͳ�Ĵ��ڷ���
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        
        //�õ���ǰ��ʾ��صĶ���
        Display display = manager.getDefaultDisplay();
        
       int height =  display.getHeight();
        int width = display.getWidth();
        System.out.println("��Ļ�ķֱ����ǣ�"+width+"---height="+height);
        //2.�õ�ͼƬ�ķֱ���
        String imgWidth  = null;
        try {
			//��ȡ���ļ����汣���ͷ������Ϣ
			ExifInterface exif = new ExifInterface("/mnt/sdcard/big.jpg");
			
			String imgHeight =  exif.getAttribute(ExifInterface.TAG_IMAGE_LENGTH) ; //��ȡ��ͼƬ�ĸ߶�
			imgWidth = exif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH) ; //��ȡ��ͼƬ�Ŀ�� 
			System.out.println("ͼƬ�ķֱ����ǣ�"+imgWidth+"-----height="+imgHeight);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       int w =  Integer.parseInt(imgWidth) / width ; 
//        imghe
        //һ����ȥ��߱������м�ֵ������ȡ������һ��ֵ
        opts.inSampleSize = 4 ; 
      Bitmap bitmap =   BitmapFactory.decodeFile("/mnt/sdcard/big.jpg" , opts); 
      
      //��ʾλͼͼ��
      iv.setImageBitmap(bitmap);
    }


    
}
