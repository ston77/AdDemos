package com.itheima.smshelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SmsListActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		
		ListView lv = (ListView) findViewById(R.id.lv);
		final String [] objects = {
				"�����ҵ��ģ������ҵĸΣ��������������ķ�֮���������ҵ�θ�������ҵķΣ����������еĺ�õ�壡���������˽ڿ��֣�",
				"���ʹ����գ������������㣬������ת���㲻Ҫ�������飬���н������Ҵ���������㣬��������ܾã���һ�����䡣ףʥ�����֣�",
				"����ĸ�ף�˭�����ߣ�����������ĸ���Ұ��㣡ĸ������Զ���֣�",
				"��߷紵�����ĥ���ң����ں����������ů���ң���Ϊ�����������Ұ������裬��Զ��Զ��ͯ���������У��㽲�Ĺ��£����ҵ�Ц����",
				"���裬лл������������ڽ���������Ľ��������Ů��ף�㽡�����١�",
				"ԲԲ��������ԲԲ�ı�������ѽڵ����㣡�������ǿգ������ľ��������������㣡���õ�ף�������õ��ģ��������������㣡��ǰԤף������ڿ��֣�",
				"�¶�ԲԲ���������࣬�±�ԲԲ���ϼ���Բ��������գ�����ףԸ���������������念��������˳�⣬������Ը�����ĳ��⣬Я����Լ��Э���������������졣����ףԸ��������֣�",
				"Ը������������һֻϲȵ����ҹ�����Ͼ��칬�ڣ������Ͽ�չ���ӳ�������£����ã��͸������л��ף���������Ҹ����ã�������ԲԲȱ����Ϧ���֡�",
				"ͨ�棺�����㳤�ڶ������ģ�����ʧ�񣬴�����ҵ�������������������Ὣ��˫�ֽ�����Ѻ����������ǩ�ֻ�Ѻ����Ϧǰ�������������Ƕ���ľ��档",
		};
		
		//ָ��listView��ʾ��������
		/**
		 * ArrayAdapter---����������
		 * ��һ��������������
		 * �ڶ���������ʹ��ʲôui����ʾitem
		 * ��������������ʾ������Դ
		 */
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, objects));
		
		
		
		//��ΪҪ֪�����������һ��item���Ա��ȡ�������ݡ����Ա���Ҫʵ�ֵ���¼�����
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(SmsListActivity.this, "������ǣ�"+position+"---ֵ�ǣ�"+objects[position], 0).show(); 
				
				
				Intent intent = new Intent();
				intent.putExtra("sms", objects[position]);
				//2.���ؽ��
				setResult(1, intent);
				
				setResult(2, intent);
				setResult(3, intent);
				setResult(4, intent);
				setResult(5, intent);
				
				//3.�رյ�ǰ����
				finish();
				
				
				
				/*Intent intent =new Intent(SmsListActivity.this,MainActivity.class);
				intent.putExtra("sms", objects[position]);
				startActivity(intent);*/
			}
		});
	}
}
