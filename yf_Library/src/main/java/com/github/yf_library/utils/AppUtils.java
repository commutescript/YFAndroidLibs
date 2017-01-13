/**
 * @CopyRight(c) 2016年12月16日
 */
package com.github.yf_library.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * app工具类 
 * @author yihui
 * @version 1.0 2016年12月16日下午4:10:48
 */
public class AppUtils {
	
	  //获取应用程序名称  
    public static String getAppName(Context context) {  
        try {  
            PackageManager pm = context.getPackageManager();  
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);  
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;  
            //这种方式是可以的  
            //String name = applicationInfo.loadLabel(pm).toString();  
            int labelRes = applicationInfo.labelRes;  
            String name = context.getResources().getString(labelRes);  
            return name;  
        } catch (PackageManager.NameNotFoundException e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
  
    //获取应用程序版本名称  
    public static String getVersionName(Context context) {  
        try {  
            PackageManager packageManager = context.getPackageManager();  
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);  
            String versionName = packageInfo.versionName;  
            return versionName;  
        } catch (PackageManager.NameNotFoundException e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  

}
