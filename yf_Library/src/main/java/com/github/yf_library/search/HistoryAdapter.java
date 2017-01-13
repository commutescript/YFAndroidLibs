/**
 * @CopyRight(c) 2016年12月22日
 */
package com.github.yf_library.search;

import java.util.List;
import java.util.Map;

import com.github.yf_library.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author yihui
 * @version 1.0 2016年12月22日下午5:20:10
 */
public class HistoryAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<Map<String, String>> list,temp_list;
	private LayoutInflater mInflater;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    private int layoutId;

	
	

	/**
	 * @param mContext
	 * @param list
	 */
	public HistoryAdapter(Context mContext, List<Map<String, String>> list,int resId) {
		super();
		this.mContext = mContext;
		this.list = list;
		this.layoutId=resId;
		mInflater = LayoutInflater.from(mContext);
        sp=mContext.getSharedPreferences("search_history_yfdef",mContext.MODE_PRIVATE);
        editor=sp.edit();

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView( final int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub
		temp_list=list;
		if (convertView == null) {
			convertView = mInflater.inflate(layoutId, null);
		}
		TextView tv = (TextView) convertView
				.findViewById(R.id.history_item);
		ImageView iv=(ImageView)convertView.findViewById(R.id.delete_img);
		tv.setText(temp_list.get(position).get("key"));
		convertView.setTag(temp_list.get(position).get("key"));
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "我要删除你", Toast.LENGTH_SHORT).show();
//                editor.remove(","+list.get(position).get("key"));
				String key=sp.getString("keywords", "");
				key=key.replace(","+temp_list.get(position).get("key"), "");
				Log.i("mylog", "此时的字符串："+key);
				editor.remove("keywords");
                editor.commit();
				Log.i("mylog", "第一次删除的sp："+sp.getString("keywords", ""));

				editor.putString("keywords", key);
				
//                editor.remove(","+temp_list.get(position).get("key"));
//                Log.i("mylog", ","+temp_list.get(position).get("key"));

                editor.commit();
				Log.i("mylog", "删除后的sp："+sp.getString("keywords", ""));

                temp_list.remove(list.get(position));
//                parent.removeViewAt(position);
                notifyDataSetChanged();  

			}
		});

		return convertView;
	}
	
	
	

}
