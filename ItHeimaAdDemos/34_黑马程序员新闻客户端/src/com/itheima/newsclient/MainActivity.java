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
					
					// 告诉 listView显示数据
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
		
		//将 条目 item 的信息显示出来 
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
				//说明是 评论
				tv_type.setTextColor(Color.BLUE);
				tv_type.setText("评论: " + item.getComment());
				
			}else if ("2".equals(item.getType())){
				//说明是视频
				tv_type.setTextColor(Color.RED);
				tv_type.setText("视频");
			}else if ("3".equals(item.getType())){
				//说明是直播
				tv_type.setTextColor(Color.YELLOW);
				tv_type.setText("直播");
			}
			
			
			//由于要显示的图片是一个网络的图片, 而连接网络显示图片又 是一个非常常见的需求, 所以
			// 就有开源的 别人实现好的框架, 你直接拿过来用就 行 了. 
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

				// 去连接服务器, 拿 数据
				// 提供一个工具类, 专门用于 完成连接网络 ,解析 数据
				String url = getResources().getString(R.string.ip);
				try {

					items = NewsUtils.getAllNewsItems(url);

					if (items.size() == 0) {

						// 说明解析 失败

						Message msg = Message.obtain();
						msg.what = ERROR;
						handler.sendMessage(msg);

					} else {

						// 说明解析成功
						Message msg = Message.obtain();
						msg.what = SUCCESS;
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					e.printStackTrace();
					// 说明解析 失败
					Message msg = Message.obtain();
					msg.what = ERROR;
					handler.sendMessage(msg);

				}

			};
		}.start();

	}

}
