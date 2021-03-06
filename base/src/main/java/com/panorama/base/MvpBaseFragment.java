package com.panorama.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.panorama.base.iView.IView;
import com.panorama.base.presenter.BasePresenter;

import butterknife.Unbinder;

public class MvpBaseFragment<V extends IView, T extends BasePresenter<V>> extends BaseFragment {
    protected T mPresenter;  // Presenter对象
    protected V iView;
    private Unbinder mBind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iView = (V) this;
        iView.setPresenter();
        mPresenter.attachView(iView);
        mPresenter.initData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
