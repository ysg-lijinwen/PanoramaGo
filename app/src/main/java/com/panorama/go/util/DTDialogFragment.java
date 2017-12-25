package com.panorama.go.util;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.panorama.go.R;

/**
 * Author：five on 2016/11/30 14:10
 * <p>
 * Email：dww@detu.com
 * <p>
 * I just wish someday and somehow,We can be back together,
 * Together we'll stay,Always and forever.
 * <p>
 * Description:
 */

public class DTDialogFragment  extends DialogFragment {

    private DisplayMetrics mDisplayMetrics;

    static int style;
    private View mView;

    private float mWidthPercentage; // 宽度所占屏幕的百分比

    private float mHeightPercentage; //高度占比

    private int mGravity = Gravity.CENTER;

    private  DialogEvent dialogEvent;

    public static final DTDialogFragment newInstance(@StyleRes int styleId) {
        DTDialogFragment dialogFragment = new DTDialogFragment();
        style = styleId;
        return dialogFragment;
    }

    public  void setDialogEvent(DialogEvent dialogEvent){
        this.dialogEvent=dialogEvent;
    }

    public void setGravity(int gravity) {
        this.mGravity = gravity;
    }

    public void setView(View view) {
        this.mView = view;
    }

    public void setWidthPercentage(float widthPercentage) {
        this.mWidthPercentage = widthPercentage;
    }

    public void setHeightPercentage(float heightPercentage) {
        this.mHeightPercentage = heightPercentage;
    }

    @SuppressWarnings("unchecked")
    public <T> T findViewById(int viewId) {
        if (mView == null) {
            return null;
        }
        return (T) mView.findViewById(viewId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (style != 0) {
            setStyle(STYLE_NO_FRAME, style);
        } else {
            setStyle(STYLE_NO_FRAME, R.style.dtdialog);
        }
        mDisplayMetrics = getActivity().getResources().getDisplayMetrics();
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        Dialog dialog = getDialog();
        if(mView==null||dialog==null){
            super.onActivityCreated(arg0);
            return;
        }
        dialog.setContentView(mView);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = mGravity;
        int width;
        if (DTUtils.isLandscape(getActivity())) {
            width = mWidthPercentage == 0 ? (int) (mDisplayMetrics.heightPixels * 0.7) : (int) (mDisplayMetrics.heightPixels * mWidthPercentage);
        } else {
            width = mWidthPercentage == 0 ? (int) (mDisplayMetrics.widthPixels * 0.7) : (int) (mDisplayMetrics.widthPixels * mWidthPercentage);
        }
        attributes.width = width;
        if (mHeightPercentage == 0) {
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        } else {
            attributes.height = (int) (mDisplayMetrics.heightPixels * mHeightPercentage);
        }

        window.setAttributes(attributes);
        super.onActivityCreated(arg0);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if(dialogEvent!=null){
            dialogEvent.onCancel();
        }

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(dialogEvent!=null){
            dialogEvent.onDismiss();
        }
    }

    public  interface  DialogEvent{
       void  onCancel();
        void onDismiss();
    }

}