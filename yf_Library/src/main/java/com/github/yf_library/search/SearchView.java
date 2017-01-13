/**
 * @CopyRight(c) 2016年12月16日
 */
package com.github.yf_library.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.yf_library.R;
import com.github.yf_library.common.ScrollListView;
import com.github.yf_library.tag.OnTagClickListener;
import com.github.yf_library.tag.TagEntity;
import com.github.yf_library.tag.TagView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 搜索框
 * @author yihui
 * @version 1.0 2016年12月16日上午10:14:48
 */
public class SearchView extends RelativeLayout{

    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    private ScrollView sc_view;
    private RelativeLayout nohistory;
    private TextView search_txt;
    private ScrollListView search_history_listview,suggest_list;
    private ScrollView suggest_view;
    private EditText search_edit;
    private Button clear_btu;
    private String keywords;
    private List list;
    
    private RelativeLayout rl01;
    private OnSerachClickListener mOnSerachClickListener;
    private SuggestAdapter mAutoCompeletAdapter;
    private List<String> msuggestData;
    private HistoryAdapter historyAdapter;
    private TagView hot_search_ll;
    private List<TagEntity> mTagEntity;
    private SearchStyle mSearchStyle;

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 * @param defStyleRes
	 */
	@SuppressLint("NewApi")
	public SearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub

	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.SearchView);//获取配置属性  
		View view=LayoutInflater.from(context).inflate(R.layout.search_view_yfdef, this,true);
		mSearchStyle=new SearchStyle();
		mSearchStyle.setHistoryResId(ta.getInt(R.styleable.SearchView_history_layoutId,
				mSearchStyle.getHistoryResId()));
		mSearchStyle.setSuggestResId(ta.getInt(R.styleable.SearchView_history_layoutId,
				mSearchStyle.getSuggestResId()));
		
		rl01=(RelativeLayout)view.findViewById(R.id.rl01);
		suggest_view=(ScrollView)view.findViewById(R.id.suggest_view);
        search_history_listview=(ScrollListView)findViewById(R.id.search_history_listview);
        search_edit=(EditText)view.findViewById(R.id.search_edit);
        
        search_txt=(TextView)view.findViewById(R.id.search_txt);
        sc_view=(ScrollView)view.findViewById(R.id.sc_view);
        clear_btu = (Button)view.findViewById(R.id.delete_history);
        nohistory=(RelativeLayout)view.findViewById(R.id.nohistory);
        suggest_list=(ScrollListView)view.findViewById(R.id.suggest_list);
        hot_search_ll=(TagView)view.findViewById(R.id.hot_search_ll);
        search_txt.setVisibility(View.GONE);
        search_edit.setHint(ta.getString(R.styleable.SearchView_search_hint));
        ta.recycle();
	}

	/**
	 * @param context
	 */
	public SearchView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public SearchStyle getSearchStyle(){
		return mSearchStyle;
	}
	
	/*
	 * 设置搜索框数据
	 */
	public void setSearchData(List<TagEntity> tagEntities, final List<String> suggestData,
			OnSerachClickListener onSerachClickListener){
		mOnSerachClickListener=onSerachClickListener;
		msuggestData=suggestData;
		mTagEntity=tagEntities;
        sp=getContext().getSharedPreferences("search_history_yfdef",getContext().MODE_PRIVATE);
        editor=sp.edit();
        isHistory();
        
        //点击搜索
        search_txt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                String keyword = search_edit.getText().toString().trim();
                if(keyword.equals("")){
                    Toast.makeText(getContext(),"请您输入搜索内容！！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"开始搜索！"+search_edit.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                    String keywords_temp = sp.getString("keywords", "");// 获取搜索历史，以逗号间隔
                    Log.i("mylog", "历史记录数据"+keywords_temp);

                    String keys[] = keywords_temp.split(",");
                    for (String key : keys) {
                        if (!key.equals("") && key.equals(keyword)) {
                            keywords_temp = keywords_temp.replace(key + ",", "");
                        }
                    }
                    String val = keywords_temp + "," + keyword;
                    editor.putString("keywords", val);
                    editor.commit();
                    Log.i("mylog", "历史记录数据:"+sp.getString("keywords", ""));

                }
                isHistory();
                mOnSerachClickListener.OnSearchclick();
                
			}
		});
        
        //根据文字变动，更新提示
        search_edit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				msuggestData=suggestData;
				mAutoCompeletAdapter = new SuggestAdapter(getContext(), suggestData,mSearchStyle.getSuggestResId());
	            mAutoCompeletAdapter.getFilter().filter(s.toString());
	            Log.i("mylog",  "适配器中得到的count"+mAutoCompeletAdapter.getCount());

				suggest_list.setAdapter(mAutoCompeletAdapter);
				suggest_list.setTextFilterEnabled(true);

	            rl01.setVisibility(View.GONE);
	            suggest_view.setVisibility(View.VISIBLE);
	            search_txt.setVisibility(View.VISIBLE);
//	            mAutoCompeletAdapter.getFilter().filter(s.toString());
	            mAutoCompeletAdapter.notifyDataSetChanged();
	            mAutoCompeletAdapter.getCount();

	            //根据s是否为空，判断呈现哪个界面
	    		if (TextUtils.isEmpty(s)) {
	    			rl01.setVisibility(View.VISIBLE);
	    			suggest_view.setVisibility(View.INVISIBLE);
		            search_txt.setVisibility(View.GONE);

	    		}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
	            search_txt.setVisibility(View.VISIBLE);

			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
        
        //清楚历史记录
        clear_btu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                editor.remove("keywords");// 清楚关键字
                editor.commit();
                Toast.makeText(getContext(),"清除成功！",Toast.LENGTH_SHORT).show();
                isHistory();
			}
		});
        
        //tag点击
        hot_search_ll.setTagsData(mTagEntity, new OnTagClickListener() {
			
			@Override
			public void setTagClick(TagEntity entity) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), entity.getTxt(), 1).show();
				if(entity.getmIntent()!=null){
					getContext().startActivity(entity.getmIntent());
				}
			}
		});
        
        
	}
	
	//将list定义为全局变量，重新调用可以起到刷新搜索历史界面的效果
	private void isHistory(){
	    list = new ArrayList();
        keywords = sp.getString("keywords", "");// 获取搜索历史，以逗号间隔
        boolean vis = false;
        // 查询搜索历史
        if (!keywords.equals("")) {
            String keyword[] = keywords.split(",");
            for (String val : keyword) {
                if (!val.equals("")) {
                    Map map = new HashMap();
                    map.put("key", val);
                    list.add(map);
                    vis = true;
                }
            }
        }

        if (vis) {
            // 历史记录存在
            sc_view.setVisibility(View.VISIBLE);
            nohistory.setVisibility(View.GONE);
            Collections.reverse(list);
            // 显示历史记录
            historyAdapter=new HistoryAdapter(getContext(), list,mSearchStyle.getHistoryResId());
            search_history_listview.setAdapter(historyAdapter);
            historyAdapter.notifyDataSetChanged();
//            isHistory();
            int totalHeight = 0;
            for (int i = 0; i < historyAdapter.getCount(); i++) {
                View listItem = historyAdapter.getView(i, null, search_history_listview);

                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = search_history_listview.getLayoutParams();
            params.height = totalHeight
                    + (search_history_listview.getDividerHeight() * (historyAdapter.getCount() - 1));
            search_history_listview.setLayoutParams(params);
        } else {
        	sc_view.setVisibility(View.INVISIBLE);
            nohistory.setVisibility(View.VISIBLE);
        }
	}
	
	public void setItemClick(final OnItemsClickListener listener){
        suggest_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
                String keyword01 = (String)view.getTag();
                String keywords_temp = sp.getString("keywords", "");// 获取搜索历史，以逗号间隔
                String keys[] = keywords_temp.split(",");
                for (String key : keys) {
                  if (!key.equals("") && key.equals(keyword01)) {
                      keywords_temp = keywords_temp.replace(key + ",", "");
                   }
                }
              String val = keywords_temp + "," + keyword01;
              editor.putString("keywords", val);
              editor.commit();

              isHistory();
			  listener.setClick((String)view.getTag());
			}
		});
        
        search_history_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				listener.setClick((String)view.getTag());

			}
		});
		
		
	}
	
	
//	//刷新数据
//	private void notifyDataChanged(){
//		setSearchData(mTagEntity,msuggestData,mOnSerachClickListener);
//	}

	

}
