package com.github.yf_library.tag;

import java.util.ArrayList;
import java.util.List;

import com.github.yf_library.R;
import com.github.yf_library.tag.TagViewStyle.TagStyle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 能根据子控件自动换行，同时把行空白空间平均分配到各行子控件中的容器
 * @author yihui
 * @since 创建时间：2015-5-09 下午02:15:22
 * 
 */
public class TagView extends ViewGroup {
	private final static int VIEW_MARGIN=10;
    private int defalutSize;
    private TagViewStyle mTagViewStyle;
    static private OnTagClickListener mOnTagClickListener;
    int  flag=0;
    private List<TagEntity> tagList=new ArrayList<TagEntity>();

	//===============================================================================
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
//        LayoutParams mLayoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        MarginLayoutParams cParams = null; 
        final int count = getChildCount();
         final int parentWidth = this.getMeasuredWidth();  //保存当前容器的宽度值
         int row=0;				// which row lay you view relative to parent
         int curLeft = VIEW_MARGIN;
         int curRight = 0;
         int curTop  = VIEW_MARGIN;
         int curRowHeight = 0;  //保存当前行的最大高度
         int curSpace = 0;	    //保存当前行应伸展或缩小的宽度变化值
         int curCol = 1;
         for(int i=0;i<count;i++){
         	final View child = getChildAt(i);  //得到子控件及宽、高值
        	int childWidth = child.getMeasuredWidth();
        	int childHeight = child.getMeasuredHeight();  

        	if(row<rowList.size()){  //获取到当前行的排版信息
	        	curRowHeight = rowList.get(row).maxHeight;  //获取到当前行的最大高度值
	        	curCol =  rowList.get(row).cols;
	        	if(curCol>0){
		        	curSpace = rowList.get(row).space / curCol;
	        	}
        	}
        	curRight = curLeft + childWidth + VIEW_MARGIN;  //得到当前子控件的右边位置
        	if(curRight>parentWidth){  //如果当前控件超出了父控件宽度。则换行排放
        		if(curCol==1&&i==0){ //如果第一行同时只是一个子控件超过父控件了，则缩小放置
        			child.layout(curLeft, curTop+((curRowHeight-childHeight)/2), curLeft+childWidth, curTop+((curRowHeight-childHeight)/2)+childHeight); //让高度小于行最大高度的控件，垂直居中显
//        			child.setLayoutParams(mLayoutParams);
//        			mparams= new LayoutParams(child.getWidth(), LayoutParams.WRAP_CONTENT);
//        			child.setLayoutParams(mparams);
        			curLeft = VIEW_MARGIN;  //复位左侧放置点到下一行最左边
        			curTop += curRowHeight+VIEW_MARGIN;  //更新高度定位点为下一行的高度
        			row++;
        		}else{
            		curLeft = VIEW_MARGIN;  //复位左侧放置点到下一行最左边
            		curTop += curRowHeight+VIEW_MARGIN;  //更新高度定位点为下一行的高度
            		curRight = curLeft + childWidth + VIEW_MARGIN;  //得到当前子控件的右边位置
            		row++;
            		if(row<rowList.size()){  //获取到当前行的排版信息
        	        	curRowHeight = rowList.get(row).maxHeight;  //获取到当前行的最大高度值
        	        	curCol =  rowList.get(row).cols;
        	        	if(curCol>0){
        		        	curSpace = rowList.get(row).space / curCol;
        	        	}
                	}
            		if(curRight>parentWidth){  //如果换行后，直接大于父控件宽度，说明当前子控件自己就宽过父控件，则直接按一行一个控件处理
            			child.layout(curLeft, curTop+((curRowHeight-childHeight)/2), curLeft+childWidth, curTop+((curRowHeight-childHeight)/2)+childHeight); //让高度小于行最大高度的控件，垂直居中显
//            			child.setLayoutParams(mLayoutParams);
//            			mparams= new LayoutParams(child.getWidth(), childHeight);
//            			child.setLayoutParams(mparams);
            			curLeft = VIEW_MARGIN;  //复位左侧放置点到下一行最左边
            			curTop += curRowHeight+VIEW_MARGIN;  //更新高度定位点为下一行的高度
            			row++;
            		}else{
                		if(mTagViewStyle.getmTagStyle()==TagStyle.wrap){
                    		child.layout(curLeft, curTop+((curRowHeight-childHeight)/2), curLeft+childWidth, curTop+((curRowHeight-childHeight)/2)+childHeight); //让高度小于行最大高度的控件，垂直居中显
//                			mparams= new LayoutParams(child.getWidth(), childHeight);
//                			child.setLayoutParams(mparams);
                    		curLeft = curLeft+childWidth+VIEW_MARGIN;  //更新左侧放置点到新放控件后面
                		}else {
                    		child.layout(curLeft, curTop+((curRowHeight-childHeight)/2), curLeft+childWidth+curSpace, curTop+((curRowHeight-childHeight)/2)+childHeight); //让高度小于行最大高度的控件，垂直居中显
//                			mparams= new LayoutParams(childWidth+curSpace, childHeight);
//                			child.setLayoutParams(mparams);
                    		curLeft = curLeft+childWidth+curSpace+VIEW_MARGIN;  //更新左侧放置点到新放控件后面
						}

            		}
        		}        		
        	}else{ //未超出，则直接排放位置

        		if(mTagViewStyle.getmTagStyle()==TagStyle.wrap){
            		child.layout(curLeft, curTop+((curRowHeight-childHeight)/2), curLeft+childWidth, curTop+((curRowHeight-childHeight)/2)+childHeight); //让高度小于行最大高度的控件，垂直居中显
//        			child.setLayoutParams(mLayoutParams);
//        			mparams= new LayoutParams(child.getWidth(), childHeight);
//        			child.setLayoutParams(mparams);
            		curLeft = curLeft+childWidth+VIEW_MARGIN;  //更新左侧放置点到新放控件后面
        		}else {
            		
        			child.layout(curLeft, curTop+((curRowHeight-childHeight)/2), curLeft+childWidth+curSpace, curTop+((curRowHeight-childHeight)/2)+childHeight); //让高度小于行最大高度的控件，垂直居中显
//        			child.setLayoutParams(mLayoutParams);
//        			mparams= new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
////        			mparams.
//        			child.setLayoutParams(mparams);

        			curLeft = curLeft+childWidth+curSpace+VIEW_MARGIN;  //更新左侧放置点到新放控件后面
            		

				}


        	}
            child.setOnClickListener(modelidButtonOnClick);
            
//            if(((TagEntity)child.getTag()).getSingleFlag()!=1){
//            	child.setSelected(false);
//            	child.setBackgroundResource(R.drawable.tag_seletor);
//            }


        	
         }
         singleCilck();

	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);  //使用FrameLayout的测量方法，让子控件计算出自己的大小
        measureChildren(widthMeasureSpec, heightMeasureSpec);

		final int parentWidth = this.getMeasuredWidth();  //保存当前容器的宽度值
		final int count = getChildCount();
		if(initChildCount!=count||parentWidth!=initParentWidth){  //这样处理可以提高效率，不用在相同宽度和相同子控件时，一直重新排列
			initChildCount = count;
			initParentWidth = parentWidth;
	        rowList.clear();
			int curLeft = VIEW_MARGIN;
			int rowMaxHeight = 0;
			int totalHeight =VIEW_MARGIN;
			int rowChildCount=0;
			int childWidth = 0;
			int childHeight = 0;
			for(int i=0;i<count;i++){
				final View child = getChildAt(i);
	            measureChild(child, widthMeasureSpec, heightMeasureSpec);  
				childWidth = child.getMeasuredWidth();
				childHeight = child.getMeasuredHeight();
				curLeft += (childWidth+VIEW_MARGIN);			
				rowChildCount++;
				if(curLeft>=parentWidth){
					if(rowChildCount<2){
						rowList.add(new RowInfo(rowChildCount, parentWidth-VIEW_MARGIN-childWidth-VIEW_MARGIN, childHeight));  //第一个子控件就超过父控件宽度时，直接缩小到同样宽度
						curLeft = VIEW_MARGIN;	
						rowChildCount =0;
						rowMaxHeight = 0;
						totalHeight += childHeight+VIEW_MARGIN;
					}else{
						rowList.add(new RowInfo(rowChildCount-1, parentWidth-(curLeft-childWidth-VIEW_MARGIN), rowMaxHeight));  //保存每一行的子控件数量，与父控件的空隙，最高的子控件高度
						curLeft = VIEW_MARGIN+childWidth+VIEW_MARGIN;	
						rowChildCount =1;
						totalHeight += rowMaxHeight+VIEW_MARGIN;
						rowMaxHeight = childHeight;
					}
				}else{
					rowMaxHeight = Math.max(rowMaxHeight, childHeight);  //这个取值要保证在判断宽度的后面，防止把下一行的第一个的高度取到
				}
			}
			if(rowChildCount>0){
				rowList.add(new RowInfo(rowChildCount,parentWidth-curLeft, rowMaxHeight));   //此处减去二次View_Margin，不知道原理。需要再研究
				totalHeight +=rowMaxHeight+VIEW_MARGIN;
			}
			 setMeasuredDimension(resolveSize(parentWidth, widthMeasureSpec),resolveSize(totalHeight, heightMeasureSpec));	
//			 setMeasuredDimension(sizeWidth,resolveSize(totalHeight, heightMeasureSpec));			

			Log.e("autobreak", "changed---------> measure");
		}else{
			Log.e("autobreak", "no changed ,no measure");
			 setMeasuredDimension(resolveSize(parentWidth, widthMeasureSpec),resolveSize(getRowTotalHeight(), heightMeasureSpec));	//此处要再设置一下高度。
//			 setMeasuredDimension(sizeWidth,resolveSize(getRowTotalHeight(), heightMeasureSpec));	//此处要再设置一下高度。

		}
	}
	//==================================================================================
	private int getRowTotalHeight(){
		int ret = VIEW_MARGIN;
		for(RowInfo row:rowList){
			ret += row.maxHeight+VIEW_MARGIN;
		}
		return ret;
	}
	private int initChildCount = 0;
	private int initParentWidth = 0;
	private ArrayList<RowInfo>  rowList = new ArrayList<TagView.RowInfo>();
	private class RowInfo{
		public short cols;  //本行控件数量
		public short space; //本行空白宽度值，
		public short maxHeight; //本行控件的最大高度值
		private RowInfo(int cols, int space, int maxHeight) {
			super();
			this.cols = (short) cols;
			this.space = (short) space;
			this.maxHeight = (short) maxHeight;
		}
		
	}
	 
	 public TagViewStyle getTagViewStyle(){
		 return mTagViewStyle;
	 }
	//==================================================================================
	public TagView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public TagView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTagViewStyle=new TagViewStyle();
	}
	public TagView(Context context) {
		super(context);
	}
	
	@Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
	
	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		// TODO Auto-generated method stub
//		canvas.drawText(((TextView)child).getText().toString(), child.getWidth(), child.getHeight(), (Paint) getParent());
		return super.drawChild(canvas, child, drawingTime);
	}
	
//	/*
//	 * 定义子布局的点击事件，同时通过getTag，setTag传回点击的文字
//	 */
	public void setItemClick(OnClickListener listener){
		
		for(int i=0;i<getChildCount();i++){
	     	View child = getChildAt(i);  //得到子控件
	     	child.setTag(((TextView)child).getText().toString());
	     	child.setOnClickListener(listener);
	     	child.setEnabled(true);

		}

	}
	
	/*
	 * 设置tag的值
	 */
	public void setTagsData(List<TagEntity> list, OnTagClickListener listener){
		tagList=list;
		mOnTagClickListener=listener;
		for(TagEntity item:tagList){
			TextView textView =new TextView(getContext());
//			cParams = (MarginLayoutParams) textView.getLayoutParams();
			textView.setText(item.getTxt());
			
//			params.gravity=Gravity.CENTER;
//			params.addRule(Gravity.CENTER_HORIZONTAL);
//			textView.setLayoutParams(params);

//			textView.setBackgroundResource(mTagViewStyle.getTagBg()[0]);
			if(item.getbGId()==0){
				item.setbGId(mTagViewStyle.getTxtBG());
			}
			textView.setBackgroundResource(item.getbGId());
			item.setSelected(textView.isSelected());
			

			if(mTagViewStyle.getTextApperance()==0){
				mTagViewStyle.setTextApperance(R.style.TagDefApperance);
			}
			textView.setTextAppearance(getContext(), mTagViewStyle.getTextApperance());
			textView.setGravity(ViewGroup.MEASURED_SIZE_MASK);
			textView.setTag(item);


			this.addView(textView);
        	
		}
	}

	
	private void singleCilck(){
		for(TagEntity tagEntity:tagList){
			if(tagEntity.getSingleFlag()==1){
				tagEntity.setSelected(true);

			}else {
				tagEntity.setSelected(false);
			}
			
		}
	}
	
	private void refreshData(){
		
	}
	
	private OnClickListener modelidButtonOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(!v.isSelected()){
				v.setSelected(true);
				((TagEntity)v.getTag()).setSelected(true);
//				tagData.setSingleFlag(1);
//				singleCilck((TagEntity)v.getTag());

			}else {
				v.setSelected(false);
				((TagEntity)v.getTag()).setSelected(false);

			}
			
			mOnTagClickListener.setTagClick((TagEntity)v.getTag());
		}
	};
	
}
