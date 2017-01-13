/**
 * @CopyRight(c) 2016年12月29日
 */
package com.github.yf_library.tag;

import android.content.Intent;

/**
 * @author yihui
 * @version 1.0 2016年12月29日下午2:15:29
 */
public class TagEntity {
	
	private String txt;//显示的tag文字
	private int img;//显示的图片
	private String url;//点击的链接
	private boolean isSelected;//是否被选中
	private Intent mIntent;//点击跳转的activity
	private int bGId;//背景id
	private int singleFlag=0;//是否单行
	
	/**
	 * @return the txt
	 */
	public String getTxt() {
		return txt;
	}
	/**
	 * @param txt the txt to set
	 */
	public void setTxt(String txt) {
		this.txt = txt;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}
	/**
	 * @param isSelected the isSelected to set
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	/**
	 * @return the mIntent
	 */
	public Intent getmIntent() {
		return mIntent;
	}
	/**
	 * @param mIntent the mIntent to set
	 */
	public void setmIntent(Intent mIntent) {
		this.mIntent = mIntent;
	}
	/**
	 * @return the bGId
	 */
	public int getbGId() {
		return bGId;
	}
	/**
	 * @param bGId the bGId to set
	 */
	public void setbGId(int bGId) {
		this.bGId = bGId;
	}
	/**
	 * @return the singleFlag
	 */
	public int getSingleFlag() {
		return singleFlag;
	}
	/**
	 * @param singleFlag the singleFlag to set
	 */
	public void setSingleFlag(int singleFlag) {
		this.singleFlag = singleFlag;
	}
	
	
	

}
