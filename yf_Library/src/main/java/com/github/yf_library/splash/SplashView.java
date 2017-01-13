/**
 * @CopyRight(c) 
 */
package com.github.yf_library.splash;

import com.github.yf_library.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 广告闪屏布局
 * @author yihui
 * @version 1.0 2016年12月9日 下午4:58:04
 */
@SuppressLint("ShowToast")
public class SplashView extends RelativeLayout{
	
	private ImageView bg;
	private TextView time_bt;
	private TextView time;
	private TimeCount timeCount;
	private OnFinishListener onFinishListener;
	private long default_time=6000;
	private Drawable default_Drawable;
	private Drawable bt_Drawable;
	private LinearLayout time_ll;

	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 * @param defStyleRes
	 */
	@SuppressLint("NewApi")
	public SplashView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub

	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	@SuppressLint("NewApi")
	public SplashView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr,0);
		// TODO Auto-generated constructor stub

	}

	/**
	 * @param context
	 * @param attrs
	 */
	@SuppressLint("NewApi")
	public SplashView(Context context, AttributeSet attrs) {
		super(context, attrs,0);
		// TODO Auto-generated constructor stub
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.SplashView);//获取配置属性  

        default_time=ta.getInt(R.styleable.SplashView_time, 6000);
        default_Drawable=ta.getDrawable(R.styleable.SplashView_Bg);
        bt_Drawable=ta.getDrawable(R.styleable.SplashView_txt_style);
        ta.recycle();
		
		LayoutInflater.from(context).inflate(R.layout.splash_view, this);
		bg=(ImageView)findViewById(R.id.bg);
		time_bt=(TextView)findViewById(R.id.time_bt);
		time=(TextView)findViewById(R.id.time);
		time_ll=(LinearLayout)findViewById(R.id.time_ll);
		bg.setBackground(default_Drawable);
		time_ll.setBackground(bt_Drawable);
		
	}

	/**
	 * @param context
	 */
	public SplashView(Context context) {
		super(context,null);
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * 设置闪屏图片
	 */
	@SuppressLint("NewApi")
	public void setBg(int drawable){
		bg.setBackgroundResource(drawable);
		
	}
	
	/**
	 * 设置字体背景
	 */
	@SuppressLint("NewApi")
	public void setTextBg(int drawable){
		time_ll.setBackgroundResource(drawable);
		
	}
	
	/*
	 * 设置字体样式
	 */
	@SuppressLint("NewApi")
	public void setTxtStyle(int resId){
		time_ll.setBackgroundResource(resId);
	}
	
	/**
	 * 设置点击事件
	 */
	public void setJumpClick(OnClickListener listener){
		time_bt.setOnClickListener(listener);
		
	}
	
	/**
	 * 设置持续时间和下一activity
	 */
	public void setTime(long t,OnFinishListener listener){
		this.onFinishListener=listener;
		time.setText("("+(t/1000+1)+"s)");
		timeCount=new TimeCount(t+1000, 1000);
		timeCount.start();
		
	}
	
	/**
	 * 设置下一activity
	 */
	public void setTime(OnFinishListener listener){
		this.onFinishListener=listener;
		time.setText("("+(default_time/1000+1)+"s)");
		timeCount=new TimeCount(default_time+1000, 1000);
		timeCount.start();
		
	}
	
	class TimeCount extends CountDownTimer {

		/**
		 * @param millisInFuture
		 * @param countDownInterval
		 */
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			time.setText("("+(millisUntilFinished/1000)+"s)");
			
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			//计时完成
			time.setText("("+0+"s)");
			//跳转操作
			onFinishListener.setFinish();
			
		}
		
	}

	
}
