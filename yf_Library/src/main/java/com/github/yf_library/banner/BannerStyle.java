/**
 * @CopyRight(c) 2016年12月15日
 */
package com.github.yf_library.banner;

import com.github.yf_library.R;

/**
 * @author yihui
 * @version 1.0 2016年12月15日上午9:58:32
 */
public class BannerStyle {
	
	//水平
    public enum HorizontalAlignment {
        LEFT, CENTER, RIGHT
    }

    //垂直
    public enum VerticalAlignment {
        TOP, CENTER, BOTTOM
    }

    //指示器的形式
    public enum IndicatorStyle{
        NUM, INDIC, IMG
    }
    
    private int time=2;//默认时间
    private HorizontalAlignment mHorizontalAlignment = HorizontalAlignment.LEFT;//默认靠左
    private VerticalAlignment mVerticalAlignment = VerticalAlignment.BOTTOM;//默认靠底部
    private IndicatorStyle mIndicatorStyle = IndicatorStyle.INDIC;//默认是指示器模式
    private boolean showInfo=false;//默认不显示信息
    
    private int textBg=R.drawable.banner_txt_rec;//默认的数字背景
    private int textApperance;//数字样式
    private int dotsNormal=R.drawable.dot_normal_yfdef;//常规的点
    private int dotsSelected=R.drawable.dot_focused_yfdef;//被选中的点
    private int defImg=R.drawable.icon_banner_yfdef;//默认加载的图片
    
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	/**
	 * @return the mHorizontalAlignment
	 */
	public HorizontalAlignment getmHorizontalAlignment() {
		return mHorizontalAlignment;
	}
	/**
	 * @param mHorizontalAlignment the mHorizontalAlignment to set
	 */
	public void setmHorizontalAlignment(HorizontalAlignment mHorizontalAlignment) {
		this.mHorizontalAlignment = mHorizontalAlignment;
	}
	/**
	 * @return the mVerticalAlignment
	 */
	public VerticalAlignment getmVerticalAlignment() {
		return mVerticalAlignment;
	}
	/**
	 * @param mVerticalAlignment the mVerticalAlignment to set
	 */
	public void setmVerticalAlignment(VerticalAlignment mVerticalAlignment) {
		this.mVerticalAlignment = mVerticalAlignment;
	}
	/**
	 * @return the mIndicatorStyle
	 */
	public IndicatorStyle getmIndicatorStyle() {
		return mIndicatorStyle;
	}
	/**
	 * @param mIndicatorStyle the mIndicatorStyle to set
	 */
	public void setmIndicatorStyle(IndicatorStyle mIndicatorStyle) {
		this.mIndicatorStyle = mIndicatorStyle;
	}
	/**
	 * @return the showInfo
	 */
	public boolean isShowInfo() {
		return showInfo;
	}
	/**
	 * @param showInfo the showInfo to set
	 */
	public void setShowInfo(boolean showInfo) {
		this.showInfo = showInfo;
	}
	/**
	 * @return the textBg
	 */
	public int getTextBg() {
		return textBg;
	}
	/**
	 * @param textBg the textBg to set
	 */
	public void setTextBg(int textBg) {
		this.textBg = textBg;
	}
	/**
	 * @return the textApperance
	 */
	public int getTextApperance() {
		return textApperance;
	}
	/**
	 * @param textApperance the textApperance to set
	 */
	public void setTextApperance(int textApperance) {
		this.textApperance = textApperance;
	}
	/**
	 * @return the dotsNormal
	 */
	public int getDotsNormal() {
		return dotsNormal;
	}
	/**
	 * @param dotsNormal the dotsNormal to set
	 */
	public void setDotsNormal(int dotsNormal) {
		this.dotsNormal = dotsNormal;
	}
	/**
	 * @return the dotsSelected
	 */
	public int getDotsSelected() {
		return dotsSelected;
	}
	/**
	 * @param dotsSelected the dotsSelected to set
	 */
	public void setDotsSelected(int dotsSelected) {
		this.dotsSelected = dotsSelected;
	}
	/**
	 * @return the defImg
	 */
	public int getDefImg() {
		return defImg;
	}
	/**
	 * @param defImg the defImg to set
	 */
	public void setDefImg(int defImg) {
		this.defImg = defImg;
	}
	
	
	
	
	
	

}
