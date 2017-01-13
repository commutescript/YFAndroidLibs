/**
 * @CopyRight(c) 2016年12月16日
 */
package com.github.yf_library.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author yihui
 * @version 1.0 2016年12月16日下午4:12:03
 */
public class ScreenUtils {
	
	//获取屏幕的宽度  
    public static int getScreenWidth(Context context) {  
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);  
        Display defaultDisplay = wm.getDefaultDisplay();  
        int width = defaultDisplay.getWidth();  
        return width;  
    }  
  
    //获取屏幕的高度  
    public static int getScreenHeight(Context context) {  
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);  
        Display defaultDisplay = wm.getDefaultDisplay();  
        int height = defaultDisplay.getHeight();  
        return height;  
    }  
  
    //获取状态栏的高度  
    public static int getStatusHeight(Context context) {  
        int statusHeight = -1;  
        //使用反射，可能会出现类找不到的异常ClassNotFoundException  
        try {  
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");  
            Object object = clazz.newInstance();  
            String status_bar_height = clazz.getField("status_bar_height").get(object).toString();  
            int height = Integer.parseInt(status_bar_height);  
            //转化成px返回  
            statusHeight = context.getResources().getDimensionPixelSize(height);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return statusHeight;  
    }  
  
    //获取当前屏幕截图，包括状态栏  
    public static Bitmap getSnapshot(Activity activity) {  
        Window window = activity.getWindow();  
        View view = window.getDecorView();  
        view.setDrawingCacheEnabled(true);  
        view.buildDrawingCache();  
        Bitmap bitmap = view.getDrawingCache();  
        int screenWidth = getScreenWidth(activity);  
        int screenHeight = getScreenHeight(activity);  
        Bitmap bp;  
        bp = Bitmap.createBitmap(bitmap, 0, 0, screenWidth, screenHeight);  
        view.destroyDrawingCache();  
        return bp;  
    }  

}
