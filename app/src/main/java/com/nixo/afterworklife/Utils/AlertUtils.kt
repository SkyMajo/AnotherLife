package com.yqjr.superviseapp.utils.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import com.nixo.afterworklife.R



/**
 *
 * @description 自定义公共弹窗
 */
@SuppressLint("StaticFieldLeak")
object AlertUtils {
    private var pg: Dialog? = null
    private var conext: Context? = null
    private var gifDrawable: pl.droidsonroids.gif.GifDrawable? = null

    /*
	 * 显示进度条
	 */
    fun showProgress(isCancel: Boolean, cxt: Context) {
        if (pg == null || conext != null && conext!!.hashCode() != cxt.hashCode()) {
            conext = cxt
            gifDrawable = pl.droidsonroids.gif.GifDrawable(conext!!.resources, R.mipmap.loading)
            pg = Dialog(cxt, R.style.myDialog)
            var view = View.inflate(conext, R.layout.dialog_loading, null)
            pg!!.setContentView(view)
            pg!!.setCanceledOnTouchOutside(false)// 按返回键不能退出
            pg!!.setOnDismissListener {
                gifDrawable!!.stop()
                dismissProgress()
            }
        }
        if (null != pg && !pg!!.isShowing) {
            pg!!.setCancelable(isCancel)
            gifDrawable!!.start()
            pg!!.show()
        }
    }

    /*
	 * 进度条消失
	 */
    fun dismissProgress() {
        if (pg != null)
            if (pg!!.isShowing)
                pg!!.dismiss()
    }
    fun onDestroy(){
        if (gifDrawable!= null){

            gifDrawable!!.recycle()
        }
    }
}
