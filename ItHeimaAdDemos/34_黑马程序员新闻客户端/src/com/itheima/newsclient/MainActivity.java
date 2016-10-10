package com.itheima.newsclient;

import java.util.List;

import com.itheima.newsclient.domain.NewsItem;
import com.itheima.newsclient.utils.NewsUtils;
import com.loopj.android.image.SmartImageView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	protected static final int ERROR = 0;
	protected static final int SUCCESS = 1;

	ListView lv;

	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
				case SUCCESS:
					
					// ���� listView��ʾ����
					if(adapter ==null){
						adapter = new MyAdapater();
						lv.setAdapter(adapter);
					}
					
					break;
				case ERROR:
	
					break;
	
				default:
					break;
			}

		};
	};

	MyAdapater adapter;
	
	private class MyAdapater extends BaseAdapter{

		@Override
		public int getCount() {
			return items.size();
		}
		
		//�� ��Ŀ item ����Ϣ��ʾ���� 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			NewsItem item = items.get(position);
			
			View view = null;
			if(convertView==null){
				view = View.inflate(MainActivity.this, R.layout.item, null);
			}else{
				view = convertView;
			}
			
			TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
			TextView tv_desc = (TextView) view.findViewById(R.id.tv_desc);
			TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
			SmartImageView iv_image = (SmartImageView) view.findViewById(R.id.iv_image);
			
			tv_title.setText(item.getTitle());
			tv_desc.setText(item.getDescription());
			
			if("1".equals(item.getType())){
				//˵���� ����
				tv_type.setTextColor(Color.BLUE);
				tv_type.setText("����: " + item.getComment());
				
			}else if ("2".equals(item.getType())){
				//˵������Ƶ
				tv_type.setTextColor(Color.RED);
				tv_type.setText("��Ƶ");
			}else if ("3".equals(item.getType())){
				//˵����ֱ��
				tv_type.setTextColor(Color.YELLOW);
				tv_type.setText("ֱ��");
			}
			
			
			//����Ҫ��ʾ��ͼƬ��һ�������ͼƬ, ������������ʾͼƬ�� ��һ���ǳ�����������, ����
			// ���п�Դ�� ����ʵ�ֺõĿ��, ��ֱ���ù����þ� �� ��. 
			System.out.println(item.getImage());
			iv_image.setImageUrl(item.getImage());
			
			return view;
		}
		
		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		
	}
	
	
	List<NewsItem> items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv = (ListView) findViewById(R.id.lv);

		new Thread() {

			public void run() {

				// ȥ���ӷ�����, �� ����
				// �ṩһ��������, ר������ ����������� ,���� ����
				String url = getResources().getString(R.string.ip);
				try {

					items = NewsUtils.getAllNewsItems(url);

					if (items.size() == 0) {

						// ˵������ ʧ��

						Message msg = Message.obtain();
						msg.what = ERROR;
						handler.sendMessage(msg);

					} else {

						// ˵�������ɹ�
						Message msg = Message.obtain();
						msg.what = SUCCESS;
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					e.printStackTrace();
					// ˵������ ʧ��
					Message msg = Message.obtain();
					msg.what = ERROR;
					handler.sendMessage(msg);

				}

			};
		}.start();

	}

}
