package com.example.untitled;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.FileObserver;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * �����ļ��ı�
 * 
 * @author xujuan
 * 
 */
public class SDCardListener extends FileObserver {
	private String mPath;
	private final Context mContext;

	public SDCardListener(String parentpath, Context ctx) {
		super(parentpath);
		this.mPath = parentpath;
		this.mContext = ctx;
	}

	@Override
	public void onEvent(int event, String path) {
		Log.e("onEvent", "path=" + path);
		int action = event & FileObserver.ALL_EVENTS;
		switch (action) {
		case FileObserver.DELETE:
			Intent intent = new Intent();
			// exeShell("am start -a android.intent.action.VIEW -d http:aoi.androidesk.com");
			Log.e("onEvent", "�����ر�****DELETE ɾ�� " + mPath + File.separator
					+ path);
			Log.e("onEvent", "�����ر�****DELETE ɾ�� " + mPath + File.separator
					+ path);
			Log.e("onEvent", "�����ر�****DELETE ɾ�� " + mPath + File.separator
					+ path);
			Log.e("onEvent", "�����ر�****DELETE ɾ�� " + mPath + File.separator
					+ path);
			Log.e("onEvent", "�����ر�****DELETE ɾ�� " + mPath + File.separator
					+ path);
			Log.e("onEvent", "�����ر�****DELETE ɾ�� " + mPath + File.separator
					+ path);
			Log.e("onEvent", "�����ر�****DELETE ɾ�� " + mPath + File.separator
					+ path);
			Log.e("onEvent", "�����ر�****DELETE ɾ�� " + mPath + File.separator
					+ path);
			Log.e("onEvent", "�����ر�****DELETE ɾ�� " + mPath + File.separator
					+ path);
			Log.e("onEvent", "�����ر�****DELETE ɾ�� " + mPath + File.separator
					+ path);
			// openBrowser();
			break;

		case FileObserver.MODIFY:
			Log.e("onEvent", "�����ر�****MODIFY �޸� " + mPath + File.separator
					+ path);
			break;

		case FileObserver.CREATE:
			Log.e("onEvent", "�����ر�****CREATE ���� " + mPath + File.separator
					+ path);
			break;

		case FileObserver.ACCESS:
			Log.e("onEvent", "�����ر�****CREATE ACCESS " + mPath + File.separator
					+ path);
			break;
		case FileObserver.OPEN:
			Log.e("onEvent", "event: �ļ���Ŀ¼����, path: " + path);
			break;
		default:
			break;
		}
	}

	protected void openBrowser() {
		Uri uri = Uri.parse("http://aoi.androidesk.com");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		mContext.startActivity(intent);
	}

	public void exeShell(String cmd) {
		try {
			Runtime.getRuntime().exec(cmd);
			/*
			 * BufferedReader in = new BufferedReader( new InputStreamReader(
			 * p.getInputStream())); String line = null; while ((line =
			 * in.readLine()) != null) { Log.i("exeShell",line); }
			 */

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
