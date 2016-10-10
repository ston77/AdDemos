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

	private static final int threadCount = 3; // �̵߳�����
	private static int runningThreadCount = 3; // ��ʾ���� �����ŵ��̵߳�����
	private static final String path = "http://188.188.4.100:8080/test.exe";

	public void download(View v) {
		// ʵ�ֶ��̵߳�����

		new Thread(){
			
			public void run() {
				
				try {
					// ��Ҫ���ʷ�����, ���Ҫ���ص��ļ��Ĵ�С
					URL url = new URL(path);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");

					int code = conn.getResponseCode();
					if (code == 200) {

						// ����ļ��Ĵ�С
						int length = conn.getContentLength();

						// Ҫ�ڱ��� ��һ��ͬ����С�Ŀ��ļ� --- RandomAccessFile()--- ��������ļ���

						String filename = getFileName(path);
						File file = new File(filename);
						RandomAccessFile raf = new RandomAccessFile(new File(Environment.getExternalStorageDirectory(),filename), "rws");
						raf.setLength(length);
						raf.close();

						// ���������߳�����--- 3 ��
						int blockSize = length / threadCount;

						// forѭ��, ÿ��ѭ��,����һ���߳�����
						for (int threadId = 0; threadId < threadCount; threadId++) {

							// ��ʼ����
							int startIndex = threadId * blockSize;

							// ��������
							int endIndex = (threadId + 1) * blockSize - 1;

							if (threadId == threadCount - 1) {
								endIndex = length - 1;
							}

							// �����߳�����
							new DownloadThread(threadId, startIndex, endIndex).start();
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();

	}

	// ��� �ļ��� ����
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
		private int currentPosition; // ʵʱ ����

		public DownloadThread(int threadId, int startIndex, int endIndex) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.currentPosition = startIndex;
		}

		@Override
		public void run() {
			System.out.println("�� " + threadId + " �߳�, ���ؿ�ʼ ���� : " + startIndex
					+ "~ " + endIndex);

			// ÿ���߳�Ҫ ȥ�ҷ������� Ŀ�� �ε�����
			try {
				URL url = new URL(path);

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();

				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");

				// Ҫ���� ������, ֻҪĿ��� ������
				// ͨ�� http������ͷ ȥ���� �Ϳ�����.
				String filename = getFileName(path);
				RandomAccessFile raf = new RandomAccessFile(new File(Environment.getExternalStorageDirectory(),filename), "rws");

				// �� Ŀ�� ��ʼ д �ӷ������������

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

				// �õ����ص�״̬��
				// 200, 304, 302, 404, 500

				// ���� ���� Ҫ���� ���� Ŀ���ļ�һ���ֵ�����, �������ﷵ�ص�״̬, ������ 200 ,
				// 206 Partial Content (�������� )
				int code = conn.getResponseCode();
				if (code == 206) {

					// �õ� Ŀ��ε�����
					InputStream in = conn.getInputStream();

					int len = 0;
					byte[] buf = new byte[1024 * 1024 * 2];
					while ((len = in.read(buf)) > 0) {
						raf.write(buf, 0, len);
						File ff2 = new File(Environment.getExternalStorageDirectory(),threadId + ".position");
						RandomAccessFile fl = new RandomAccessFile(ff2, "rws");

						// ������ �ۼ� ���� , ������ ʵʱ�ı��浽 �ļ���ȥ
						currentPosition += len; // 7
						fl.write((currentPosition + "").getBytes());
						fl.close();

							//��������ʾ�ڽ������� 
						
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

				System.out.println("�� " + threadId + " �߳�������� ...");

				File ff2 = new File(Environment.getExternalStorageDirectory(),threadId + ".position");
				ff2.renameTo(new File(Environment.getExternalStorageDirectory(),threadId + ".position.finish"));

				synchronized (MainActivity.class) {
					runningThreadCount--;

					if (runningThreadCount <= 0) {

						// ˵�������̶߳��������
						// �Ͱ������ļ���ɾ��

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
