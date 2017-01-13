/**
 * @CopyRight(c) 2016年12月27日
 */
package com.github.yf_library.guidepage;

import com.github.yf_library.R;

/**
 * @author yihui
 * @version 1.0 2016年12月27日下午9:35:46
 */
public class GuidePageStyle {
	
	//dot和底部之间的距离，默认为200
	private int dotsHeight=200;
	
	//按钮样式
	private int btnStyle=R.drawable.tag_bg_yfdef01;
	
	//btn和底部之间的距离，默认为200
	private int btnHeight;
	
	//btn的文字
	private String btnStr="马上体验";

	/**
	 * @return the dotsHeight
	 */
	public int getDotsHeight() {
		return dotsHeight;
	}

	/**
	 * @param dotsHeight the dotsHeight to set
	 */
	public void setDotsHeight(int dotsHeight) {
		this.dotsHeight = dotsHeight;
	}

	/**
	 * @return the btnStyle
	 */
	public int getBtnStyle() {
		return btnStyle;
	}

	/**
	 * @param btnStyle the btnStyle to set
	 */
	public void setBtnStyle(int btnStyle) {
		this.btnStyle = btnStyle;
	}

	/**
	 * @return the btnHeight
	 */
	public int getBtnHeight() {
		return btnHeight;
	}

	/**
	 * @param btnHeight the btnHeight to set
	 */
	public void setBtnHeight(int btnHeight) {
		this.btnHeight = btnHeight;
	}

	/**
	 * @return the btnStr
	 */
	public String getBtnStr() {
		return btnStr;
	}

	/**
	 * @param btnStr the btnStr to set
	 */
	public void setBtnStr(String btnStr) {
		this.btnStr = btnStr;
	}
	

}
