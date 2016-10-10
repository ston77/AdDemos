package com.itheima.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {

	//�� ���ڽ�һ�� �� ������ת����   �ַ��� ���� 
	public static String decodeStream(InputStream in) throws IOException {
		
		//�ײ��� 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int len=0;
		byte[] buf = new byte[1024];
		while((len=in.read(buf))>0){
			baos.write(buf, 0, len);
		}
		
		baos.close();
		//���� �ַ� ����
		return baos.toString();
	}

}
