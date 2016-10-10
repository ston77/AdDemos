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

	
	//����ѧ������Ϣ
	public void save(View v){
		
		String name = ed_name.getText().toString().trim();
		String number = ed_number.getText().toString().trim();
		
		if(TextUtils.isEmpty(number)||TextUtils.isEmpty(name)){
			Toast.makeText(this, "�Բ���, ѧ�������� ѧ�Ų���Ϊ��", 0).show();
			return;
		}
/*

zs.xml

		С��ҵ:  ����ѧ������Ϣ��ʱ��, ������ѧ���� ���� city ��Ϣ 

<?xml version="1.0" standalone="yes" encoding="gbk"?>
<student city="shenzhen">
	<name>����</name>
	<number>20150832</number>
	<sex>male</sex>
</student>

lisi.xml

<student city="guangzhou">
	<name>����</name>
	<number>20150832</number>
	<sex>male</sex>
</student>
			
			pull���� ����һ�� api ���� : serializer , �Լ� pullparser 
			
			android�� �Ѿ����� ��pull ������ �� �е� api , �������� ����ֱ������.
			
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
				Toast.makeText(this, "����ɹ� ", 0).show();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, "����ʧ�� ", 0).show();
			}
		
	}
	
	//��ѯ ѧ������Ϣ , ��ʾ����, ʹ��  pull parser
	public void query(View v){
		
		String name = ed_name.getText().toString().trim();
		
		if(TextUtils.isEmpty(name)){
			Toast.makeText(this, "Ҫ��ѯ��ѧ������������Ϊ�� ", 0).show();
			return;
		}
		
		File file = new File(getFilesDir(), name+".xml");
		
		if(file==null||file.length()<=0){
			Toast.makeText(this, "Ҫ��ѯ��ѧ�������� ������ ", 0).show();
			return;
		}
		
		//��˵�� ѧ������Ϣ �϶�����
		//����Ҫȥ����xml �ļ��� 
		
		try {
			
			
			XmlPullParser parser =  Xml.newPullParser();
			InputStream in = new FileInputStream(file);
			parser.setInput(in, "UTF-8");
			
			int type = parser.getEventType();
/*
 * 
<student city="guangzhou">
	<name>����</name>
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
			Toast.makeText(this, "����ʧ��  ", 0).show();
		}
		
	}
	
	
}
