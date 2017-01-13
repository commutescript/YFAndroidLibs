/**
 * @CopyRight(c) 2016年12月27日
 */
package com.github.yf_library.dw;

import java.util.ArrayList;
import java.util.List;

import com.github.yf_library.R;
import com.github.yf_library.common.ClearEditText;
import com.github.yf_library.dw.db.City;
import com.github.yf_library.dw.db.MyCityDBHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author yihui
 * @version 1.0 2016年12月27日下午2:56:15
 */
public class AddressView extends LinearLayout{
	
	private View rootView;
	
	private Context mContext;

	private ClearEditText mClearEditText;
	private TextView tv_mid_letter;
	private ListView listView;
	private MyLetterSortView right_letter;

	private AllCityAdapter mAdapter;
	private List<City> mlist;
	private InputMethodManager inputMethodManager;

	private View mCityContainer;
	private FrameLayout mSearchContainer;
	private ListView mSearchListView;
	private SearchCityAdapter mSearchCityAdapter;
	private ImageView btnback;
	Intent data;


	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 * @param defStyleRes
	 */
	@SuppressLint("NewApi")
	public AddressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public AddressView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public AddressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		rootView=LayoutInflater.from(context).inflate(R.layout.view_address_yfdef, this,true);
		mContext=context;
		inputMethodManager = (InputMethodManager) getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		listView = (ListView) findViewById(R.id.list);
		mClearEditText = (ClearEditText) findViewById(R.id.et_msg_search);
		right_letter = (MyLetterSortView) findViewById(R.id.right_letter);
		tv_mid_letter = (TextView) findViewById(R.id.tv_mid_letter);
		//返回按钮
		btnback=(ImageView)findViewById(R.id.back);

		right_letter.setTextView(tv_mid_letter);
		mCityContainer = findViewById(R.id.city_content_container);
		mSearchContainer = (FrameLayout) this.findViewById(R.id.search_content_container);
		mSearchListView = (ListView) findViewById(R.id.search_list);
		mSearchListView.setEmptyView(findViewById(R.id.search_empty));
		mSearchContainer.setVisibility(View.GONE);

	}

	/**
	 * @param context
	 */
	public AddressView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 传入包名
	 */
	public void setAddressData(String str){
//		initData(str);
		mlist = new ArrayList<City>();
		getDataCity(str);
		mAdapter = new AllCityAdapter(getContext(),mlist);
		listView.setEmptyView(findViewById(R.id.citys_list_load));
		listView.setAdapter(mAdapter);
		setLinstener();
	}
	
	
	protected void initData(String str) {
		mlist = new ArrayList<City>();
		getDataCity(str);
		mAdapter = new AllCityAdapter(getContext(),mlist);
		listView.setEmptyView(findViewById(R.id.citys_list_load));
		listView.setAdapter(mAdapter);
		
//		City city;
//		city=(City)mAdapter.getItem(listView.getFirstVisiblePosition());
//		city.getFirstPY();
//		
////		listView.setSelection(position);
////		listView.getSelectedItem();
//		listView.setOnScrollListener(new OnScrollListener() {
//			
//			@Override
//			public void onScrollStateChanged(AbsListView view, int scrollState) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//				// TODO Auto-generated method stub
//				
//			}
//		});

	}


	protected void setLinstener() {

		// tv_reget_pwd.setOnClickListener(this);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getContext(), "点击子项", Toast.LENGTH_SHORT).show();
//				T.showShort(getApplicationContext(),
//						((City) mAdapter.getItem(position)).toString());
//				Intent mintent=new Intent(LetterSortActivity.this,MainActivity.class);
//				mintent.putExtra("city",((City) mAdapter.getItem(position)).getCity());
//				Bundle bundle=new Bundle();
//				bundle.putString("city", ((City) mAdapter.getItem(position)).getCity());
//				data.putExtra("province", ((City) mAdapter.getItem(position)).getProvince());
//				data.putExtra("city", ((City) mAdapter.getItem(position)).getCity());
//				getContext().setResult(101, data);
//				Log.i("mylog", mintent.getStringExtra("city"));
//				System.out.println(mintent.getStringExtra("city"));
//				finish();

			}
		});

		listView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (((Activity)mContext).getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
					if (((Activity)mContext).getCurrentFocus() != null)
						inputMethodManager.hideSoftInputFromWindow(
								((Activity)mContext).getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				}
				return false;
			}
		});

		right_letter
				.setOnTouchingLetterChangedListener(new MyLetterSortView.OnTouchingLetterChangedListener() {

					@Override
					public void onTouchingLetterChanged(String s) {
						int position = mAdapter.getPositionForSection(s
								.charAt(0));
						if (position != -1) {
							listView.setSelection(position);
						}

					}
				});

		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
//				filterData2(s.toString());
				mSearchCityAdapter = new SearchCityAdapter(getContext(), mlist);

				mSearchListView.setAdapter(mSearchCityAdapter);
	            Log.i("mylog",  "适配器中得到的count"+mSearchCityAdapter.getCount());

				mSearchListView.setTextFilterEnabled(true);
				
				mCityContainer.setVisibility(View.INVISIBLE);
				mSearchContainer.setVisibility(View.VISIBLE);
				mSearchCityAdapter.getFilter().filter(s.toString());
				mSearchCityAdapter.notifyDataSetChanged();
				
				if (TextUtils.isEmpty(s.toString())) {
					mCityContainer.setVisibility(View.VISIBLE);
					mSearchContainer.setVisibility(View.INVISIBLE);

				} 
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		mSearchListView
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						Toast.makeText(getContext(), "我在点击子项", Toast.LENGTH_SHORT).show();
						// L.i(mSearchCityAdapter.getItem(position).toString());
//						data.putExtra("province", ((City) mSearchCityAdapter.getItem(position)).getProvince());
//						data.putExtra("city", ((City) mSearchCityAdapter.getItem(position)).getCity());
//						LetterSortActivity.this.setResult(101, data);
//						T.showLong(getApplicationContext(), mSearchCityAdapter
//								.getItem(position).toString());
//						finish();
					}
				});
	}


	private void getDataCity(final String str) {
//		Toast.makeText(getContext(),"建数据库",Toast.LENGTH_SHORT).show();
		new Thread(new Runnable() {
			@Override
			public void run() {
				MyCityDBHelper myCityDBHelper = new MyCityDBHelper(getContext(),str);

				mlist = myCityDBHelper.getCityDB().getAllCity();
				mHandler.sendEmptyMessage(0);

			}
		}).start();

	}


//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//
////		case R.id.back:
////			finish();
////			break;
//
//		//
//		default:
//			break;
//		}
//
//	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:

				mAdapter.updateListView(mlist);
				break;
			default:
				break;
			}
		}
	};

	

}
