package com.panorama.go.view

import com.panorama.base.iView.IView
import com.panorama.go.bean.ListBean

/**
 * Description:主页
 * Created by Kevin.Li on 2017-12-08.
 */
interface IViewHome : IView {
    /**
     * 填充轮播图
     *
     * @param carouselList 轮播图列表
     */
    fun fillCarousel(carouselList: MutableList<ListBean>)

    /**
     * 填充信息列
     */
    fun fillHomeInfo(homeInfo: MutableList<ListBean>)
}