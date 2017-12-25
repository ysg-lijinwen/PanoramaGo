package com.panorama.go.fragment

import com.panorama.base.MvpBaseFragment2
import com.panorama.go.R
import com.panorama.go.bean.ListBean
import com.panorama.go.presenter.HomePresenter
import com.panorama.go.view.IViewHome

/**
 * Description:
 * Created by Kevin.Li on 2017-12-12.
 */
class NotificationsFragment : MvpBaseFragment2<IViewHome, HomePresenter>(), IViewHome {
    override fun fillCarousel(carouselList: MutableList<ListBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fillHomeInfo(homeInfos: MutableList<ListBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter() {
        mPresenter = HomePresenter(mContext)
    }

    override fun initRootViewResource(): Int {
        return R.layout.fragment_notifications
    }

    companion object {
        fun newInstance(): NotificationsFragment {
            val fragment = NotificationsFragment()
            return fragment
        }
    }

    override fun initWidget() {
        super.initWidget()
    }

    override fun initData() {
        super.initData()
    }

    override fun initListener() {
        super.initListener()
    }
}