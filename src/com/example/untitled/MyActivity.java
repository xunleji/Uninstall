package com.example.untitled;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyActivity extends Activity {

	private Button btn1, btn2;
	private SDCardListener mFileObserver;
	private String mFilePath, mSDCardPath = Environment
			.getExternalStorageDirectory().toString();
	private File mFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mFileObserver = new SDCardListener(mSDCardPath, MyActivity.this);
				mFileObserver.startWatching();
			}
		});
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mFilePath = mSDCardPath + File.separator + "test.txt";
				mFile = new File(mFilePath);
				if (!mFile.exists()) {
					try {
						mFile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Intent intent = new Intent(this, SDCardListenSer.class);
		startService(intent);
		uninstall(android.os.Build.VERSION.SDK_INT);

	}

	public native String uninstall(int version);

	public native String stringFromJNI();

	static {
		Log.d("onEvent", "load jni lib");
		System.loadLibrary("hello-jni");
	}

}
