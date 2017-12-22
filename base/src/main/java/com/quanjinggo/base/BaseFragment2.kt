package com.quanjinggo.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jokeTng on 2017/11/28.
 */
abstract class BaseFragment2 : Fragment() {
    protected lateinit var mContext: Context
    protected lateinit var activity: Context
    protected var isInitView = false
    protected lateinit var rootView: View
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
        activity = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!isInitView) {
            rootView = LayoutInflater.from(mContext).inflate(initRootViewResource(), container, false)
            initData()
            initWidget()
            initListener()
            isInitView = true
        }
        return rootView
    }

    abstract fun initRootViewResource(): Int

    protected open fun initWidget() {}

    protected open fun initData() {}

    protected open fun initListener() {}
}