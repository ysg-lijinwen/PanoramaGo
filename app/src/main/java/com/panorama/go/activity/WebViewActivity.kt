package com.panorama.go.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView
import com.jointem.plugin.request.util.NetUtil
import com.panorama.base.MvpBaseActivity
import com.panorama.base.iView.IView
import com.panorama.go.R
import com.panorama.go.presenter.WebViewPresenter
import com.panorama.go.util.visible
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.com_link_failed.*

//
class WebViewActivity : MvpBaseActivity<IView, WebViewPresenter>(), IView {
    override fun setPresenter() {
        mPresenter = WebViewPresenter(context)
    }

    override fun setRootView() {
        super.setRootView()
        setContentView(R.layout.activity_webview)
    }

    override fun initData() {
        super.initData()
    }

    override fun initWidget() {
        super.initWidget()
        val setting = wvWeb.settings
        initWebSettings(context, setting)
        WebView.setWebContentsDebuggingEnabled(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        wvWeb.setDownloadListener { url, _, _, _, _ ->
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            this@WebViewActivity.startActivity(intent)
        }

        if (!NetUtil.isNetworkConnected(context)) {
            llComLinkFailed.visible(true)
        } else {
            llComLinkFailed.visible(false)
        }
        /**
         * 设置WebChromeClient和WebViewClient
         */
        wvWeb.loadUrl("")// TODO
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebSettings(context: Context, settings: WebSettings) {
        settings.javaScriptEnabled = true//支持js
        settings.useWideViewPort = true//将图片调整到适合webview的大小
        settings.loadWithOverviewMode = true // 缩放至屏幕的大小
        settings.setSupportZoom(true)//支持缩放，默认为true。是下面那个的前提。
        settings.builtInZoomControls = false//设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.displayZoomControls = false //隐藏原生的缩放控件
        settings.javaScriptCanOpenWindowsAutomatically = true//支持通过JS打开新窗口
        settings.loadsImagesAutomatically = true  //支持自动加载图片
        settings.defaultTextEncodingName = "utf-8"//设置编码格式
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL //支持内容重新布局
        settings.domStorageEnabled = true // 开启 DOM storage API 功能
        settings.databaseEnabled = true   //开启 database storage API 功能
        settings.setAppCacheEnabled(true)//开启 Application Caches 功能
        settings.setAppCacheMaxSize((5 * 1024 * 1024).toLong()) // 5MB
        settings.setGeolocationEnabled(true)//启用地理定位
        //设置定位的数据库路径
        val dir = context.applicationContext.getDir("database", Context.MODE_PRIVATE).path
        settings.setGeolocationDatabasePath(dir)
        if (NetUtil.isNetworkConnected(context)) {
            settings.cacheMode = WebSettings.LOAD_DEFAULT
        } else {
            settings.cacheMode = WebSettings.LOAD_CACHE_ONLY
        }
    }
}
