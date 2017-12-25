package com.panorama.base

import android.os.Bundle
import android.view.View
import butterknife.Unbinder
import com.panorama.base.iView.IView
import com.panorama.base.presenter.BasePresenter

/**
 * Created by jokeTng on 2017/11/28.
 */
abstract class MvpBaseFragment2<V : IView, T : BasePresenter<V>> : BaseFragment2() {

    protected var mPresenter: T? = null  // Presenter对象
    protected lateinit var iView: V
    private val mBind: Unbinder? = null

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iView = this as V
        iView.setPresenter()
        mPresenter!!.attachView(iView)
        mPresenter!!.initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.detachView()
            mPresenter = null
        }
    }
}