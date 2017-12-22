package com.quanjinggo.panoramago.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

/**
 * new DTDialog(context).setView(R.id.test).show(); </p>
 * 仅支持在FragmentActivity的环境内 {@link #show()}
 *
 * @author WangC
 */
public class DTDialog implements DialogInterface, DTDialogFragment.DialogEvent {

    private static final String TAG = DTDialog.class.getSimpleName();

    private Context mContext;

    private DTDialogFragment mDialogFragment;

    private OnDismissListener mOnDismissListener;

    private OnShowListener mOnShowListener;

    private OnCancelListener mOnCancelListener;

    public DTDialog(Context context) {
        this(context, 0);
    }

    public DTDialog(Context context, @StyleRes int theme) {
        this.mContext = context;
        mDialogFragment = DTDialogFragment.newInstance(theme);
        mDialogFragment.setDialogEvent(this);
    }

    public Context getContext() {
        return mContext;
    }

    public DTDialog setView(View view) {
        if (mDialogFragment != null) {
            mDialogFragment.setView(view);
        }
        return this;
    }

    public DTDialog setView(int layoutRes) {
        if (mDialogFragment != null) {
            mDialogFragment.setView(View.inflate(mContext, layoutRes, null));
        }
        return this;
    }

    public DTDialog setCancelable(boolean cancelable) {
        if (mDialogFragment != null) {
            mDialogFragment.setCancelable(cancelable);
        }
        return this;
    }

    public void setOnDismissListener(OnDismissListener listener) {
        this.mOnDismissListener = listener;
    }

    public void setOnShowListener(OnShowListener listener) {
        this.mOnShowListener = listener;
    }

    public void setOnCancelListener(OnCancelListener listener) {
        this.mOnCancelListener = listener;
    }

    public DTDialog setGravity(int gravity) {
        if (mDialogFragment != null) {
            mDialogFragment.setGravity(gravity);
        }
        return this;
    }

    public DTDialog setWidthPercentage(float widthPercentage) {
        if (mDialogFragment != null) {
            mDialogFragment.setWidthPercentage(widthPercentage);
        }
        return this;
    }

    public DTDialog setHeightPercentage(float heightPercentage) {
        if (mDialogFragment != null) {
            mDialogFragment.setHeightPercentage(heightPercentage);
        }
        return this;
    }

    public void show() {
        if (!(mContext instanceof FragmentActivity) || mDialogFragment == null) {
            return;
        }
        FragmentActivity activity = (FragmentActivity) mContext;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        // 指定一个系统转场动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.add(mDialogFragment, "dialog");
        ft.commitAllowingStateLoss();
    }

    @SuppressWarnings("unchecked")
    public <T> T findViewById(int viewId) {
        if (mDialogFragment != null) {
            return mDialogFragment.findViewById(viewId);
        }
        return null;
    }

    public boolean isShowing() {
        return mDialogFragment.isVisible();
    }

    @Override
    public void dismiss() {
        Log.e(TAG, "dismiss() ");
        mDialogFragment.dismissAllowingStateLoss();
    }

    @Override
    public void cancel() {
        Log.e(TAG, "cancel() ");
        mDialogFragment.dismissAllowingStateLoss();
    }

    public int getScreenWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    public int getScreenHeight() {
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onCancel() {
        Log.e(TAG, "onCancel() ");
        if (mOnCancelListener != null) {
            mOnCancelListener.onCancel(this);
        }
    }

    @Override
    public void onDismiss() {
        Log.e(TAG, "onDismiss() ");
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(this);
        }
    }
}
