/**
 * @CopyRight(c) 2016年12月16日
 */
package com.github.yf_library.tab;

/**
 * tabview的样式
 * @author yihui
 * @version 1.0 2016年12月16日下午4:57:59
 */
public class TabStyle {
	
	//暂时定义四种类型的tab
	public enum TabPositionType{
			bottm,top,middle_fixed,middle_movable
	}
	
	
	private TabPositionType mPositionType=TabPositionType.bottm;
	private int mTextColor,mTextColorSelected;
	private int mTextStyle,tabHeight,tabBackGround;


	/**
	 * @return the mPositionType
	 */
	public TabPositionType getmPositionType() {
		return mPositionType;
	}


	/**
	 * @param mPositionType the mPositionType to set
	 */
	public void setmPositionType(TabPositionType mPositionType) {
		this.mPositionType = mPositionType;
	}


	/**
	 * 正常颜色
	 * @return the mTextColor
	 */
	public int getmTextColor() {
		return mTextColor;
	}


	/**
	 * @param mTextColor the mTextColor to set
	 */
	public void setmTextColor(int mTextColor) {
		this.mTextColor = mTextColor;
	}


	/**
	 * 被选中时的颜色
	 * @return the mTextColorSelected
	 */
	public int getmTextColorSelected() {
		return mTextColorSelected;
	}


	/**
	 * @param mTextColorSelected the mTextColorSelected to set
	 */
	public void setmTextColorSelected(int mTextColorSelected) {
		this.mTextColorSelected = mTextColorSelected;
	}


	/**
	 * @return the mTextStyle
	 */
	public int getmTextStyle() {
		return mTextStyle;
	}


	/**
	 * @param mTextStyle the mTextStyle to set
	 */
	public void setmTextStyle(int mTextStyle) {
		this.mTextStyle = mTextStyle;
	}


	/**
	 * @return the tabHeight
	 */
	public int getTabHeight() {
		return tabHeight;
	}


	/**
	 * @param tabHeight the tabHeight to set
	 */
	public void setTabHeight(int tabHeight) {
		this.tabHeight = tabHeight;
	}


	/**
	 * tab的背景颜色
	 * @return the tabBackGround
	 */
	public int getTabBackGround() {
		return tabBackGround;
	}


	/**
	 * @param tabBackGround the tabBackGround to set
	 */
	public void setTabBackGround(int tabBackGround) {
		this.tabBackGround = tabBackGround;
	}
	
	
	
	
	
	
	

}
