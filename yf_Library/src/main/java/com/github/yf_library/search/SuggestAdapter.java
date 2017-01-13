/**
 * @CopyRight(c) 2016年12月22日
 */
package com.github.yf_library.search;

import java.util.ArrayList;
import java.util.List;

import com.github.yf_library.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

/**
 * @author yihui
 * @version 1.0 2016年12月22日下午2:41:48
 */
public class SuggestAdapter extends BaseAdapter implements Filterable{
	
	private Context mContext;
	private List<String> list;
	private List<String> resultlist;
	private LayoutInflater mInflater;
	private int layoutId;
	
	
	/**
	 * @param mContext
	 * @param list
	 */
	public SuggestAdapter(Context mContext, List<String> list,int resId) {
		super();
		this.mContext = mContext;
		this.list = list;
		this.layoutId=resId;
		resultlist = new ArrayList<String>();

		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return resultlist.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return resultlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if (convertView == null) {
			convertView = mInflater.inflate(layoutId, null);
		}
		TextView tv = (TextView) convertView
				.findViewById(R.id.history_item);
		tv.setText(resultlist.get(position)+"");
		convertView.setTag(resultlist.get(position));

		return convertView;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		Filter filter = new Filter() {
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				resultlist = (ArrayList<String>) results.values;
				if (results.count > 0) {
					notifyDataSetChanged();
				} else {
					notifyDataSetInvalidated();
				}
			}

			protected FilterResults performFiltering(CharSequence s) {
				String str = s.toString().toUpperCase();
				// mFilterStr = str;
				FilterResults results = new FilterResults();
				List<String> temp_List = new ArrayList<String>();
				if (list != null && list.size() != 0) {
					for (String cb: list) {
						if (cb.indexOf(str) > -1
								|| cb.indexOf(str) > -1
								|| cb.indexOf(str) > -1) {
							temp_List.add(cb);
						}
					}
				}
				results.values = temp_List;
				results.count = temp_List.size();
				return results;
			}
		};
		return filter;
	}
	

}
