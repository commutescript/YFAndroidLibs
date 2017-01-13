/**
 * @CopyRight(c) 2016年12月16日
 */
package com.github.yf_library.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * 单位转换
 * @author yihui
 * @version 1.0 2016年12月16日下午4:15:27
 */
public class DensityUtils {
	
	public static int sp2px(Context context, int i) {  
        Resources resources = context.getResources();  
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();  
        float fontScale = displayMetrics.scaledDensity;  
        return (int) (i * fontScale + 0.5f);  
    }  
  
  
    //dp转化成px   其他的模仿就行了  因为1dp=1sp的  
    public static int dp2px(Context context, float dpValue) {  
        Resources resources = context.getResources();  
        //用于存储显示的通用信息，如显示大小，分辨率和字体。  
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();  
        //获取转化新系数（这里不太懂 要好好搞一下）  
        float scale = displayMetrics.density;  
        //在160dpi里面  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    public static int px2dp(Context context, float pxValue) {  
        Resources resources = context.getResources();  
        //用于存储显示的通用信息，如显示大小，分辨率和字体。  
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();  
        //获取转化新系数（这里不太懂 要好好搞一下）  
        float scale = displayMetrics.density;  
        //在160dpi里面  
        return (int) (pxValue / scale + 0.5f);  
    }  

}
