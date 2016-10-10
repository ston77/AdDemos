package com.itheima.properyanimation;

import android.R.anim;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
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
//		iv.setTranslationX()
		
		ObjectAnimator animator =  ObjectAnimator.ofFloat(
				iv,  //˭��Ҫִ��������� һ��ſؼ��Ķ���
				"translationX",  //ˮƽƽ�ƵĶ���
				new float[]{0 ,10,20,30, 100}); //��0λ�ã��ƶ���100λ��
		
		animator.setDuration(2000);
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.setRepeatMode(ObjectAnimator.REVERSE);
		
		animator.start();
		
	}

	public void rotate(View v) {
//		iv.setRotation(rotation)
//		iv.setRotationY(rotationY)
		ObjectAnimator animator =  ObjectAnimator.ofFloat(
				iv,  //˭��Ҫִ��������� һ��ſؼ��Ķ���
				"rotationX",  //��x����Ϊ��������ת�Ķ���
				new float[]{0 ,360}); //��0λ�ã���ת��100λ��
		
		animator.setDuration(2000);
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.setRepeatMode(ObjectAnimator.REVERSE);
		
		animator.start();
	}

	public void scale(View v) {
		
//		iv.setScaleX(scaleX)
		ObjectAnimator animator =  ObjectAnimator.ofFloat(
				iv,  //˭��Ҫִ��������� һ��ſؼ��Ķ���
				"scaleX",  //��x����Ϊ��������ת�Ķ���
				new float[]{1 ,3}); //��0λ�ã���ת��100λ��
		
		animator.setDuration(2000);
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.setRepeatMode(ObjectAnimator.REVERSE);
		
		animator.start();
	}

	public void alpha(View v) {
		
//		iv.setAlpha(alpha)
		
		ObjectAnimator animator =  ObjectAnimator.ofFloat(
				iv,  //˭��Ҫִ��������� һ��ſؼ��Ķ���
				"alpha",  //͸���ȶ���
				new float[]{0 ,1}); //����ȫ͸������ȫ��͸��
		
		animator.setDuration(2000);
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.setRepeatMode(ObjectAnimator.REVERSE);
		
		animator.start();
		
	}
	
	//���Զ�������
	public void set(View v){
//		AnimationSet View�����ļ���
		AnimatorSet set = new AnimatorSet();
		
		
		ObjectAnimator animator =  ObjectAnimator.ofFloat(
				iv,  //˭��Ҫִ��������� һ��ſؼ��Ķ���
				"translationX",  //ˮƽƽ�ƵĶ���
				new float[]{0 , 100}); //��0λ�ã��ƶ���100λ��
		
		animator.setDuration(2000);
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.setRepeatMode(ObjectAnimator.REVERSE);
		
		ObjectAnimator animator2 =  ObjectAnimator.ofFloat(
				iv,  //˭��Ҫִ��������� һ��ſؼ��Ķ���
				"translationY",  //ˮƽƽ�ƵĶ���
				new float[]{0 , 100}); //��0λ�ã��ƶ���100λ��
		
		animator2.setDuration(2000);
		animator2.setRepeatCount(ObjectAnimator.INFINITE);
		animator2.setRepeatMode(ObjectAnimator.REVERSE);
		
		
//		set.playTogether(animator , animator2); //ͬʱ������������
		set.playSequentially(animator , animator2); //��˳�򲥷�
		
		set.start(); //���Ŷ���
		
	}
}
