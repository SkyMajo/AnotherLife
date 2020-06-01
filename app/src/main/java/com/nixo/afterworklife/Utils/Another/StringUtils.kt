package com.nixo.osubox.Utils.Another

import android.content.Context

object StringUtils {

    /**
     * 将dp转换成px
     * @param context
     * @param dpValue
     * @return
     */
    fun dip2px(context: Context, dpValue: Float): Int = (dpValue * context.resources.displayMetrics.density + 0.5f).toInt()

    /**
     * 将像素转换成dp
     * @param context
     * @param pxValue
     * @return
     */
    fun px2dip(context: Context, pxValue: Float): Int = (pxValue / context.resources.displayMetrics.density + 0.5f).toInt()

}
