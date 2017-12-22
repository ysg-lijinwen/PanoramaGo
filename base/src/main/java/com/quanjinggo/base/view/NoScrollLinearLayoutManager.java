package com.quanjinggo.base.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Description: 禁止RecyclerView 滑动
 * Created by molin on 17/11/30.
 */
public class NoScrollLinearLayoutManager extends LinearLayoutManager {

    public NoScrollLinearLayoutManager(Context context) {
        super(context);
    }

    public NoScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public NoScrollLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

}
