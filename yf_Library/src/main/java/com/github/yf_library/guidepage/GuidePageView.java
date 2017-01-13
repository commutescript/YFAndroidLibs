/**
 * @CopyRight(c) 2016年12月14日
 */
package com.github.yf_library.guidepage;

import java.util.ArrayList;
import java.util.List;

import com.github.yf_library.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author yihui
 * @version 1.0 2016年12月14日下午2:14:55
 */
public class GuidePageView extends RelativeLayout{
	
	private ViewPager vp; 
	private LinearLayout img_ll;
	private RelativeLayout jump_ll;
	private GuideAdapter mAdapter;
	private List<View> indicatorView;
	private List<View> contentView;
	private int currentIndex;  
    
	private int mPaddingLeft = 20;
	private int mPaddingTop = 20;
	private int mPaddingRight = 20;
	private int mPaddingBottom = 20;
	private View[] dots;
	private TextView jump_txt;
	private int lens;
	private GuidePageStyle mGuidePageViewStyle;
	View v;

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public GuidePageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub

	}

	/**
	 * @param context
	 * @param attrs
	 */
	public GuidePageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		v=LayoutInflater.from(context).inflate(R.layout.guide_page_view, this);
		vp=(ViewPager)v.findViewById(R.id.guide_vp);
		img_ll=(LinearLayout)v.findViewById(R.id.dot_ll);
		jump_ll=(RelativeLayout)v.findViewById(R.id.jump_ll);
		jump_txt=(TextView)v.findViewById(R.id.jump_txt);
		jump_txt.setVisibility(View.INVISIBLE);
		jump_txt.setGravity(Gravity.CENTER);
		mGuidePageViewStyle=new GuidePageStyle();
		

	}

	/**
	 * @param context
	 */
	public GuidePageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取样式对象
	 */
	public GuidePageStyle getGuidePageStyle(){
		return mGuidePageViewStyle;
		
	}
	
	/**
	 * 设置圆点，滑动的图片
	 */
	public void setData(int drawable, int[] imgs){
//		setStyle();
		//图片数量
		lens=imgs.length;
		//点
		initDots(lens, drawable);
		//视图
		initViews(imgs);
		
		vp.setOffscreenPageLimit(3);
		vp.setCurrentItem(0);

	}
	
	/**
	 * 设置跳转事件
	 */
	public void setJumpClick(OnClickListener listener){
		jump_txt.setOnClickListener(listener);
		
	}
	
	/**
	 * 初始化点
	 */
	@SuppressLint("NewApi")
	private void initDots(int lens,int drawable){
		dots=new View[lens];
        for(int i = 0; i < lens; i++){
            LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  
            		LinearLayout.LayoutParams.WRAP_CONTENT);  
        	ImageView img=new ImageView(getContext());
        	img.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight,
					mPaddingBottom);
        	img.setImageDrawable(getResources().getDrawable(drawable));
        	img.setLayoutParams(mParams);
        	img_ll.addView(img);
        	dots[i]=img;
        	
        }
        currentIndex = 0;  
        dots[currentIndex].setEnabled(false);
		
	}
	
	/**
	 * 初始化图片
	 */
	@SuppressLint("InflateParams")
	private void initViews(int[] imgs) {
		contentView=new ArrayList<View>();
        for(int i = 0; i < imgs.length; i++){
        	ImageView iv=new ImageView(getContext());
        	iv.setImageResource(imgs[i]);
//            View view=LayoutInflater.from(getContext()).inflate(R.layout.img, null);
//            view.setBackgroundResource(imgs[i]);
            iv.setScaleType(ScaleType.FIT_XY);
            contentView.add(iv);  
        	
        }
		vp.setAdapter(new MyViewPagerAdapter());
		vp.setOnPageChangeListener(mPageListener);
		
	}
	

	/**
	 * 设置适配器，暂时还在完善中
	 */
	public void setAdapter(GuideAdapter adapter){
		if(null!=adapter){
			setStyle();
			adapter.setGuidePageView(this);
			mAdapter=adapter;
			initView(adapter);
		}
		
	}
	
	/**
	 * 初始化图片
	 */
	private void initView(GuideAdapter adapter){
		if(null==adapter){
			return;
		}
		//点
		indicatorView=mAdapter.getIndicator();
		initIndicator(indicatorView);
		//视图
		contentView=mAdapter.getDisplayView();
		initContentView(contentView);
		
	}
	
	private void initIndicator(List<View> items){
		lens=items.size();
		Log.i("mylog", "数量"+items.size());
		dots=new View[lens];

        for (int i = 0; i < items.size(); i++) { 
        	items.get(i).setPadding(mPaddingLeft, mPaddingTop, mPaddingRight,
					mPaddingBottom);
        	img_ll.addView(items.get(i));
        	dots[i]=items.get(i);

        }  
        currentIndex = 0;  
        items.get(currentIndex).setEnabled(false);
        dots[currentIndex].setEnabled(false);
		
	}
	
	private void initContentView(List<View> contentItems){
		
		if (null == contentItems || 0 == contentItems.size())
		{
			return;
		}
		vp.setAdapter(new MyViewPagerAdapter());
		vp.setOnPageChangeListener(mPageListener);
		
	}
	
	/**
	 * viewpage适配器
	 * @author yihui
	 * @version 1.0 2016年12月14日下午3:40:07
	 */
	private class MyViewPagerAdapter extends PagerAdapter{
	  
	    //数量
	    @Override  
	    public int getCount() {  
	       return contentView.size();  
	          
	    }  
	      
	    //取得对象
	    @Override  
	    public Object instantiateItem(View arg0, int arg1) {  
	    	View v=contentView.get(arg1);
	        ((ViewPager) arg0).addView(v);  
	          
	        return v;  
	    } 
	    
	    //摧毁
	    @Override  
	    public void destroyItem(View arg0, int arg1, Object arg2) {  
			((ViewPager) arg0).removeView((View) arg2);
	    }  
	  
	    @Override  
	    public void finishUpdate(View arg0) {  
	        // TODO Auto-generated method stub  
	          
	    }  
	  
	    //判断 
	    @Override  
	    public boolean isViewFromObject(View arg0, Object arg1) {  
	        return (arg0 == arg1);  
	    }  
	  
	    @Override  
	    public void restoreState(Parcelable arg0, ClassLoader arg1) {  
	        // TODO Auto-generated method stub  
	          
	    }  
	  
	    @Override  
	    public Parcelable saveState() {  
	        // TODO Auto-generated method stub  
	        return null;  
	    }  
	  
	    @Override  
	    public void startUpdate(View arg0) {  
	        // TODO Auto-generated method stub  
	          
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

	        setCurDot(arg0); 
	        if(arg0==lens-1){
	        	jump_txt.setVisibility(View.VISIBLE);
	        }else {
	        	jump_txt.setVisibility(View.INVISIBLE);

			}

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
//			vp.setCurrentItem(currentIndex);

		}
		
	};
	
    /** 
     *设置当前位置
     */  
    @SuppressWarnings("unused")
	private void setCurView(int position)  
    {  
        if (position < 0 || position >= contentView.size()) {  
            return;  
        }  
        vp.setCurrentItem(position);  
        
    }  
  
    /** 
     *设置当前的点
     */  
    private void setCurDot(int positon)  
    {  
        if (positon < 0 || positon > contentView.size() - 1 || currentIndex == positon) {  
            return;  
        }  
        dots[positon].setEnabled(false);  
        dots[currentIndex].setEnabled(true);  
        currentIndex = positon;  
        
    }  
    
    /**
     * 设置样式
     */
    private void setStyle(){
    	
		jump_txt.setText(mGuidePageViewStyle.getBtnStr());
//		jump_txt.setBackgroundResource(mGuidePageViewStyle.getBtnStyle());
		jump_txt.setTextAppearance(getContext(), mGuidePageViewStyle.getBtnStyle());
		RelativeLayout.LayoutParams mParams=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		mParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		mParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mParams.addRule(Gravity.CENTER);
		mParams.setMargins(0, 0, 0, mGuidePageViewStyle.getBtnHeight());
		jump_txt.setGravity(Gravity.CENTER);
		if(mGuidePageViewStyle.getBtnHeight()!=0){
			jump_ll.setLayoutParams(mParams);

		}
		mParams.setMargins(0, 0, 0, mGuidePageViewStyle.getDotsHeight());
		img_ll.setLayoutParams(mParams);

    }
	
    
}
