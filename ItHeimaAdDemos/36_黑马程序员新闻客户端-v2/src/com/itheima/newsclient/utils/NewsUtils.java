package com.itheima.newsclient.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.itheima.newsclient.domain.NewsItem;

public class NewsUtils {

	
	//���� ���е� �������� item, Ȼ�� �� ���� item �ŵ� һ�� list������, �� ���� 
	public static List<NewsItem> getAllNewsItems(String path) throws Exception {
		List<NewsItem> list = new ArrayList<NewsItem>();
		
		//�������� 
		
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		
		//��÷��ص� ״̬��
		int code = conn.getResponseCode();
		
		if(code==200){
			
			InputStream in = conn.getInputStream();  // ��һ�� xml ��ʽ�����ݵ��� 
			
			//���� xml ��ʽ������, ��Ҫʹ�õ� pull ������ 
			
			XmlPullParser parser = Xml.newPullParser();
			
			parser.setInput(in, "UTF-8");
			
			int type = parser.getEventType();
/*
 * 
<channel>
	<item>
		<title>��������ź�</title>
		<description>�����˲��� ��������ͣ��֧�֣����䣬������ϣ���ˡ�</description>
		<image>http://188.188.4.100:8080/img/a.jpg</image>
		<type>1</type>
		<comment>163</comment>
	</item>
 * 			
 */
			
			
			NewsItem item = null;
			while(type!=XmlPullParser.END_DOCUMENT){
				
				if(type==XmlPullParser.START_TAG){
					
					if("item".equals(parser.getName())){
						item = new NewsItem();
					}else if("title".equals(parser.getName())){
						item.setTitle(parser.nextText());
					}else if("description".equals(parser.getName())){
						item.setDescription(parser.nextText());
					}else if("image".equals(parser.getName())){
						item.setImage(parser.nextText());
					}else if("type".equals(parser.getName())){
						item.setType(parser.nextText());
					}else if("comment".equals(parser.getName())){
						item.setComment(parser.nextText());
					}
				}else if(type==XmlPullParser.END_TAG){
					if("item".equals(parser.getName())){
						
						//��item ��ӵ� list ��ȥ 
						list.add(item);
					}
				}
				type = parser.next();
			}
		}
		return list;
	}

}
