package com.itheima.smartimage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

public class HeimaSmartImageView extends ImageView {

	protected static final int SUCCESS = 0;
	protected static final int ERROR = 1;
	protected static final int ERROR_PIC = 2;

	public HeimaSmartImageView(Context context) {
		super(context);
	}

	public HeimaSmartImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HeimaSmartImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
				case SUCCESS:
						
					//图片拿到了要显示 
					Bitmap bitmap = (Bitmap) msg.obj;
					setImageBitmap(bitmap);
					break;
				case ERROR_PIC:
					
					int resourceID = (Integer) msg.obj;
					setImageResource(resourceID);
					//出错了 
					break;
				case ERROR:
	
					//出错了 
					break;
	
				default:
					break;
			}

		};
	};

	//用于实现, 图片没显示了的时候就 显示  这里给定 的默认的图片..
	public void setImageUrl(final String url,final int resourceId) {

		new Thread() {

			public void run() {

				try {
					URL ul = new URL(url);
					HttpURLConnection conn = (HttpURLConnection) ul
							.openConnection();

					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");

					int code = conn.getResponseCode();
					if (code == 200) {

						InputStream in = conn.getInputStream();
						Bitmap bitmap = BitmapFactory.decodeStream(in);

						Message msg = Message.obtain();
						msg.obj = bitmap;
						msg.what = SUCCESS;
						handler.sendMessage(msg);
					} else {
						Message msg = Message.obtain();
						msg.what = ERROR_PIC;
						msg.obj = resourceId;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = Message.obtain();
					msg.what = ERROR;
					handler.sendMessage(msg);
				}

			};

		}.start();

	}

	
	public void setImageUrl(final String url) {

		new Thread() {

			public void run() {

				try {
					URL ul = new URL(url);
					HttpURLConnection conn = (HttpURLConnection) ul
							.openConnection();

					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");

					int code = conn.getResponseCode();
					if (code == 200) {

						InputStream in = conn.getInputStream();
						Bitmap bitmap = BitmapFactory.decodeStream(in);

						Message msg = Message.obtain();
						msg.obj = bitmap;
						msg.what = SUCCESS;
						handler.sendMessage(msg);
					} else {
						Message msg = Message.obtain();
						msg.what = ERROR;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = Message.obtain();
					msg.what = ERROR;
					handler.sendMessage(msg);
				}

			};

		}.start();

	}

}
