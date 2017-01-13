/**
 * @CopyRight(c) 2016年12月14日
 */
package com.github.yf_library.tab;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * tab抽象类,设置tab和跳转
 * @author yihui
 * @version 1.0 2016年12月14日下午8:58:15
 */
public abstract class TabAdapter {
	
	private TabView mTabView01;
	
	public abstract List<View> setTabIndicator();
	public abstract List<Intent> setActivities();
	
	
	public void  setTabView01(TabView tabView01) {
		mTabView01=tabView01;
	}

}
