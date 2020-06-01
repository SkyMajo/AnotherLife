package com.nixo.afterworklife.Utils;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;

public class BottomDialog extends Dialog {
    public BottomDialog(Context context) {
        super(context);
        this.init();
    }

    public BottomDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.init();
    }

    protected BottomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.init();
    }

    private void init() {
        Window dialogWindow = this.getWindow();
//        dialogWindow.setGravity(80);
        dialogWindow.setGravity(Gravity.BOTTOM);
        LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        lp.width = LayoutParams.MATCH_PARENT;
//        lp.height ;
//        lp.y = 0;
        dialogWindow.setAttributes(lp);
    }

    public void show() {
        super.show();
    }
}
