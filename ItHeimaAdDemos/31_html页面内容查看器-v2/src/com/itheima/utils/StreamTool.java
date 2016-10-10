package com.itheima.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {

	//就 用于将一个 流 的数据转换成   字符串 返回 
	
	//使用默认的编码:  在android 中, 默认的编码是 UTF-8
	
	public static String decodeStream(InputStream in) throws IOException {
		
		//底层流 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int len=0;
		byte[] buf = new byte[1024];
		while((len=in.read(buf))>0){
			baos.write(buf, 0, len);
		}
		
		baos.close();
		
		//在返回数据前, 进行 判断 
		String value = baos.toString();
		
		//返回 字符 数据
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
