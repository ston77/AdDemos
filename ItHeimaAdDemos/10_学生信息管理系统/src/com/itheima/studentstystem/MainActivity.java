package com.itheima.studentstystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_name;
	private EditText ed_number;
	private RadioGroup rgb;
	private TextView tv_info;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ed_name = (EditText) findViewById(R.id.ed_name);
		ed_number = (EditText) findViewById(R.id.ed_number);
		rgb = (RadioGroup) findViewById(R.id.rgb);
		tv_info = (TextView) findViewById(R.id.tv_info);
	}

	
	//保存学生的信息
	public void save(View v){
		
		String name = ed_name.getText().toString().trim();
		String number = ed_number.getText().toString().trim();
		
		if(TextUtils.isEmpty(number)||TextUtils.isEmpty(name)){
			Toast.makeText(this, "对不起, 学生姓名和 学号不能为空", 0).show();
			return;
		}
/*

zs.xml

		小作业:  保存学生的信息的时候, 保存下学生的 城市 city 信息 

<?xml version="1.0" standalone="yes" encoding="gbk"?>
<student city="shenzhen">
	<name>张三</name>
	<number>20150832</number>
	<sex>male</sex>
</student>

lisi.xml

<student city="guangzhou">
	<name>李四</name>
	<number>20150832</number>
	<sex>male</sex>
</student>
			
			pull解析 器的一套 api 包括 : serializer , 以及 pullparser 
			
			android中 已经集成 了pull 解析器 所 有的 api , 所以这里 可以直接用了.
			
 * 
 */
			String sex ="male";
			int id = rgb.getCheckedRadioButtonId();
		
			if(id==R.id.male){
				sex ="male";
			}else{
				sex ="female";
			}
		
			try {
				File file = new File(getFilesDir(), name+".xml");
				XmlSerializer newSerializer = Xml.newSerializer();

				OutputStream out = new FileOutputStream(file);
				newSerializer.setOutput(out, "utf-8");
				newSerializer.startDocument("utf-8", true);
				
				newSerializer.startTag(null, "student");
				
				newSerializer.startTag(null, "name");
				newSerializer.text(name);
				newSerializer.endTag(null, "name");
				
				newSerializer.startTag(null, "number");
				newSerializer.text(number);
				newSerializer.endTag(null, "number");
				
				newSerializer.startTag(null, "sex");
				newSerializer.text(sex);
				newSerializer.endTag(null, "sex");
				
				newSerializer.endTag(null, "student");
				newSerializer.endDocument();
				
				out.close();
				Toast.makeText(this, "保存成功 ", 0).show();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, "保存失败 ", 0).show();
			}
		
	}
	
	//查询 学生的信息 , 显示出来, 使用  pull parser
	public void query(View v){
		
		String name = ed_name.getText().toString().trim();
		
		if(TextUtils.isEmpty(name)){
			Toast.makeText(this, "要查询的学生的姓名不能为空 ", 0).show();
			return;
		}
		
		File file = new File(getFilesDir(), name+".xml");
		
		if(file==null||file.length()<=0){
			Toast.makeText(this, "要查询的学生的姓名 不存在 ", 0).show();
			return;
		}
		
		//则说明 学生的信息 肯定存在
		//就需要去解析xml 文件了 
		
		try {
			
			
			XmlPullParser parser =  Xml.newPullParser();
			InputStream in = new FileInputStream(file);
			parser.setInput(in, "UTF-8");
			
			int type = parser.getEventType();
/*
 * 
<student city="guangzhou">
	<name>李四</name>
	<number>20150832</number>
	<sex>male</sex>
</student>

 * 			
 */
			String cityValue = null;
			String nameValue = null;
			String number = null;
			String sex = null;
			while(type!=XmlPullParser.END_DOCUMENT){
			
				if(XmlPullParser.START_TAG==type){
					if("student".equals(parser.getName())){
						int count = parser.getAttributeCount();
						if(count>0){
							cityValue = parser.getAttributeValue(0);
						}
					}else if("name".equals(parser.getName())){
						nameValue = parser.nextText();
					}else if("number".equals(parser.getName())){
						number = parser.nextText();
					}else if("sex".equals(parser.getName())){
						sex = parser.nextText();
					}
				}
				type=parser.next();
			}
			tv_info.setText(nameValue+"\n"+number +"\n"+sex+"\n"+cityValue);
			
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "解析失败  ", 0).show();
		}
		
	}
	
	
}
