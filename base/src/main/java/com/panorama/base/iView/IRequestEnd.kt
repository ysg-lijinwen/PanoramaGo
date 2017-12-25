package com.panorama.base.iView

import android.content.Context
import com.panorama.base.util.UiUtil

/**
 * Created by jokeTng on 2017/11/29.
 */
object IRequestEnd {
    fun handler(context: Context, code: String, message: String) {
        handler(context, null, code, message)
    }

    fun handler(context: Context, irequestView: IRequestView?, code: String, message: String) {
        when (code) {
            "999999" -> {
            }
            else -> UiUtil.showToast(context, message)
        }
        irequestView?.onFinishRequest(true)
    }
}