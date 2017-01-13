/**
 * @CopyRight(c) 2016年12月14日
 */
package com.github.yf_library.guidepage;

import java.util.List;

import android.view.View;
import android.widget.ImageView;

/**
 * GuidePage适配器抽样类
 * @author yihui
 * @version 1.0 2016年12月14日下午2:39:34
 */
public abstract class GuideAdapter {
	
	private GuidePageView mGuidePageView;
	
	public abstract List<View> getIndicator();
	
	public abstract List<View> getDisplayView();
	
	public void setGuidePageView(GuidePageView guidePageView){
		mGuidePageView=guidePageView;
	}
	
//	public abstract void onPageChanged(int position,boolean visitStatus);


}
