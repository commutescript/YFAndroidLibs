/**
 * @CopyRight(c) 2016年12月20日
 */
package com.github.yf_library.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;
import android.view.View;;

/**
 * @author yihui
 * @version 1.0 2016年12月20日上午10:31:13
 */
public class CustomTextView extends View implements View.OnClickListener{
	
	
    // 定义画笔
    private Paint mPaint;
    // 用于获取文字的宽和高
    private Rect mBounds;
    // 计数值，每点击一次本控件，其值增加1
    private int mCount;
    private String txt;

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 * @param defStyleRes
	 */
	@SuppressLint("NewApi")
	public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
        // 初始化画笔、Rect
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        // 本控件的点击事件
        setOnClickListener(this);
	}

	/**
	 * @param context
	 */
	public CustomTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	 @Override
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);

	        mPaint.setColor(Color.BLUE);
	        // 绘制一个填充色为蓝色的矩形
	        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
//	        canvas.drawRect(0, 0, mBounds.width(), mBounds.height(), mPaint);


	        mPaint.setColor(Color.YELLOW);
	        mPaint.setTextSize(50);
//	        String text = String.valueOf(mCount);
	        String text = txt;

	        // 获取文字的宽和高
	        mPaint.getTextBounds(text, 0, text.length(), mBounds);
	        float textWidth = mBounds.width();
	        float textHeight = mBounds.height();

	        // 绘制字符串
	        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2
	                + textHeight / 2, mPaint);
//	        canvas.drawText(text,textWidth , textHeight , mPaint);
	    }

	 public void setText(String str){
		 txt=str;
		 
	 }
	 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
        mCount ++;
        
        // 重绘
        invalidate();
		
	}
	 
	 


	
	
	
	

}
