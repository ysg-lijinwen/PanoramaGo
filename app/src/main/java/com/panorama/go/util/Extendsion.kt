package com.panorama.go.util

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.handmark.pulltorefresh.library.PullToRefreshBase
import com.jointem.plugin.request.GetInterfaceConfig
import com.panorama.base.BaseApplication
import com.panorama.base.view.CustomToast
import com.panorama.go.R
import com.yanzhenjie.recyclerview.swipe.refresh.RecyclerView1
import jp.wasabeef.glide.transformations.CropCircleTransformation
import java.net.URLDecoder

/**
 * Description:
 * Created by Kevin.Li on 2017-12-25.
 */
val circleDefaultDrawable: RoundedBitmapDrawable by lazy {
    val defaultSquareTransitImg = R.mipmap.ic_launcher
    val drawable = RoundedBitmapDrawableFactory.create(BaseApplication.getContextFromApplication().resources,
            BitmapFactory.decodeResource(BaseApplication.getContextFromApplication().resources, defaultSquareTransitImg))
    drawable.isCircular = true
    drawable
}

fun Context.showToast(resId: Int) {
    val toast = CustomToast(this, resId)
    toast.show()
}

fun Context.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    val toast = CustomToast(this, message)
    toast.show(duration)
}

@JvmOverloads fun ImageView.loadImage(url: String, defImg: Int = R.mipmap.ic_zsyx_loading) {
    Glide.with(this.context)
            .load(url)
            .placeholder(defImg)
            .error(defImg)
            .centerCrop()
            .crossFade()
            .into(this)
}

fun ImageView.loadCircleImage(url: String, defImg: Int = R.mipmap.ic_launcher, cct: CropCircleTransformation = CropCircleTransformation(context)) {
    var convertUrl = url
    if (!url.startsWith("http")) {
        convertUrl = "${GetInterfaceConfig.getBaseUrl()}$url"
    }
    Glide.with(this.context)
            .load(convertUrl)
            .placeholder(defImg)
            .error(defImg)
            .bitmapTransform(cct)
            .centerCrop()
            .crossFade()
            .into(this)
}

fun TextView.ttext(): String? {
    if (this.text.isNullOrEmpty()) {
        return null
    }
    return this.text.toString()
}

fun String.attachSplit(): List<String> {
    var attach = this
    if (this.startsWith("|")) {
        attach = this.substring(1)
    }
    return attach.split("|").map {
        var str = it
        if (it.contains("/")) {
            str = it.substring(it.lastIndexOf("/"))
        } else {
            str = it
        }
        URLDecoder.decode(str)
    }
}

fun String.split2list(): List<String> {
    var attach = this
    if (this.startsWith("|")) {
        attach = this.substring(1)
    }
    return attach.split("|")
}

fun String.isPicUrl(): Boolean {
    if (this.endsWith(".png")) {
        return true
    }
    if (this.endsWith(".jpg")) {
        return true
    }
    if (this.endsWith(".gif")) {
        return true
    }
    if (this.endsWith(".webp")) {
        return true
    }
    return false

}

fun PullToRefreshBase<RecyclerView1>.finishRequest(isError: Boolean){
    this.onRefreshComplete()
    if(isError){
        curPageNo--
    }
}