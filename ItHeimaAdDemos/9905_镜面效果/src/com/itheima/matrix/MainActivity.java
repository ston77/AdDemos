package com.itheima.matrix;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView iv_src , iv_target;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv_src = (ImageView) findViewById(R.id.iv_src);
		iv_target = (ImageView) findViewById(R.id.iv_target);
	}
	
	public void scale(View v){
		//����ͼƬ
		
		//1.��ʾԭͼ
		Bitmap bitmap = BitmapFactory.decodeFile("/mnt/sdcard/tp1.jpg");
		iv_src.setImageBitmap(bitmap);
		
		//2.��ʾ���ŵ�ͼƬ
		//Ҫ����ʾ���ŵ�ͼƬ��������ֱ�Ӷ�ԭͼ�������ţ����Ƕ�ԭͼ��һ�ݿ�����������
		
		//a.����һ��ԭͼ�Ŀ��� --����ԭͼ�õ�һ�ݿհ׵�λͼ����
		//�ٸ����ӣ��˿�ͼ��Ҫ��һ����Ʒ�� ��߱�����֮һ���������˼�һ�۾Ϳ����ˡ�
		Bitmap copyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		
		
		//b.�������һ������ ������ ,����ڷ���ԭ���Ŀհ�ֽ�ţ�����Ҫ������ֽ��������
		Canvas canvas = new Canvas(copyBitmap);
		
		//c. ����һ������
		Paint paint = new Paint();
		
		//d.��������  ��1��1�ı���ͼƬ
		Matrix matrix = new Matrix();
		//��ˮƽ�����꣬ȫ����ɸ����������겻��Ҫ���ġ�
		matrix.setScale(-1, 1);
		
		// ���ں����궼����˸���������ͼƬ���ܵ���Ļ�����ȥ�ˣ������ƶ�������
		//������ע�⣺�ƶ���ʱ�򣬲���ʹ��setXXX���ַ�ʽ�ƶ�������setXXX����������ԭ����ͼƬ�޸Ļ�������ִ�С�
		matrix.postTranslate(copyBitmap.getWidth(), 0);
		

		//e.��ʼ������ ������һ��ͼ��ʼ����
		canvas.drawBitmap(bitmap, matrix, paint);
		
		//������ϼ������趼�Ѿ������ˣ���ô�����ſհ�ֽ�����Ѿ���ͼ��
		iv_target.setImageBitmap(copyBitmap);
		
		
		
		
	}


}
