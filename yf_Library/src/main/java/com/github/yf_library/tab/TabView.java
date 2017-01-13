/**
 * @CopyRight(c) 2016年12月14日
 */
package com.github.yf_library.tab;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import com.github.yf_library.R;
import com.github.yf_library.tab.TabStyle.TabPositionType;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 底部导航
 * @author yihui
 * @version 1.0 2016年12月14日下午8:47:33
 */
public class TabView extends RelativeLayout{
	
	private View rootView;
	private ViewPager tab01_vp;
	private LinearLayout tab_ll;
	private ArrayList<View> actvity_list;
	LocalActivityManager manager = null;
	private int offset = 0;// 动画图片偏移量
	private int bmpW;// 动画图片宽度
	
	private TabStyle mTabViewStyle;
	private RelativeLayout tab_rl;

	private String[] titles=new String[]{};
	private int[] mpics=new int[]{};
	List<ViewHolder> holdList;
	
	private RadioGroup rg_items;
	private List<RadioButton> rb_items = new ArrayList<RadioButton>();
//	private MyViewPager vp_content;
	private Context mContext;
	private ColorStateList mColors;
	private HorizontalScrollView hsv_menu;
	private int mBackgroundResId;

	private int mPaddingLeft = 40;
	private int mPaddingTop = 20;
	private int mPaddingRight = 40;
	private int mPaddingBottom = 20;
	private boolean[] mVisitStatus; // 菜单访问状态

	private boolean mSwiped = true; // 是否可滑动


	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public TabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//更具传入的style，判断加载哪一个布局
		mContext=context;
		mTabViewStyle=new TabStyle();
		rootView= LayoutInflater.from(context).inflate(R.layout.tab_view_yfdef, this,true);
		tab01_vp=(ViewPager)rootView.findViewById(R.id.tab01_vp);
		tab_rl=(RelativeLayout)rootView.findViewById(R.id.tab_rl);
		tab_ll=(LinearLayout)rootView.findViewById(R.id.tab01_ll);
		rg_items = (RadioGroup) rootView.findViewById(R.id.rg_items);
//		vp_content = (MyViewPager) rootView.findViewById(R.id.vp_content);
		mColors = getResources().getColorStateList(
				R.drawable.radio_color_selector);
		hsv_menu = (HorizontalScrollView) rootView.findViewById(R.id.hsv_menu);
		mBackgroundResId = R.drawable.bg_rb_checked;
		
	}

	/**
	 * @param context
	 */
	public TabView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public TabStyle getTabViewStyle(){
		return mTabViewStyle;
		
	}
	
	@SuppressWarnings("deprecation")
	public void setTabData(LocalActivityManager manager, String[] txt, int[] pics, List<Intent> items){
		if(txt.length!=pics.length){
			Toast.makeText(getContext(), "输入图片数和标题数不一样，请重新输入！", Toast.LENGTH_LONG).show();
		}
		titles=txt;
		mpics=pics;

		initTab(titles,mpics);

		initActvity(items,manager);
		
	}
	
	
	/*
	 * 处理tab
	 */
	private void initTab(String[] txt, int[] pics){
		//根据style加载
		LinearLayout ll;

		//设置默认高度，150
		if(mTabViewStyle.getTabHeight()==0){
			mTabViewStyle.setTabHeight(150);
		}
		//tab_rl
		RelativeLayout.LayoutParams rl_Params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				mTabViewStyle.getTabHeight());
		
		//tab_ll
		RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		//page
		RelativeLayout.LayoutParams page_Params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		//ll
		LinearLayout.LayoutParams params = new LinearLayout.
				LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		if(mTabViewStyle.getTabBackGround()==0){
			mTabViewStyle.setTabBackGround(Color.CYAN);
		}
		tab_ll.setBackgroundColor(mTabViewStyle.getTabBackGround());
		tab_rl.setLayoutParams(rl_Params);

		holdList=new ArrayList<TabView.ViewHolder>();
		for(int i=0; i<txt.length;i++){
			//组装tab
			ViewHolder viewHolder=new ViewHolder();
			ll=new LinearLayout(getContext());

			ll.setOrientation(LinearLayout.VERTICAL);
		
			if(mTabViewStyle.getmPositionType()!=TabPositionType.top){
				params.weight=1;

			}
			params.gravity=Gravity.CENTER;
			ll.setGravity(Gravity.CENTER);
			ll.setLayoutParams(params);
			ll.setWeightSum(2);
			
			viewHolder.mTextView=new TextView(getContext());
			viewHolder.mImageView=new ImageView(getContext());
			viewHolder.mTextView.setText(txt[i]);
			viewHolder.mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
			viewHolder.mTextView.setTextColor(mTabViewStyle.getmTextColor());
			viewHolder.mTextView.setBackgroundResource(mTabViewStyle.getmTextStyle());
			viewHolder.mImageView.setImageResource(pics[i]);
			ll.addView(viewHolder.mImageView);
			ll.addView(viewHolder.mTextView);
			ll.setTag(i);
			holdList.add(viewHolder);

			tab_ll.addView(ll);
			
			ll.setOnClickListener(new MyOnClickListener(i));

		}
		

//		rl.addView(tab_ll);
		
		//根据导航条的位置，加载不同的布局
		switch (mTabViewStyle.getmPositionType()) {
		
		case bottm:
			page_Params.addRule(RelativeLayout.ABOVE,tab_rl.getId());
			rl_Params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			tab_ll.setWeightSum(txt.length);
			tab_rl.setLayoutParams(rl_Params);

			tab_ll.setLayoutParams(mParams);


			tab01_vp.setLayoutParams(page_Params);
			
			break;
		case top:
			page_Params.addRule(RelativeLayout.BELOW,tab_rl.getId());
//			mParams.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
//			tab_ll.setWeightSum(txt.length);
			tab_ll.setGravity(Gravity.CENTER_HORIZONTAL);

			tab_ll.setLayoutParams(mParams);
			
			tab01_vp.setLayoutParams(page_Params);
			ImageView back=new ImageView(getContext());
			back.setImageResource(R.drawable.icon_back_yfdef);
			LinearLayout.LayoutParams pms=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			pms.gravity=Gravity.LEFT;
			tab_rl.addView(back);
			//默认去掉图片
			for(int i=0;i<holdList.size();i++){
				holdList.get(i).mImageView.setVisibility(View.GONE);
			}
			break;
		case middle_fixed:
			page_Params.addRule(RelativeLayout.BELOW,tab_rl.getId());
			tab_ll.setWeightSum(txt.length);
			

			tab_ll.setLayoutParams(mParams);
			tab01_vp.setLayoutParams(page_Params);
			//默认去掉图片
			for(int i=0;i<holdList.size();i++){
				holdList.get(i).mImageView.setVisibility(View.GONE);
			}
			break;
		case middle_movable:
			page_Params.addRule(RelativeLayout.BELOW,hsv_menu.getId());
			hsv_menu.setLayoutParams(rl_Params);
			tab01_vp.setLayoutParams(page_Params);
			
			hsv_menu.setVisibility(View.VISIBLE);
			tab_rl.setVisibility(View.GONE);
			
			if (null != txt && 0 != txt.length)
			{
				rg_items.setOnCheckedChangeListener(mItemListener);
				for (String str : txt)
				{
					RadioButton rb_item = (RadioButton) LayoutInflater.from(
							getContext()).inflate(R.layout.radiobtn_item_yfdef, null);
//					RadioButton rb_item = new RadioButton(mContext);
					rb_item.setTextColor(mColors);
					rb_item.setText(str);
					rb_item.setGravity(Gravity.CENTER);
					rb_item.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight,
							mPaddingBottom);
					rg_items.addView(rb_item);
					rb_items.add(rb_item);
				}
				rb_items.get(0).setChecked(true);
			}
			
			break;
		default:
			break;
		}
		
		
		
	}
	
	/*
	 * 加载Intent
	 */
	@SuppressWarnings("deprecation")
	private void initActvity(List<Intent> items,LocalActivityManager manager) {
		actvity_list=new ArrayList<View>();
		for(int i=0;i<items.size();i++){
			//组装actvity或者fragment
			actvity_list.add(getView(manager,"mIntent", items.get(i)));
		}
		if(mTabViewStyle.getmPositionType()==TabPositionType.middle_movable){
			tab01_vp.setAdapter(new MyViewPagerAdapter(actvity_list));
			tab01_vp.setOnPageChangeListener(mPageListener);
		}else {
			tab01_vp.setAdapter(new MyPagerAdapter(actvity_list));
			tab01_vp.setCurrentItem(0,false);
			holdList.get(0).mTextView.setTextColor(mTabViewStyle.getmTextColorSelected());

			tab01_vp.setOnPageChangeListener(new MyOnPageChangeListener());
		}

		


	}
	
	/**
	 * Pager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter{
		List<View> list =  new ArrayList<View>();
		public MyPagerAdapter(ArrayList<View> list) {
			this.list = list;
		}

		@Override
		public void destroyItem(ViewGroup container, int position,
				Object object) {
			ViewPager pViewPager = ((ViewPager) container);
			pViewPager.removeView(list.get(position));
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object instantiateItem(View arg0, int arg1) {
			ViewPager pViewPager = ((ViewPager) arg0);
			pViewPager.addView(list.get(arg1));
			return list.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}
	
	
	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量

		@Override
		public void onPageSelected(int arg0) {
			for(int i=0;i<holdList.size();i++){
				holdList.get(i).mTextView.setTextColor(mTabViewStyle.getmTextColor());
			}
			holdList.get(arg0).mTextView.setTextColor(mTabViewStyle.getmTextColorSelected());
//			switch (arg0) {
//			case 0:
//				if (currIndex == 1) {
//					animation = new TranslateAnimation(one, 0, 0, 0);
//				} else if (currIndex == 2) {
//					animation = new TranslateAnimation(two, 0, 0, 0);
//				}
//				break;
//			case 1:
//				if (currIndex == 0) {
//					animation = new TranslateAnimation(offset, one, 0, 0);
//				} else if (currIndex == 2) {
//					animation = new TranslateAnimation(two, one, 0, 0);	
//				}
//				break;
//			case 2:
//				if (currIndex == 0) {
//					animation = new TranslateAnimation(offset, two, 0, 0);
//				} else if (currIndex == 1) {
//					animation = new TranslateAnimation(one, two, 0, 0);
//				}
//				break;
//			}
//			currIndex = arg0;
//			animation.setFillAfter(true);// True:图片停在动画结束位置
//			animation.setDuration(300);
//			cursor.startAnimation(animation);
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
	}
	
	
	@SuppressWarnings("deprecation")
	private View getView(LocalActivityManager manager,String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}
	
	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private ViewHolder mHolder = new ViewHolder();
		int clickInt;

		public MyOnClickListener(int i) {
			clickInt = i;
		}
		

		@Override
		public void onClick(View v) {
			for(int i=0;i<holdList.size();i++){
				holdList.get(i).mTextView.setTextColor(mTabViewStyle.getmTextColor());
			}
			tab01_vp.setCurrentItem(clickInt);
			holdList.get((Integer) v.getTag()).mTextView.setTextColor(mTabViewStyle.getmTextColorSelected());

		}
	};
	
	private class ViewHolder{
		TextView mTextView;
		ImageView mImageView;
		
	}
	
	/**
	 * radiobutton菜单项切换监听器
	 */
	private OnCheckedChangeListener mItemListener = new OnCheckedChangeListener()
	{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId)
		{
			// TODO Auto-generated method stub
			RadioButton btn = (RadioButton) group.findViewById(checkedId);
			setMenuItemsNullBackground();
			btn.setBackgroundResource(mBackgroundResId);
			btn.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight,
					mPaddingBottom);
			int position = 0;
			for (int i = 0; i < rb_items.size(); i++)
			{
				if (rb_items.get(i) == btn)
				{
					position = i;
				}
			}
			tab01_vp.setCurrentItem(position);
			moveItemToCenter(btn);
//			mAdapter.onPageChanged(position, mVisitStatus[position]);
//			mVisitStatus[position] = true;
		}

	};
	/**
	 * 设置没有菜单项的背景为trasparent
	 */
	private void setMenuItemsNullBackground()
	{
		if (null != rg_items)
			for (int i = 0; i < rg_items.getChildCount(); i++)
			{
				View v = rg_items.getChildAt(i);
				v.setBackgroundResource(android.R.color.transparent);
			}
	}
	
	/**
	 * 将菜单项尽量移至中央位置
	 * 
	 * @param rb
	 */
	private void moveItemToCenter(RadioButton rb)
	{
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;
		int[] locations = new int[2];
		rb.getLocationInWindow(locations);
		int rbWidth = rb.getWidth();
		hsv_menu.smoothScrollBy((locations[0] + rbWidth / 2 - screenWidth / 2),
				0);
	}
	
	/**
	 * 视图页的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	static class MyViewPagerAdapter extends PagerAdapter
	{
		private List<View> mViews;

		public MyViewPagerAdapter(List<View> views)
		{
			// TODO Auto-generated constructor stub
			mViews = views;
		}

		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return mViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			// TODO Auto-generated method stub
			container.removeView(mViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			// TODO Auto-generated method stub
			container.addView(mViews.get(position));
			return mViews.get(position);
		}
	}
	
	/**
	 * 内容页切换监听器
	 */
	private OnPageChangeListener mPageListener = new OnPageChangeListener()
	{

		@Override
		public void onPageSelected(int arg0)
		{
			// TODO Auto-generated method stub
			rb_items.get(arg0).setChecked(true);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0)
		{
			// TODO Auto-generated method stub

		}
	};
	
}
