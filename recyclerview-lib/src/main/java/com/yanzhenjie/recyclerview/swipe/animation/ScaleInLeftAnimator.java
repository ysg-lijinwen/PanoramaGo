package com.yanzhenjie.recyclerview.swipe.animation;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Interpolator;

/**
 * @author THC
 * @Title: ScaleInLeftAnimator
 * @Package com.jointem.zyb.view.widget.recyclerview.animation
 * @Description:
 * @date 2016/3/26 9:51
 */
public class ScaleInLeftAnimator extends BaseItemAnimator {

    public ScaleInLeftAnimator() {
    }

    public ScaleInLeftAnimator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    @Override
    protected void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {
        ViewCompat.setPivotX(holder.itemView, 0);
    }

    @Override
    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        ViewCompat.animate(holder.itemView)
                .scaleX(0)
                .scaleY(0)
                .setDuration(getRemoveDuration())
                .setInterpolator(mInterpolator)
                .setListener(new DefaultRemoveVpaListener(holder))
                .setStartDelay(getRemoveDelay(holder))
                .start();
    }

    @Override
    protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
        ViewCompat.setPivotX(holder.itemView, 0);
        ViewCompat.setScaleX(holder.itemView, 0);
        ViewCompat.setScaleY(holder.itemView, 0);
    }

    @Override
    protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
        ViewCompat.animate(holder.itemView)
                .scaleX(1)
                .scaleY(1)
                .setDuration(getAddDuration())
                .setInterpolator(mInterpolator)
                .setListener(new DefaultAddVpaListener(holder))
                .setStartDelay(getAddDelay(holder))
                .start();
    }
}
