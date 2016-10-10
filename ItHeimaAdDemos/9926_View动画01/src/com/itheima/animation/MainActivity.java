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

		// ����һ��ƽ�ƶ���
		/*
		 * TranslateAnimation animation = new TranslateAnimation( 0, 200, 0, 0);
		 */

		/*
		 * RELATIVE_TO_PARENT �������Ļ�Ŀ����˵ ����
		 *  RELATIVE_TO_SELF ������Լ��ؼ��Ŀ����˵ ����
		 * Animation.ABSOLUTE ������ֵ ָ��������
		 */

		TranslateAnimation animation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
				0.5f, Animation.RELATIVE_TO_PARENT, 0,
				Animation.RELATIVE_TO_PARENT, 0);
		// ���嶯��ʱ��
		animation.setDuration(2000);
		// ����ѭ������
		animation.setRepeatCount(TranslateAnimation.INFINITE);

		// ָ���ظ����ŵ�ģʽ
		animation.setRepeatMode(TranslateAnimation.RESTART);

		// ��ͼƬ�����������
		iv.startAnimation(animation);

	}

	public void rotate(View v) {

		RotateAnimation animation = new RotateAnimation(0,
				360,// ��ʲô�Ƕ���ת��ʲô�Ƕ�
				Animation.RELATIVE_TO_SELF, 0.2f, 
				Animation.RELATIVE_TO_SELF,
				0.5f);

		// ���嶯��ʱ��
		animation.setDuration(2000);
		// ����ѭ������
		animation.setRepeatCount(TranslateAnimation.INFINITE);

		// ָ���ظ����ŵ�ģʽ
		animation.setRepeatMode(TranslateAnimation.REVERSE);

		// ��ͼƬ�����������
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
}
