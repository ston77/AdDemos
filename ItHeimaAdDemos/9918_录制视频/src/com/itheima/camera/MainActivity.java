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
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {
	 File file  ;
	VideoView vv ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vv = (VideoView)findViewById(R.id.vv);
	}

	public void click(View v){
		

	    // create Intent to take a picture and return control to the calling application
	    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

	   // fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
	    
	    
	    file = new File("/mnt/sdcard/"+System.currentTimeMillis()+".3gp");
	    Uri fileUri = Uri.fromFile(file);
	    
	    //这个传递的数值代表的意思是：告诉系统照相机，拍完照片之后，把那张照片保存到给你的uri路径下
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
	    
	    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high


	    // start the image capture Intent
	    startActivityForResult(intent, 1);

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//转化图片
		if(file != null){
			vv.setVideoPath(file.getAbsolutePath());
			
			MediaController controller = new MediaController(this);
			controller.setAnchorView(vv);
			
			vv.setMediaController(controller);
			
			vv.start();
		}
	}
}
