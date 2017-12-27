package com.panorama.go.view

import com.panorama.base.iView.IView

/**
 * Description:
 * Created by Kevin.Li on 2017-12-26.
 */
interface IViewSearch : IView {
    /**
     * 展示历史记录
     */
    fun fillHistory(list: MutableList<String>)

    /**
     * 展示热门搜索
     */
    fun fillHotKey(list: MutableList<String>)
}