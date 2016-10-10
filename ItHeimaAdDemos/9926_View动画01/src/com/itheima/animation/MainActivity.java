package com.itheima.animation;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.iv);
	}

	public void translate(View v) {

		// 定义一个平移动画
		/*
		 * TranslateAnimation animation = new TranslateAnimation( 0, 200, 0, 0);
		 */

		/*
		 * RELATIVE_TO_PARENT ：相对屏幕的宽高来说 倍数
		 *  RELATIVE_TO_SELF ：相对自己控件的宽高来说 倍数
		 * Animation.ABSOLUTE ：绝对值 指的是像素
		 */

		TranslateAnimation animation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
				0.5f, Animation.RELATIVE_TO_PARENT, 0,
				Animation.RELATIVE_TO_PARENT, 0);
		// 定义动画时长
		animation.setDuration(2000);
		// 无限循环播放
		animation.setRepeatCount(TranslateAnimation.INFINITE);

		// 指定重复播放的模式
		animation.setRepeatMode(TranslateAnimation.RESTART);

		// 让图片播放这个动画
		iv.startAnimation(animation);

	}

	public void rotate(View v) {

		RotateAnimation animation = new RotateAnimation(0,
				360,// 从什么角度旋转到什么角度
				Animation.RELATIVE_TO_SELF, 0.2f, 
				Animation.RELATIVE_TO_SELF,
				0.5f);

		// 定义动画时长
		animation.setDuration(2000);
		// 无限循环播放
		animation.setRepeatCount(TranslateAnimation.INFINITE);

		// 指定重复播放的模式
		animation.setRepeatMode(TranslateAnimation.REVERSE);

		// 让图片播放这个动画
		iv.startAnimation(animation);
	}

	public void scale(View v) {

		ScaleAnimation animation = new ScaleAnimation(3, 1, // x方向，从什么倍数到什么倍数
				3, 1,// y方向，从什么倍数到什么倍数
				Animation.RELATIVE_TO_SELF, 0.5f, // 放大的中心点 x方向
				Animation.RELATIVE_TO_SELF, 0.5f); // 放大的中心店 y方向

		// 定义动画时长
		animation.setDuration(2000);
		// 无限循环播放
		animation.setRepeatCount(TranslateAnimation.INFINITE);

		// 指定重复播放的模式
		animation.setRepeatMode(TranslateAnimation.REVERSE);

		// 让图片播放这个动画
		iv.startAnimation(animation);
	}

	public void alpha(View v) {

		AlphaAnimation animation = new AlphaAnimation(0.5f, 1);

		// 定义动画时长
		animation.setDuration(2000);
		// 无限循环播放
		animation.setRepeatCount(TranslateAnimation.INFINITE);

		// 指定重复播放的模式
		animation.setRepeatMode(TranslateAnimation.REVERSE);

		// 让图片播放这个动画
		iv.startAnimation(animation);
	}
}
