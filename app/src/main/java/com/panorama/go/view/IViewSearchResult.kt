package com.panorama.go.view

import com.panorama.base.iView.IView
import com.panorama.go.bean.ListBean

/**
 * Description:搜索结果
 * Created by Kevin.Li on 2017-12-26.
 */
interface IViewSearchResult : IView{
    /**
     * 展示搜索结果
     */
    fun fillSearchResult(list: MutableList<ListBean>)
}