package com.quanjinggo.panoramago.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
/**
 * 关于View的工具类
 * @author WangC
 */
public class ViewUtil {
	
	@SuppressWarnings("unchecked")
	public static <T> T findViewById(Activity activity, int viewId) {
		return (T) activity.findViewById(viewId);
	}

	@SuppressWarnings("unchecked")
	public static <T> T findViewById(View viewParent, int viewId) {
		return (T) viewParent.findViewById(viewId);
	}
	
	//隐藏键盘
	public static void  hideKeyBoard(View view){
		InputMethodManager imm=(InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm.isActive()){
		imm.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

}
