package com.nixo.afterworklife.Utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast


/**
 * @Description: 自定义Toast
 */
object ToastUtils {
    /**
     * 短时间显示
     *
     * @param cxt
     * @param content
     * @author lvgg
     * @date 2014-5-16
     */
    fun newToastShort(cxt: Context, content: String) {
        Toast.makeText(cxt, content, Toast.LENGTH_SHORT).show()
    }

    /**
     * @param cxt
     * @param resource 资源
     */
    fun newToastShort(cxt: Context, resource: Int) {
        Toast.makeText(cxt, resource, Toast.LENGTH_SHORT).show()
    }

    /**
     * 位于屏幕顶端
     *
     * @param cxt
     * @param content
     */
    fun newToastTop(cxt: Context, content: String) {
        val toast = Toast.makeText(cxt, content, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 0, 0)
        toast.show()
    }

    /**
     * 位于屏幕顶端
     *
     * @param cxt
     * @param content
     */
    fun newToastTop(cxt: Context, content: Int) {
        val toast = Toast.makeText(cxt, content, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 0, 0)
        toast.show()
    }

    /**
     * 长时间显示
     *
     * @param cxt
     * @param content
     * @author lvgg
     * @date 2014-5-16
     */
    fun newToastLong(cxt: Context, content: String) {
        Toast.makeText(cxt, content, Toast.LENGTH_LONG).show()
    }

    /**
     * 利用string 中的资源文件
     *
     * @param cxt
     * @param resource
     * @author lvgg
     * @date 2014-5-16
     */
    fun newToastLong(cxt: Context, resource: Int) {
        Toast.makeText(cxt, resource, Toast.LENGTH_LONG).show()
    }

    /**
     * 位于屏幕中央
     *
     * @param cxt
     * @param content
     * @author lvgg
     * @date 2014-5-16
     */
    fun newToastCenter(cxt: Context, content: String) {
        val toast = Toast.makeText(cxt, content, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    /**
     * 位于屏幕中央
     *
     * @param cxt
     * @param content
     * @author lvgg
     * @date 2014-5-16
     */
    fun toastCenterLong(cxt: Context, content: String) {
        val toast = Toast.makeText(cxt, content, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun newToastCenter(cxt: Context, content: Int) {
        val toast = Toast.makeText(cxt, content, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}
