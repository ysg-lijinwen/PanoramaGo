package com.quanjinggo.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Description:
 * Created by Kevin.Li on 2017/4/8.
 */
public class
BaseFragment extends Fragment {
    protected View rootView;
    protected Context context;
    protected Context activity;
    protected boolean isInitView = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        activity = context;
    }
}
