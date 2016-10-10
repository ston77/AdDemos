package com.itheima.animation;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
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
		//从xml文件---动画对象
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_demo);
		iv.startAnimation(animation);
	}

	public void rotate(View v) {
		//从xml文件---动画对象
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotete_demo);
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
	
	
	public void set(View v){
		//包含 平移动画 、 旋转动画
//		Interpolator : 插入器   false : 每个动画使用自己的插入器，  true: 大家都使用集合里面的插入器，使用同一
		AnimationSet set = new AnimationSet(false);
		
		/*//定义平移动画
		TranslateAnimation animation = new TranslateAnimation(
				Animation.ABSOLUTE, 0, 
				Animation.ABSOLUTE, 200, 
				Animation.ABSOLUTE, 0,
				Animation.ABSOLUTE, 0);*/
		TranslateAnimation animation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
				0.5f, Animation.RELATIVE_TO_PARENT, 0,
				Animation.RELATIVE_TO_PARENT, 0.5f);
		// 定义动画时长
		animation.setDuration(2000);
		// 无限循环播放
		animation.setRepeatCount(TranslateAnimation.INFINITE);

		// 指定重复播放的模式
		animation.setRepeatMode(TranslateAnimation.REVERSE);
		
		/**///定义旋转动画
		RotateAnimation ranimation = new RotateAnimation(0,
				360,// 从什么角度旋转到什么角度
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF,0.5f);

		// 定义动画时长
		ranimation.setDuration(2000);
		// 无限循环播放
		ranimation.setRepeatCount(TranslateAnimation.INFINITE);

		// 指定重复播放的模式
		ranimation.setRepeatMode(TranslateAnimation.REVERSE);

		
		ScaleAnimation sanimation = new ScaleAnimation(1, 2, // x方向，从什么倍数到什么倍数
				1, 2,// y方向，从什么倍数到什么倍数
				Animation.RELATIVE_TO_SELF, 0.5f, // 放大的中心点 x方向
				Animation.RELATIVE_TO_SELF, 0.5f); // 放大的中心店 y方向

		// 定义动画时长
		sanimation.setDuration(2000);
		// 无限循环播放
		sanimation.setRepeatCount(TranslateAnimation.INFINITE);

		// 指定重复播放的模式
		sanimation.setRepeatMode(TranslateAnimation.REVERSE);
		
		AlphaAnimation aanimation = new AlphaAnimation(0, 1);
		
		// 定义动画时长
		aanimation.setDuration(2000);
		// 无限循环播放
		aanimation.setRepeatCount(TranslateAnimation.INFINITE);
		
		// 指定重复播放的模式
		aanimation.setRepeatMode(TranslateAnimation.REVERSE);
		
		
		set.addAnimation(animation);
		set.addAnimation(ranimation);
//		set.addAnimation(aanimation);
		
		
		
		//播放集合动画
		iv.startAnimation(set);
		
	}
}
