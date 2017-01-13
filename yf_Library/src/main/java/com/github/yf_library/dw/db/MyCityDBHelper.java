package com.github.yf_library.dw.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;


public class MyCityDBHelper {

	Context context;
	private CityDB mCityDB;
	SQLiteDatabase database;
	private String pakageName;


	public MyCityDBHelper(Context context,String str) {
		super();
		this.context = context;
		this.pakageName=str;
	}

	public synchronized CityDB getCityDB() {
		if (mCityDB == null)
			mCityDB = openCityDB();
		return mCityDB;
	}

	private CityDB openCityDB() {
		String path = "/data"
				+ Environment.getDataDirectory().getAbsolutePath()
				+ File.separator + pakageName
				+ File.separator + CityDB.CITY_DB_NAME;
//		String path = "/data/data/com.gpdi.bdapp/databases/" + CityDB.CITY_DB_NAME;
//		String path = "/Android/data/com.gpdi.bdapp/" + CityDB.CITY_DB_NAME;

		Log.i("log",path);
		System.out.print("路径"+path);
		File db = new File(path);

		if (!db.exists()) {
			Log.i("log","db is not exists");
			try {
//				db.createNewFile();
				InputStream ios=context.getClass().getClassLoader().getResourceAsStream("assets/"+"city.db");

//				InputStream is = context.getAssets().open("city.db");
				FileOutputStream fos = new FileOutputStream(db);
				int len = -1;
				byte[] buffer = new byte[1024];
				while ((len = ios.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					fos.flush();
				}
				fos.close();
				ios.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		return new CityDB(context, path);
	}
}
