/**
 * @CopyRight(c) 2016年12月16日
 */
package com.github.yf_library.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 软键盘工具类,需要事件触发 
 * @author yihui
 * @version 1.0 2016年12月16日下午4:09:34
 */
public class KeyBoardUtils {
	
	 /** 
     * 打开软键盘 
     * 
     * @param editText 
     * @param context 
     */  
    public static void openKeybord(EditText editText, Context context) {  
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);  
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);  
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);  
    }  
  
    /** 
     * 关闭软键盘 
     * @param editText 
     * @param context 
     */  
    public static void closeKeybord(EditText editText, Context context) {  
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);  
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);  
    }  

}
