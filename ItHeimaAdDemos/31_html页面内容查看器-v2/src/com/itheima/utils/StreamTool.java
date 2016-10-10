package com.itheima.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {

	//�� ���ڽ�һ�� �� ������ת����   �ַ��� ���� 
	
	//ʹ��Ĭ�ϵı���:  ��android ��, Ĭ�ϵı����� UTF-8
	
	public static String decodeStream(InputStream in) throws IOException {
		
		//�ײ��� 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int len=0;
		byte[] buf = new byte[1024];
		while((len=in.read(buf))>0){
			baos.write(buf, 0, len);
		}
		
		baos.close();
		
		//�ڷ�������ǰ, ���� �ж� 
		String value = baos.toString();
		
		//���� �ַ� ����
		if(value.contains("GBK")){
			return baos.toString("gbk");
		}else if(value.contains("UTF-8")){
			return baos.toString("UTF-8");
		}else if(value.contains("ISO8859-1")){
			return baos.toString("ISO8859-1");
		}
		return baos.toString("UTF-8");
	}

}
