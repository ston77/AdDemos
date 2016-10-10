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
		//��xml�ļ�---��������
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_demo);
		iv.startAnimation(animation);
	}

	public void rotate(View v) {
		//��xml�ļ�---��������
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotete_demo);
		iv.startAnimation(animation);
		
	}

	public void scale(View v) {

		ScaleAnimation animation = new ScaleAnimation(3, 1, // x���򣬴�ʲô������ʲô����
				3, 1,// y���򣬴�ʲô������ʲô����
				Animation.RELATIVE_TO_SELF, 0.5f, // �Ŵ�����ĵ� x����
				Animation.RELATIVE_TO_SELF, 0.5f); // �Ŵ�����ĵ� y����

		// ���嶯��ʱ��
		animation.setDuration(2000);
		// ����ѭ������
		animation.setRepeatCount(TranslateAnimation.INFINITE);

		// ָ���ظ����ŵ�ģʽ
		animation.setRepeatMode(TranslateAnimation.REVERSE);

		// ��ͼƬ�����������
		iv.startAnimation(animation);
	}

	public void alpha(View v) {

		AlphaAnimation animation = new AlphaAnimation(0.5f, 1);

		// ���嶯��ʱ��
		animation.setDuration(2000);
		// ����ѭ������
		animation.setRepeatCount(TranslateAnimation.INFINITE);

		// ָ���ظ����ŵ�ģʽ
		animation.setRepeatMode(TranslateAnimation.REVERSE);

		// ��ͼƬ�����������
		iv.startAnimation(animation);
	}
	
	
	public void set(View v){
		//���� ƽ�ƶ��� �� ��ת����
//		Interpolator : ������   false : ÿ������ʹ���Լ��Ĳ�������  true: ��Ҷ�ʹ�ü�������Ĳ�������ʹ��ͬһ
		AnimationSet set = new AnimationSet(false);
		
		/*//����ƽ�ƶ���
		TranslateAnimation animation = new TranslateAnimation(
				Animation.ABSOLUTE, 0, 
				Animation.ABSOLUTE, 200, 
				Animation.ABSOLUTE, 0,
				Animation.ABSOLUTE, 0);*/
		TranslateAnimation animation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
				0.5f, Animation.RELATIVE_TO_PARENT, 0,
				Animation.RELATIVE_TO_PARENT, 0.5f);
		// ���嶯��ʱ��
		animation.setDuration(2000);
		// ����ѭ������
		animation.setRepeatCount(TranslateAnimation.INFINITE);

		// ָ���ظ����ŵ�ģʽ
		animation.setRepeatMode(TranslateAnimation.REVERSE);
		
		/**///������ת����
		RotateAnimation ranimation = new RotateAnimation(0,
				360,// ��ʲô�Ƕ���ת��ʲô�Ƕ�
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF,0.5f);

		// ���嶯��ʱ��
		ranimation.setDuration(2000);
		// ����ѭ������
		ranimation.setRepeatCount(TranslateAnimation.INFINITE);

		// ָ���ظ����ŵ�ģʽ
		ranimation.setRepeatMode(TranslateAnimation.REVERSE);

		
		ScaleAnimation sanimation = new ScaleAnimation(1, 2, // x���򣬴�ʲô������ʲô����
				1, 2,// y���򣬴�ʲô������ʲô����
				Animation.RELATIVE_TO_SELF, 0.5f, // �Ŵ�����ĵ� x����
				Animation.RELATIVE_TO_SELF, 0.5f); // �Ŵ�����ĵ� y����

		// ���嶯��ʱ��
		sanimation.setDuration(2000);
		// ����ѭ������
		sanimation.setRepeatCount(TranslateAnimation.INFINITE);

		// ָ���ظ����ŵ�ģʽ
		sanimation.setRepeatMode(TranslateAnimation.REVERSE);
		
		AlphaAnimation aanimation = new AlphaAnimation(0, 1);
		
		// ���嶯��ʱ��
		aanimation.setDuration(2000);
		// ����ѭ������
		aanimation.setRepeatCount(TranslateAnimation.INFINITE);
		
		// ָ���ظ����ŵ�ģʽ
		aanimation.setRepeatMode(TranslateAnimation.REVERSE);
		
		
		set.addAnimation(animation);
		set.addAnimation(ranimation);
//		set.addAnimation(aanimation);
		
		
		
		//���ż��϶���
		iv.startAnimation(set);
		
	}
}
