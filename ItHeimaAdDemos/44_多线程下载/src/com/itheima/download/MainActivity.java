package com.itheima.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	ProgressBar pb0;
	ProgressBar pb1;
	ProgressBar pb2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pb0 = (ProgressBar) findViewById(R.id.pb0);
		pb1 = (ProgressBar) findViewById(R.id.pb1);
		pb2 = (ProgressBar) findViewById(R.id.pb2);
		
	}

	private static final int threadCount = 3; // 线程的条数
	private static int runningThreadCount = 3; // 表示正在 运行着的线程的数量
	private static final String path = "http://188.188.4.100:8080/test.exe";

	public void download(View v) {
		// 实现多线程的下载

		new Thread(){
			
			public void run() {
				
				try {
					// 需要访问服务器, 获得要下载的文件的大小
					URL url = new URL(path);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");

					int code = conn.getResponseCode();
					if (code == 200) {

						// 获得文件的大小
						int length = conn.getContentLength();

						// 要在本地 建一个同样大小的空文件 --- RandomAccessFile()--- 随机访问文件类

						String filename = getFileName(path);
						File file = new File(filename);
						RandomAccessFile raf = new RandomAccessFile(new File(Environment.getExternalStorageDirectory(),filename), "rws");
						raf.setLength(length);
						raf.close();

						// 启动多条线程下载--- 3 条
						int blockSize = length / threadCount;

						// for循环, 每次循环,启动一条线程下载
						for (int threadId = 0; threadId < threadCount; threadId++) {

							// 开始索引
							int startIndex = threadId * blockSize;

							// 结束索引
							int endIndex = (threadId + 1) * blockSize - 1;

							if (threadId == threadCount - 1) {
								endIndex = length - 1;
							}

							// 启动线程下载
							new DownloadThread(threadId, startIndex, endIndex).start();
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();

	}

	// 获得 文件的 名称
	private static String getFileName(String path) {
		// http://188.188.4.100:8080/test.txt
		int index = path.lastIndexOf("/");
		String name = path.substring(index + 1);

		return name;
	}

	class DownloadThread extends Thread {

		private int threadId;
		private int startIndex;
		private int endIndex;
		private int currentPosition; // 实时 进度

		public DownloadThread(int threadId, int startIndex, int endIndex) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.currentPosition = startIndex;
		}

		@Override
		public void run() {
			System.out.println("第 " + threadId + " 线程, 下载开始 索引 : " + startIndex
					+ "~ " + endIndex);

			// 每条线程要 去找服务器拿 目标 段的数据
			try {
				URL url = new URL(path);

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();

				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");

				// 要告诉 服务器, 只要目标段 的数据
				// 通过 http的请求头 去设置 就可以了.
				String filename = getFileName(path);
				RandomAccessFile raf = new RandomAccessFile(new File(Environment.getExternalStorageDirectory(),filename), "rws");

				// 从 目标 开始 写 从服务器获得数据

				File ff = new File(Environment.getExternalStorageDirectory(),threadId + ".position");

				if (ff.exists() && ff.length() > 0) {
					BufferedReader br = new BufferedReader(new FileReader(ff));
					String line = br.readLine();

					currentPosition = Integer.parseInt(line);
					conn.setRequestProperty("range", "bytes=" + currentPosition
							+ "-" + endIndex);
					raf.seek(currentPosition);
				} else {

					// range:bytes=0-499
					conn.setRequestProperty("range", "bytes=" + startIndex
							+ "-" + endIndex);
					raf.seek(startIndex);
				}

				// 拿到返回的状态码
				// 200, 304, 302, 404, 500

				// 由于 这里 要的是 整个 目标文件一部分的数据, 所以这里返回的状态, 不再是 200 ,
				// 206 Partial Content (部分数据 )
				int code = conn.getResponseCode();
				if (code == 206) {

					// 拿到 目标段的数据
					InputStream in = conn.getInputStream();

					int len = 0;
					byte[] buf = new byte[1024 * 1024 * 2];
					while ((len = in.read(buf)) > 0) {
						raf.write(buf, 0, len);
						File ff2 = new File(Environment.getExternalStorageDirectory(),threadId + ".position");
						RandomAccessFile fl = new RandomAccessFile(ff2, "rws");

						// 将进度 累计 起来 , 将进度 实时的保存到 文件中去
						currentPosition += len; // 7
						fl.write((currentPosition + "").getBytes());
						fl.close();

							//将进度显示在进度条上 
						
						int max = endIndex-startIndex;
						
						if(threadId==0){
							pb0.setMax(max);
							pb0.setProgress(currentPosition);
						}else if(threadId==1){
							pb1.setMax(max);
							pb1.setProgress(currentPosition);
						}else if(threadId==2){
							pb2.setMax(max);
							pb2.setProgress(currentPosition);
						}
					}
					in.close();
					raf.close();
				}

				System.out.println("第 " + threadId + " 线程下载完成 ...");

				File ff2 = new File(Environment.getExternalStorageDirectory(),threadId + ".position");
				ff2.renameTo(new File(Environment.getExternalStorageDirectory(),threadId + ".position.finish"));

				synchronized (MainActivity.class) {
					runningThreadCount--;

					if (runningThreadCount <= 0) {

						// 说明三条线程都下载完成
						// 就把三个文件给删掉

						for (int i = 0; i < threadCount; i++) {
							File fl = new File(Environment.getExternalStorageDirectory(),i + ".position.finish");
							fl.delete();
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
