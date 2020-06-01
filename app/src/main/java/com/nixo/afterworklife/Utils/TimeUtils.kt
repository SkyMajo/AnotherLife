package com.nixo.afterworklife.Utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils{

    //需要复写get方法，不然(仅在第一次使用初始化，后面使用都是同一个对象，会导致时间不变)

    //日
    @SuppressLint("SimpleDateFormat")
    var DAY = SimpleDateFormat("dd").format(Date())
    get() = SimpleDateFormat("dd").format(Date())

    //月
    @SuppressLint("SimpleDateFormat")
    var MOUTH = SimpleDateFormat("MM").format(Date())
    get() = SimpleDateFormat("MM").format(Date())

    //年
    @SuppressLint("SimpleDateFormat")
    var YEAR = SimpleDateFormat("yyyy").format(Date())
    get() = SimpleDateFormat("yyyy").format(Date())

    //小时
    @SuppressLint("SimpleDateFormat")
    var HOUR = SimpleDateFormat("HH").format(Date())
    get() = SimpleDateFormat("HH").format(Date())

    //分钟
    @SuppressLint("SimpleDateFormat")
    var MINUTE = SimpleDateFormat("mm").format(Date())
    get() = SimpleDateFormat("mm").format(Date())
    //秒
    @SuppressLint("SimpleDateFormat")
    var SECOND = SimpleDateFormat("mm").format(Date())
    get() = SimpleDateFormat("mm").format(Date())

    // 13:59:12
    @SuppressLint("SimpleDateFormat")
    var STYLE_TIME1 = SimpleDateFormat("HH:mm:ss").format(Date())
    get() =  SimpleDateFormat("HH:mm:ss").format(Date())

    // 13-59-12
    @SuppressLint("SimpleDateFormat")
    var STYLE_TIME2 = SimpleDateFormat("HH-mm-ss").format(Date())
    get() = SimpleDateFormat("HH-mm-ss").format(Date())

    //2019年6月10日
    @SuppressLint("SimpleDateFormat")
    var STYLE_DAYS1 = SimpleDateFormat("yyyy年MM月dd日").format(Date())
    get() = SimpleDateFormat("yyyy年MM月dd日").format(Date())

    //2019-6-10
    @SuppressLint("SimpleDateFormat")
    var STYLE_DAYS2 = SimpleDateFormat("yyyy-MM-dd").format(Date())
    get() = SimpleDateFormat("yyyy-MM-dd").format(Date())

    //2019-6-14 08:42:41
    var  STYLE_DAYS_ALL = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
    get() = SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date())

}