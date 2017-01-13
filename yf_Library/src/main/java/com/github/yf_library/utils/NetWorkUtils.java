/**
 * @CopyRight(c) 2016年12月16日
 */
package com.github.yf_library.utils;

import android.R.string;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author yihui
 * @version 1.0 2016年12月16日下午3:59:11
 */
public class NetWorkUtils {
	
	 /**
     * 检查网络是否连通
     * 
     * @return boolean
     * @since V1.0
     */
    public static boolean isNetworkAvailable(Context context) {
        // 创建并初始化连接对象
        ConnectivityManager connMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 判断初始化是否成功并作出相应处理
        if (connMan != null) {
            // 调用getActiveNetworkInfo方法创建对象,如果不为空则表明网络连通，否则没连通
            NetworkInfo info = connMan.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }
    
    /** 
     * 判断是不是WiFi连接 
     * 
     * @param context 
     * @return 
     */  
    public static boolean isWifi(Context context) {  
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        if (cm != null) {  
            return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;  
        }  
        return false;  
    }  
  
    /** 
     * 打开网络设置界面 
     * 
     * @param activity 
     */  
    public static void openSetting(Activity activity) {  
        Intent intent = new Intent("/");  
        ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");  
        intent.setComponent(cm);  
        intent.setAction("android.intent.action.VIEW");  
        activity.startActivityForResult(intent, 0);  
    }  

}
