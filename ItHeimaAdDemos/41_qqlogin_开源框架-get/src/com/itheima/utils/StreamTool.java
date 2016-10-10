package com.itheima.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {

	//就 用于将一个 流 的数据转换成   字符串 返回 
	public static String decodeStream(InputStream in) throws IOException {
		
		//底层流 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int len=0;
		byte[] buf = new byte[1024];
		while((len=in.read(buf))>0){
			baos.write(buf, 0, len);
		}
		
		baos.close();
		//返回 字符 数据
		return baos.toString();
	}

}
