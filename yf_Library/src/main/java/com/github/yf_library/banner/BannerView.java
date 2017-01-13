/**
 * @CopyRight(c) 2016年12月14日
 */
package com.github.yf_library.banner;

import java.util.ArrayList;
import java.util.List;
import com.github.yf_library.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 广告类,暴露接口,只要两个方法，待扩展
 * @author yihui
 * @version 1.0 2016年12月14日下午8:47:05
 */
public class BannerView extends Fragment{
	
	protected BannerStyle mBannerStyle;
	private List<BannerEntity> adList;
	private List<ImageView> imageViews= new ArrayList<ImageView>();// 滑动的图片集合
	private RelativeLayout info_rl;

	private LinearLayout dot_ll;
	private boolean isCycle = true; // 是否循环
	private boolean isWheel = true; // 是否轮播
	private boolean isScrolling = false; // 滚动框是否滚动着
	private int WHEEL = 100; // 转动
	private int WHEEL_WAIT = 101; // 等待

	private ImageView[] indicators;
	private TextView tv_date;
	private TextView tv_title;
	private TextView tv_topic_from;
	private TextView tv_topic;
	private int currentItem = 0; // 当前图片的索引号
	private List<View> dots; // 图片标题正文的那些点
	private View view;
	OnImageClickListener mImageClickListener;
	private TextView textView;

	private ViewPager banner_vp;
	LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(15,  
            15);  
	private long releaseTime = 0; // 手指松开、页面不滚动时间，防止手机松后短时间进行切换
	
	private Handler handler;


	@SuppressLint("HandlerLeak")
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=LayoutInflater.from(getActivity()).inflate(R.layout.banner_view_yfdef, null);
		dot_ll=(LinearLayout)view.findViewById(R.id.banner_dot_ll);
		dot_ll.setPadding(15, 15, 15, 15);
		info_rl=(RelativeLayout)view.findViewById(R.id.info);
		tv_date = (TextView) view.findViewById(R.id.tv_date);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_topic_from = (TextView)view.findViewById(R.id.tv_topic_from);
		tv_topic = (TextView)view.findViewById(R.id.tv_topic);
		banner_vp=(ViewPager)view.findViewById(R.id.banner_vp);
		mBannerStyle=new BannerStyle();
		configImageLoader();
		
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == WHEEL && imageViews.size() != 0) {
					if (!isScrolling) {
						int max = imageViews.size() + 1;
						int position = (currentItem + 1) % imageViews.size();
						banner_vp.setCurrentItem(position, true);
						if (position == max) { // 最后一页时回到第一
							banner_vp.setCurrentItem(1, false);
						}
					}
					releaseTime = System.currentTimeMillis();
					handler.removeCallbacks(runnable);
					handler.postDelayed(runnable, mBannerStyle.getTime());
					return;
				}
				if (msg.what == WHEEL_WAIT && imageViews.size() != 0) {
					handler.removeCallbacks(runnable);
					handler.postDelayed(runnable, mBannerStyle.getTime());
				}
			}
		};

		return view;
	}
	
	public void setBannerData(List<BannerEntity> items,OnImageClickListener listener){
		mImageClickListener=listener;
		this.imageViews.clear();
		if(mBannerStyle.isShowInfo()==true){
			info_rl.setVisibility(View.VISIBLE);

		}else {
			info_rl.setVisibility(View.INVISIBLE);

		}
		Log.i("mylog", "数量"+items.size());
		// 广告数据
		adList = items;
		//处理点
		initDots(items.size()+2);
		//处理图片
		initPics(items.size());
		
		banner_vp.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		banner_vp.setOnPageChangeListener(new MyPageChangeListener());
		//设置布局
		setLayout();
		banner_vp.setCurrentItem(1);
		banner_vp.setOffscreenPageLimit(3);
		setWheel(isWheel);

	}
	
	/*
	 * 获取样式对象 
	 */
	public BannerStyle getBannerStyle(){
		return mBannerStyle;
		
	}
	@SuppressLint("NewApi")
	private void initDots(int len){
		dots = new ArrayList<View>();  

		switch (mBannerStyle.getmIndicatorStyle()) {
		
		case NUM:
			textView =new TextView(getActivity());
			textView.setText("1/5");
//			textView.setTextColor(Color.rgb(255,255,255));
			textView.setBackground(getResources().getDrawable(mBannerStyle.getTextBg()));
			if(mBannerStyle.getTextApperance()!=0){
				textView.setTextAppearance(getActivity(), mBannerStyle.getTextApperance());
			}
			dot_ll.addView(textView);
			break;
		case INDIC:
			// 设置指示
			indicators = new ImageView[len];
			if (isCycle)
				indicators = new ImageView[len - 2];
			dot_ll.removeAllViews();
			for(int i=0;i<indicators.length;i++){
				View v=LayoutInflater.from(getActivity()).inflate(R.layout.dots_yfdef, null);
				v.setBackgroundResource(mBannerStyle.getDotsNormal());
				mParams.setMargins(10, 0, 10, 0);
	            v.setLayoutParams(mParams);  
				dots.add(v);
				dot_ll.addView(v);
			}
			break;
		case IMG:
			// 设置指示
			indicators = new ImageView[len];
			if (isCycle)
				indicators = new ImageView[len - 2];
			dot_ll.removeAllViews();
			for(int i=0;i<indicators.length;i++){
				View v=LayoutInflater.from(getActivity()).inflate(R.layout.dots_yfdef, null);
				v.setBackgroundResource(mBannerStyle.getDotsNormal());
				mParams.setMargins(10, 0, 10, 0);
	            v.setLayoutParams(mParams);  
				dots.add(v);
				dot_ll.addView(v);
			}
			break;

		default:
			break;
		}

		
	}
	
	private void initPics(int len){
		imageViews.add(ImgFactory.getImageView(getActivity(), adList.get(len-1).getImgUrl()));

		for(int j=0;j<len;j++){
			imageViews.add(ImgFactory.getImageView(getActivity(), adList.get(j).getImgUrl()));
			
		}
		// 异步加载图片
		imageViews.add(ImgFactory.getImageView(getActivity(), adList.get(0).getImgUrl()));
		
	}

	/*
	 * 滑动listener
	 */
	private class MyPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			if (arg0 == 1) { // viewPager在滚
				isScrolling = true;
				return;
			} else if (arg0 == 0) { // viewPager滚动结束
				releaseTime = System.currentTimeMillis();
				banner_vp.setCurrentItem(currentItem, false);
				
			}
			isScrolling = false;

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			int max = imageViews.size() - 1;
			int position = arg0;
			currentItem = arg0;

			if (isCycle) {
				if (position == 0) {
					currentItem = max - 1;
				} else if (position == max) {
					currentItem = 1;
				}
				position = currentItem - 1;
			}
			BannerEntity adDomain = adList.get(position);
			tv_title.setText(adDomain.getTitle()); // 设置标题
			tv_date.setText(adDomain.getDate());
			tv_topic_from.setText(adDomain.getTopicFrom());
			tv_topic.setText(adDomain.getTopic());
			switch (mBannerStyle.getmIndicatorStyle()) {
			case INDIC:
				//将点添加到list
				for(int i=0;i<dots.size();i++){
					dots.get(i).setBackgroundResource(mBannerStyle.getDotsNormal());
				}
				dots.get(position).setBackgroundResource(mBannerStyle.getDotsSelected());
				
				break;
			case NUM:
				textView.setText((position+1)+"/"+adList.size());
				
				break;
			case IMG:
				//该选项还在扩展中
				textView.setText((position+1)+"/"+adList.size());

				break;

			default:
				break;
			}

		}
	}

	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageViews.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView iv = imageViews.get(position);
			((ViewPager) container).addView(iv);
			// 在这个方法里面设置图片的点击事件
			iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// 处理跳转逻辑
					mImageClickListener.onImageClick(adList.get(currentItem - 1), currentItem, v);

				}
			});
			return iv;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
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

		@Override
		public void finishUpdate(View arg0) {

		}

	}
	/**
	 * 设置指示器的位置
	 */
	private void setLayout(){
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 10, 0, 10);
		//水平设置
		switch (mBannerStyle.getmHorizontalAlignment()) {
		case CENTER:
			params.addRule(RelativeLayout.CENTER_HORIZONTAL);
			break;
			
		case LEFT:
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			break;

		case RIGHT:
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			break;

		default:
			params.addRule(RelativeLayout.CENTER_HORIZONTAL);

			break;
		}
		
		//垂直设置
		switch (mBannerStyle.getmVerticalAlignment()) {
		case CENTER:
			params.addRule(RelativeLayout.CENTER_HORIZONTAL);
			break;
			
		case BOTTOM:
			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;

		case TOP:
			params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			break;

		default:
			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			break;
		}
		dot_ll.setLayoutParams(params);
	}
	
	final Runnable runnable = new Runnable() {

		@Override
		public void run() {
			if (getActivity() != null && !getActivity().isFinishing()
					&& isWheel) {
				long now = System.currentTimeMillis();
				// 测上次滑动时间与本次之间是否有(手滑)操作，有的话等待下次轮播
				if (now - releaseTime > mBannerStyle.getTime() - 500) {
					handler.sendEmptyMessage(WHEEL);
				} else {
					handler.sendEmptyMessage(WHEEL_WAIT);
				}
			}
		}
	};

	private void setWheel(boolean isWheel) {
		this.isWheel = isWheel;
		isCycle = true;
		if (isWheel) {
			handler.postDelayed(runnable, mBannerStyle.getTime());
		}
	}
	
	
	/**
	 * 配置ImageLoder
	 */
	private void configImageLoader() {
		// 初始化ImageLoader
		@SuppressWarnings("deprecation")
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(mBannerStyle.getDefImg()) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(mBannerStyle.getDefImg()) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(mBannerStyle.getDefImg()) // 设置图片加载或解码过程中发生错误显示的图
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图
				.build(); // 创建配置过得DisplayImageOption对象

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity()).defaultDisplayImageOptions(options)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);		
	}
}
