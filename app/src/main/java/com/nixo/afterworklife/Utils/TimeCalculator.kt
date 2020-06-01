package com.nixo.afterworklife.Utils

import android.annotation.SuppressLint
import android.content.Context
import com.jerey.loglib.Klog
import java.text.SimpleDateFormat
import java.util.*
import android.widget.Toast
import com.nixo.afterworklife.MainPage.Acivity.MainActivity
import java.text.ParseException






object TimeCalculator {

    @SuppressLint("SimpleDateFormat")
    fun TimeReduceCalculator(starTime:String, endTime:String): Int  {
        var  start = 0

        var  end = 0

        Klog.e("Nixo--TimeReduceCalculator", "开始结束时间$starTime  $endTime");

        try {

            var  df =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

            var start = df.parse(starTime).time

            var end = df.parse(endTime).time

        } catch (e:Exception) {

            // TODO: handle exception

        }

//  CLog.e("开始结束时间1", (end - start) + "");

        var minutes = ((end - start) / (1000 * 60))

        Klog.e("Nixo--TimeReduceCalculator","开始结束时间$minutes")

        return minutes


    }
    fun calendar2string(calendar: Calendar, pattern:String) :String{

        // Calendar calendar = Calendar.getInstance();

        var  df =  SimpleDateFormat(pattern)// 设置你想要的格式

        var dateStr = df.format(calendar.time);

        return dateStr

    }
    fun getTimeString(context: Context,endTime: String, expendTime: String): String {
        //传入字串类型 end:2016/06/28 08:30 expend: 03:25
        val longEnd = getTimeMillis(context,endTime)
        val expendTimes = expendTime.split(":".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()   //截取出小时数和分钟数
        val longExpend =
            java.lang.Long.parseLong(expendTimes[0]) * 60 * 60 * 1000 + java.lang.Long.parseLong(
                expendTimes[1]
            ) * 60 * 1000
        val sdfTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdfTime.format(Date(longEnd - longExpend))
    }

    private fun getTimeMillis(context: Context,strTime: String): Long {
        var returnMillis: Long = 0
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var d: Date? = null
        try {
            d = sdf.parse(strTime)
            returnMillis = d!!.time
        } catch (e: ParseException) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        }

        return returnMillis
    }
    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    private val minute = (60 * 1000).toLong()// 1分钟
    private val hour = 60 * minute// 1小时
    private val day = 24 * hour// 1天
    private val month = 31 * day// 月
    private val year = 12 * month// 年

    fun getTimeFormatText(time: String): String {
        var date = dateToStamp(time)
        val diff = Date().time - date
        var r: Long = 0
        if (diff > year) {
            r = diff / year
            return r.toString() + "年前"
        }
        if (diff > month) {
            r = diff / month
            return r.toString() + "个月前"
        }
        if (diff > day) {
            r = diff / day
            return r.toString() + "天前"
        }
        if (diff > hour) {
            r = diff / hour
            return r.toString() + "个小时前"
        }
        if (diff > minute) {
            r = diff / minute
            return r.toString() + "分钟前"
        }
        return "刚刚"
    }
    /*
     * 将时间转换为时间戳
     */
    @Throws(ParseException::class)
    fun dateToStamp(s: String): Long {
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = simpleDateFormat.parse(s)
        val ts = date.time
        return ts
    }

}