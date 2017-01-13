package com.github.yf_library.dw.db;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;



public class CityDB {
	public static final String CITY_DB_NAME = "city.db";
	private static final String CITY_TABLE_NAME = "city";
	private SQLiteDatabase db;
	private Context mContext;
	List<City> list;
	SharedPreferences sp;
	SharedPreferences.Editor editor;

	public CityDB(Context context, String path) {
		db = context.openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
		this.mContext=context;
		sp=context.getSharedPreferences("DbInfo", context.MODE_PRIVATE);
		editor=sp.edit();
	}

	public List<City> getAllCity() {
		if(sp.getBoolean("isFirsh", true)==true){
			setHotCity();
			editor.putBoolean("isFirsh", false);
			editor.commit();
		}
	
		list = new ArrayList<City>();
	//	Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME, null); //查询
		Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME+" order by firstpy", null);
		
		while (c.moveToNext()) {
//			if(!c.getString(c.getColumnIndex("firstpy")).equals("!")){
//				setHotCity();
//			}
			String province = c.getString(c.getColumnIndex("province"));
			String city = c.getString(c.getColumnIndex("city"));
			String number = c.getString(c.getColumnIndex("number"));
			String allPY = c.getString(c.getColumnIndex("allpy"));
			String allFirstPY = c.getString(c.getColumnIndex("allfirstpy"));
			String firstPY = c.getString(c.getColumnIndex("firstpy"));
			City item = new City(province, city, number, firstPY, allPY,
					allFirstPY);
			list.add(item);
		}
		return list;
	}

	public City getCity(String city) {
		if(TextUtils.isEmpty(city))
			return null;
		City item = getCityInfo(parseName(city));
		if (item == null) {
			item = getCityInfo(city);
		}
		return item;
	}
	
	/**
	 * 去掉后缀搜索
	 * @param city
	 * @return
	 */
	private String parseName(String city) {
		if (city.contains("市")) {//
			String subStr[] = city.split("市");
			city = subStr[0];
		} else if (city.contains("县")) {//
			String subStr[] = city.split("县");
			city = subStr[0];
		}
		return city;
	}

	private City getCityInfo(String city) {
		Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME
				+ " where city=?", new String[] { city });
		if (c.moveToFirst()) {
			String province = c.getString(c.getColumnIndex("province"));
			String name = c.getString(c.getColumnIndex("city"));
			String number = c.getString(c.getColumnIndex("number"));
			String allPY = c.getString(c.getColumnIndex("allpy"));
			String allFirstPY = c.getString(c.getColumnIndex("allfirstpy"));
			String firstPY = c.getString(c.getColumnIndex("firstpy"));
			City item = new City(province, name, number, firstPY, allPY,
					allFirstPY);
			return item;
		}
		return null;
	}

	public void setHotCity(){
		String[] Str=new String[]{
				"上海","广州","长沙"
		};
		String c_str="广州";

		for(String s:Str){
			Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME
					+ " where city=?", new String[] { s });
			ContentValues mContentValues=new ContentValues();

			if (c.moveToFirst()) {
				String province = c.getString(c.getColumnIndex("province"));
				String name = c.getString(c.getColumnIndex("city"));
				String number = c.getString(c.getColumnIndex("number"));
				String allPY = c.getString(c.getColumnIndex("allpy"));
				String allFirstPY = c.getString(c.getColumnIndex("allfirstpy"));
				String firstPY = c.getString(c.getColumnIndex("firstpy"));
				mContentValues.put("province",province);
				mContentValues.put("city",name);
				mContentValues.put("number",number);
				mContentValues.put("allpy",allPY);
				mContentValues.put("allfirstpy",allFirstPY);
				mContentValues.put("firstpy","$");
				db.insert(CITY_TABLE_NAME, null, mContentValues);

			}

		}
		
		Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME
				+ " where city=?", new String[] { c_str });
		ContentValues mContentValues=new ContentValues();

		if (c.moveToFirst()) {
			String province = c.getString(c.getColumnIndex("province"));
			String name = c.getString(c.getColumnIndex("city"));
			String number = c.getString(c.getColumnIndex("number"));
			String allPY = c.getString(c.getColumnIndex("allpy"));
			String allFirstPY = c.getString(c.getColumnIndex("allfirstpy"));
			String firstPY = c.getString(c.getColumnIndex("firstpy"));
			mContentValues.put("province",province);
			mContentValues.put("city",name);
			mContentValues.put("number",number);
			mContentValues.put("allpy",allPY);
			mContentValues.put("allfirstpy",allFirstPY);
			mContentValues.put("firstpy","!");
			db.insert(CITY_TABLE_NAME, null, mContentValues);

		}

	}
	
	
	
}
