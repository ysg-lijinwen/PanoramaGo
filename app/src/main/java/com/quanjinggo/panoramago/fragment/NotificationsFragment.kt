package com.quanjinggo.panoramago.fragment

import com.quanjinggo.base.MvpBaseFragment2
import com.quanjinggo.panoramago.R
import com.quanjinggo.panoramago.presenter.HomePresenter
import com.quanjinggo.panoramago.view.IViewHome

/**
 * Description:
 * Created by Kevin.Li on 2017-12-12.
 */
class NotificationsFragment : MvpBaseFragment2<IViewHome, HomePresenter>(), IViewHome {
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