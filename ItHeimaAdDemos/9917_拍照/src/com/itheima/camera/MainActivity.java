package com.itheima.camera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	 File file  ;
	ImageView iv ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView)findViewById(R.id.iv);
	}

	public void click(View v){
		

	    // create Intent to take a picture and return control to the calling application
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

	   // fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
	    
	    
	    file = new File("/mnt/sdcard/"+System.currentTimeMillis()+".jpg");
	    Uri fileUri = Uri.fromFile(file);
	    
	    //������ݵ���ֵ�������˼�ǣ�����ϵͳ�������������Ƭ֮�󣬰�������Ƭ���浽�����uri·����
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

	    // start the image capture Intent
	    startActivityForResult(intent, 1);

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//ת��ͼƬ
		if(file != null){
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			iv.setImageBitmap(bitmap);
		}
	}
}
