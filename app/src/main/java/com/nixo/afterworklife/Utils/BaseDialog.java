package com.nixo.afterworklife.Utils;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.nixo.afterworklife.R;

/**
 * Created by gry on 2018/4/9.
 */
public class BaseDialog extends Dialog {
    protected Context mContext;
    protected boolean mDismissIsCancel = false;
    protected boolean mCannotClose = false;

    public BaseDialog(Context context) {
        super(context, R.style.NewDialog);
        this.mContext = context;


    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;


    }

    void setView(View infornoeView) {
        setContentView(infornoeView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            Window dialogWindow = getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            dialogWindow.setGravity(Gravity.CENTER);

            lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度
            lp.height = WindowManager.LayoutParams.MATCH_PARENT; // 高度

            dialogWindow.setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}




