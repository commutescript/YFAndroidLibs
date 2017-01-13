package com.github.yf_library.dw;

import java.util.List;

import com.github.yf_library.R;
import com.github.yf_library.dw.db.City;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class AllCityAdapter extends BaseAdapter implements SectionIndexer {
	private Context ct;
	private List<City> data;

	public AllCityAdapter(Context ct, List<City> datas) {
		this.ct = ct;
		this.data = datas;
	}

	/**
	 * 
	 * @param list
	 */
	public void updateListView(List<City> list) {
		this.data = list;
		notifyDataSetChanged();
	}

	public void remove(City user){
		this.data.remove(user);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(ct).inflate(
					R.layout.select_city_item_yfdef, null);
			viewHolder = new ViewHolder();
			viewHolder.group_title = (TextView) convertView.findViewById(R.id.group_title);
			viewHolder.column_title = (TextView) convertView
					.findViewById(R.id.column_title);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		City cityBean = data.get(position);
		final String name = cityBean.getCity();
		final String avatar = cityBean.getFirstPY();

		viewHolder.column_title.setText(name);
		//
		int section = getSectionForPosition(position);
		// 。
		if (position == getPositionForSection(section)) {
			viewHolder.group_title.setVisibility(View.VISIBLE);
			viewHolder.group_title.setText(cityBean.getFirstPY());

			if(cityBean.getFirstPY().equals("$")){
				viewHolder.group_title.setText("热门城市");

			}
			if(cityBean.getFirstPY().equals("!")){
				viewHolder.group_title.setText("当前城市");

			}
		} else {
			viewHolder.group_title.setVisibility(View.GONE);
		}

		return convertView;
	}

	static class ViewHolder {
         TextView group_title;// 导航
		
		TextView column_title;
	}

	public int getSectionForPosition(int position) {
		return data.get(position).getFirstPY().charAt(0);
	}


	@SuppressLint("DefaultLocale")
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = data.get(i).getFirstPY();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section){
				return i;
			}
		}

		return -1;
	}

	@Override
	public Object[] getSections() {
		return null;
	}

}