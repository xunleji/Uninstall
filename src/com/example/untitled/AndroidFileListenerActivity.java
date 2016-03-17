package com.example.untitled;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AndroidFileListenerActivity extends Activity {

	private FileObserver mFileObserver;
	private Button btn1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String mFilePath = Environment.getExternalStorageDirectory()
						.getPath() + File.separator + "test.txt";
				File mFile = new File(mFilePath);
				if (!mFile.exists()) {
					try {
						mFile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		if (null == mFileObserver) {
			mFileObserver = new SDCardFileObserver(Environment
					.getExternalStorageDirectory().getPath());
			mFileObserver.startWatching(); // 开始监听
		}
	}

	public void onDestory() {
		if (null != mFileObserver)
			mFileObserver.stopWatching(); // 停止监听
	}

	static class SDCardFileObserver extends FileObserver {
		// mask:指定要监听的事件类型，默认为FileObserver.ALL_EVENTS
		public SDCardFileObserver(String path, int mask) {
			super(path, mask);
		}

		public SDCardFileObserver(String path) {
			super(path);
		}

		@Override
		public void onEvent(int event, String path) {
			final int action = event & FileObserver.ALL_EVENTS;
			switch (action) {
			case FileObserver.CREATE:
				System.out.println("event: 文件或目录被创建, path: " + path);
				break;
			case FileObserver.ACCESS:
				System.out.println("event: 文件或目录被访问, path: " + path);
				break;

			case FileObserver.DELETE:
				System.out.println("event: 文件或目录被删除, path: " + path);
				break;

			case FileObserver.OPEN:
				System.out.println("event: 文件或目录被打开, path: " + path);
				break;

			case FileObserver.MODIFY:
				System.out.println("event: 文件或目录被修改, path: " + path);
				break;
			}
		}

	}
}
