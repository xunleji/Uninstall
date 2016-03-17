package com.example.untitled;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.FileObserver;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * 监听文件改变
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
			Log.e("onEvent", "打包请关闭****DELETE 删除 " + mPath + File.separator
					+ path);
			Log.e("onEvent", "打包请关闭****DELETE 删除 " + mPath + File.separator
					+ path);
			Log.e("onEvent", "打包请关闭****DELETE 删除 " + mPath + File.separator
					+ path);
			Log.e("onEvent", "打包请关闭****DELETE 删除 " + mPath + File.separator
					+ path);
			Log.e("onEvent", "打包请关闭****DELETE 删除 " + mPath + File.separator
					+ path);
			Log.e("onEvent", "打包请关闭****DELETE 删除 " + mPath + File.separator
					+ path);
			Log.e("onEvent", "打包请关闭****DELETE 删除 " + mPath + File.separator
					+ path);
			Log.e("onEvent", "打包请关闭****DELETE 删除 " + mPath + File.separator
					+ path);
			Log.e("onEvent", "打包请关闭****DELETE 删除 " + mPath + File.separator
					+ path);
			Log.e("onEvent", "打包请关闭****DELETE 删除 " + mPath + File.separator
					+ path);
			// openBrowser();
			break;

		case FileObserver.MODIFY:
			Log.e("onEvent", "打包请关闭****MODIFY 修改 " + mPath + File.separator
					+ path);
			break;

		case FileObserver.CREATE:
			Log.e("onEvent", "打包请关闭****CREATE 创建 " + mPath + File.separator
					+ path);
			break;

		case FileObserver.ACCESS:
			Log.e("onEvent", "打包请关闭****CREATE ACCESS " + mPath + File.separator
					+ path);
			break;
		case FileObserver.OPEN:
			Log.e("onEvent", "event: 文件或目录被打开, path: " + path);
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
