package com.nixo.afterworklife.Utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.Wheel.Common
import com.nixo.afterworklife.Utils.Wheel.WheelView

class ConfirmOldDialog : BaseDialog{

    constructor(context: Context) : super(context) {
        init(context)
    }

    private var confirmOnClickListener: ConfirmOldOnClickListener? = null
    fun setConfirmOnClickListener(confirmOnClickListener: ConfirmOldOnClickListener) :ConfirmOldDialog{
        this.confirmOnClickListener = confirmOnClickListener
        return this
    }

    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_sex_confirm, null)
        setContentView(view)

        var www1 = view!!.findViewById<WheelView>(R.id.www1)
        var www2 = view!!.findViewById<WheelView>(R.id.www2)
        var www3 = view!!.findViewById<WheelView>(R.id.www3)
        var ll_next = view!!.findViewById<LinearLayout>(R.id.ll_enter)
        var years = TimeUtils.YEAR
        var months = TimeUtils.MOUTH
        var days = TimeUtils.DAY
        //年滚轮 www1
        //月滚轮 www2
        //日滚轮 www3
        var yearsList = Common.yearNearbyList(years.toInt(),99)
        var monthIndex = Integer.parseInt(months) - 1
        var index = 0
        for (i in 0 until yearsList.size) {
            if (yearsList[i] == years.toString() + "年") {
                index = i
            }
        }
        www1.setItems(yearsList, index)
        www2.setItems(Common.nearByMonthList(), monthIndex)
        www3.setItems(Common.dayList(years, months), Integer.parseInt(days) - 1)
        www1.onItemSelectedListener = WheelView.OnItemSelectedListener { _, _ ->
            var yearItem = www1.selectedItem
            var monthItem = www2.selectedItem
            yearItem = yearItem.substring(0, yearItem.length - 1)
            monthItem = monthItem.substring(0, monthItem.length - 1)
            www2.setItems(Common.monthList(), monthIndex)
            if (monthItem == "02") {
                if (Integer.parseInt(yearItem) % 4 == 0 && Integer.parseInt(yearItem) % 100 != 0 || Integer.parseInt(yearItem) % 400 == 0) {
                    www3.setItems(Common.dayList(yearItem, monthItem), 0)
                }
            }
        }
        www2.onItemSelectedListener = WheelView.OnItemSelectedListener { index, _ ->
            var yearItem = www1.selectedItem
            var monthItem = www2.selectedItem
            yearItem = yearItem.substring(0, yearItem.length - 1)
            monthItem = monthItem.substring(0, monthItem.length - 1)
            monthIndex = index
            if (monthItem == "2") {
                if (Integer.parseInt(yearItem) % 4 == 0 && Integer.parseInt(yearItem) % 100 != 0 || Integer.parseInt(yearItem) % 400 == 0) {
                    www3.setItems(Common.dayList(yearItem, monthItem), 0)
                }
            } else {
                www3.setItems(Common.dayList(yearItem, monthItem), 0)
            }
        }

        ll_next.click {
            var mSelectDate = www1.selectedItem
            var mSelectHour = www2.selectedItem
            var mSelectMin = www3.selectedItem
            mSelectDate = mSelectDate.substring(0, mSelectDate.length - 1)
            mSelectHour = mSelectHour.substring(0, mSelectHour.length - 1)
            mSelectMin = mSelectMin.substring(0, mSelectMin.length - 1)
            val pickUpDate = "$mSelectDate-$mSelectHour-$mSelectMin"
            confirmOnClickListener!!.data(pickUpDate)
            dismiss()
        }

    }


    interface ConfirmOldOnClickListener {
        fun data(data: String)
    }
}