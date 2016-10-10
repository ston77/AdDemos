package com.itheima.properyanimation;

import java.io.Serializable;

import android.R.anim;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageView iv ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv = (ImageView) findViewById(R.id.iv);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "aaaaaaaaaaa", 0).show();
			}
		});
	}

	public void translate(View v) {
		
		 Xml.newSerializer().setOutput(os, encoding);
		
		
		
		
		//从xml文件导入动画资源
		Animator anim = AnimatorInflater.loadAnimator(this, R.animator.traslate_demo); 
		
		//指定哪一个控件去播放这个动作
		anim.setTarget(iv);
		
		anim.start();
		
		
		
	}

	public void rotate(View v) {
//		iv.setRotation(rotation)
//		iv.setRotationY(rotationY)
		ObjectAnimator animator =  ObjectAnimator.ofFloat(
				iv,  //谁将要执行这个动画 一般放控件的对象
				"rotationX",  //以x方向为中轴线旋转的动画
				new float[]{0 ,360}); //从0位置，旋转到100位置
		
		animator.setDuration(2000);
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.setRepeatMode(ObjectAnimator.REVERSE);
		
		animator.start();
	}

	public void scale(View v) {
		
//		iv.setScaleX(scaleX)
		ObjectAnimator animator =  ObjectAnimator.ofFloat(
				iv,  //谁将要执行这个动画 一般放控件的对象
				"scaleX",  //以x方向为中轴线旋转的动画
				new float[]{1 ,3}); //从0位置，旋转到100位置
		
		animator.setDuration(2000);
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.setRepeatMode(ObjectAnimator.REVERSE);
		
		animator.start();
	}

	public void alpha(View v) {
		
//		iv.setAlpha(alpha)
		
		ObjectAnimator animator =  ObjectAnimator.ofFloat(
				iv,  //谁将要执行这个动画 一般放控件的对象
				"alpha",  //透明度动画
				new float[]{0 ,1}); //从完全透明到完全不透明
		
		animator.setDuration(2000);
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.setRepeatMode(ObjectAnimator.REVERSE);
		
		animator.start();
		
	}
	
	//属性动画集合
	public void set(View v){
//		AnimationSet View动画的集合
		AnimatorSet set = new AnimatorSet();
		
		
		ObjectAnimator animator =  ObjectAnimator.ofFloat(
				iv,  //谁将要执行这个动画 一般放控件的对象
				"translationX",  //水平平移的动画
				new float[]{0 , 100}); //从0位置，移动到100位置
		
		animator.setDuration(2000);
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.setRepeatMode(ObjectAnimator.REVERSE);
		
		ObjectAnimator animator2 =  ObjectAnimator.ofFloat(
				iv,  //谁将要执行这个动画 一般放控件的对象
				"translationY",  //水平平移的动画
				new float[]{0 , 100}); //从0位置，移动到100位置
		
		animator2.setDuration(2000);
		animator2.setRepeatCount(ObjectAnimator.INFINITE);
		animator2.setRepeatMode(ObjectAnimator.REVERSE);
		
		
//		set.playTogether(animator , animator2); //同时播放两个动画
		set.playSequentially(animator , animator2); //按顺序播放
		
		set.start(); //播放动画
		
	}
}
