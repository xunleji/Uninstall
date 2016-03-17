package com.example.untitled;

import java.io.File;
import java.io.IOException;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

public class SDCardListenSer extends IntentService {

	SDCardListener[] listenners;

	public SDCardListenSer() {
		super("SDCardListenSer");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		SDCardListener[] listenners = {
				new SDCardListener("/data/data/com.example.untitled", this),
				new SDCardListener(Environment.getExternalStorageDirectory()
						+ File.separator + "1.txt", this) };
		this.listenners = listenners;

		Log.e("onEvent", "=========onCreate============");
		for (SDCardListener listener : listenners) {
			listener.startWatching();
		}

		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "1.txt");
		Log.e("onEvent", "dddddddddddddddddddddd nCreate============");
		if (file.exists())
			file.delete();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onHandleIntent(Intent intent) {
	}

	@Override
	public void onDestroy() {
		for (SDCardListener listener : listenners) {
			listener.stopWatching();
		}
	}
}
