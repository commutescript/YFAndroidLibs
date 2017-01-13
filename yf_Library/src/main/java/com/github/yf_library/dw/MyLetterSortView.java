package com.github.yf_library.dw;


import com.github.yf_library.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class MyLetterSortView extends View {
	//
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	// 26个字母
	public static String[] b = {"!","$","#",  "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };
	private int choose = -1;
	private int current_index=0;
	private Paint paint = new Paint();

	private TextView mTextDialog;

	public void setTextView(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}

	public MyLetterSortView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyLetterSortView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyLetterSortView(Context context) {
		super(context);
	}

	/**
	 * 重绘
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int height = getHeight();//
		int width = getWidth(); //
		int singleHeight = height / b.length;//

		for (int i = 0; i < b.length; i++) {
//			paint.setColor(Color.parseColor("#9da0a4"));
			
			paint.setColor(Color.parseColor("#F49484"));

			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
	      //	paint.setTextSize(PixelUtil.sp2px(12));
	    	paint.setTextSize(25);
	
			if (i == choose) {
				paint.setColor(Color.parseColor("#3399ff"));
				paint.setFakeBoldText(true);
			}
			
			
			if(i==getCurrent_index()){
				paint.setColor(Color.parseColor("#3399ff"));
				paint.setFakeBoldText(true);
			}
			float xPos = width / 2 - paint.measureText(b[i]) / 2;
			float yPos = singleHeight * i + singleHeight;
			canvas.drawText(b[i], xPos, yPos, paint);
			paint.reset();
		}

	}

	
	
	
	/**
	 * @return the current_index
	 */
	public int getCurrent_index() {
		return current_index;
	}

	/**
	 * @param current_index the current_index to set
	 */
	public void setCurrent_index(int current_index) {
		this.current_index = current_index;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY();//
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		final int c = (int) (y / getHeight() * b.length);

		switch (action) {
		case MotionEvent.ACTION_UP:
			setBackgroundDrawable(new ColorDrawable(0x00000000));
			choose = -1;//
			invalidate();
			if (mTextDialog != null) {
				mTextDialog.setVisibility(View.INVISIBLE);
			}
			break;

		default:
			setBackgroundResource(R.drawable.letter_sort_bg);
			if (oldChoose != c) {
				if (c >= 0 && c < b.length) {
					if (listener != null) {
						listener.onTouchingLetterChanged(b[c]);
					}
					if (mTextDialog != null) {
						mTextDialog.setText(b[c]);
						mTextDialog.setVisibility(View.VISIBLE);
					}
					
					choose = c;
					invalidate();
				}
			}

			break;
		}
		return true;
	}

	/**
	 *
	 * 
	 * @param onTouchingLetterChangedListener
	 */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	
	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String s);
	}

}
