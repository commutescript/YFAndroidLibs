/**
 * @CopyRight(c) 2016年12月25日
 */
package com.github.yf_library.tag;

import com.github.yf_library.R;

/**
 * 自定义tag属性
 * @author yihui
 * @version 1.0 2016年12月25日下午5:25:53
 */
public class TagViewStyle {
	
	/*
	 * 样式
	 */
	public enum TagStyle{
			wrap,match
	}
	
	private TagStyle  mTagStyle=TagStyle.wrap;
	
	
	private enum SelectedType{
		sigle,multi
	}
	
	
	/* 
	 * 字体样式
	 */
	private int textApperance;
	
	
	private int txtBG=R.drawable.tag_seletor_yfdef;

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
	 * @return the mTagStyle
	 */
	public TagStyle getmTagStyle() {
		return mTagStyle;
	}

	/**
	 * @param mTagStyle the mTagStyle to set
	 */
	public void setmTagStyle(TagStyle mTagStyle) {
		this.mTagStyle = mTagStyle;
	}

	/**
	 * @return the txtBG
	 */
	public int getTxtBG() {
		return txtBG;
	}

	/**
	 * @param txtBG the txtBG to set
	 */
	public void setTxtBG(int txtBG) {
		this.txtBG = txtBG;
	}


}
