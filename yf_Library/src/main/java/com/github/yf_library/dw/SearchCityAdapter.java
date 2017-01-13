package com.github.yf_library.dw;

import java.util.ArrayList;
import java.util.List;

import com.github.yf_library.R;
import com.github.yf_library.dw.db.City;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;


public class SearchCityAdapter extends BaseAdapter implements Filterable {

	private List<City> mAllCities;
	private List<City> mResultCities;
	private LayoutInflater mInflater;
	private Context mContext;
	private City city;


	public SearchCityAdapter(Context context, List<City> allCities) {
		mContext = context;
		mAllCities = allCities;
		mResultCities = new ArrayList<City>();
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mResultCities.size();
	}

	@Override
	public City getItem(int position) {
		return mResultCities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.search_city_item_yfdef, null);
		}
		city=mResultCities.get(position);
		TextView provinceTv = (TextView) convertView
				.findViewById(R.id.search_province);
		provinceTv.setText(city.getProvince());
		TextView cityTv = (TextView) convertView
				.findViewById(R.id.column_title);
		cityTv.setText(city.getCity());
		Log.i("mylog", "长度"+mResultCities.size());
//		Toast.makeText(mContext, mResultCities.size(), 1).show();

		return convertView;
	}

	@Override
	public Filter getFilter() {
		Filter filter = new Filter() {
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				mResultCities = (ArrayList<City>) results.values;
				if (results.count > 0) {
					notifyDataSetChanged();
				} else {
					notifyDataSetInvalidated();
				}
			}

			@SuppressLint("DefaultLocale")
			protected FilterResults performFiltering(CharSequence s) {
				String str = s.toString().toUpperCase();
				// mFilterStr = str;
				FilterResults results = new FilterResults();
				ArrayList<City> cityList = new ArrayList<City>();
				if (mAllCities != null && mAllCities.size() != 0) {
					for (City cb : mAllCities) {
//						if (cb.getAllFristPY().indexOf(str) > -1
//								|| cb.getAllPY().indexOf(str) > -1
//								|| cb.getCity().indexOf(str) > -1) {
//							cityList.add(cb);
//						}
						if ( cb.getCity().indexOf(str) > -1
								) {
							cityList.add(cb);
						}
					}
				}
				results.values = cityList;
				results.count = cityList.size();
				Log.i("mylog", "results.count"+results.count);
				return results;
			}
		};
		return filter;
	}

}
