/**
 * @CopyRight(c) 2016年12月30日
 */
package com.github.yf_library.search;

import com.github.yf_library.R;

/**
 * 搜索样式
 * @author yihui
 * @version 1.0 2016年12月30日上午9:30:43
 */
public class SearchStyle {
	
	//搜索框layout id样式
	private int historyResId=R.layout.search_history_item_yfdef;
	
	//提示框layout id样式
	private int suggestResId=R.layout.search_history_item_yfdef;
	
	//默认字符
	private String hints="请输入搜索内容";

	/**
	 * @return the historyResId
	 */
	public int getHistoryResId() {
		return historyResId;
	}

	/**
	 * @param historyResId the historyResId to set
	 */
	public void setHistoryResId(int historyResId) {
		this.historyResId = historyResId;
	}

	/**
	 * @return the suggestResId
	 */
	public int getSuggestResId() {
		return suggestResId;
	}

	/**
	 * @param suggestResId the suggestResId to set
	 */
	public void setSuggestResId(int suggestResId) {
		this.suggestResId = suggestResId;
	}

	/**
	 * @return the hints
	 */
	public String getHints() {
		return hints;
	}

	/**
	 * @param hints the hints to set
	 */
	public void setHints(String hints) {
		this.hints = hints;
	}

}
