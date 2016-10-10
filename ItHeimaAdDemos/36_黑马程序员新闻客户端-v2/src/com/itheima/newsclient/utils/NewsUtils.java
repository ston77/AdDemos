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

	
	//返回 所有的 解析到的 item, 然后 将 所有 item 放到 一个 list集合中, 再 返回 
	public static List<NewsItem> getAllNewsItems(String path) throws Exception {
		List<NewsItem> list = new ArrayList<NewsItem>();
		
		//连接网络 
		
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		
		//获得返回的 状态码
		int code = conn.getResponseCode();
		
		if(code==200){
			
			InputStream in = conn.getInputStream();  // 是一个 xml 格式的数据的流 
			
			//解析 xml 格式的数据, 需要使用到 pull 解析器 
			
			XmlPullParser parser = Xml.newPullParser();
			
			parser.setInput(in, "UTF-8");
			
			int type = parser.getEventType();
/*
 * 
<channel>
	<item>
		<title>军报评徐才厚</title>
		<description>人死账不消 反腐步不停，支持，威武，顶，有希望了。</description>
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
						
						//将item 添加到 list 中去 
						list.add(item);
					}
				}
				type = parser.next();
			}
		}
		return list;
	}

}
