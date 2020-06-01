package com.nixo.afterworklife.Welcome.View.Fragment

import android.content.Context
import android.text.TextUtils
import com.jerey.loglib.Klog
import com.nixo.afterworklife.Common.BaseEmptyFragment
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Commons
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.TimeUtils
import com.nixo.afterworklife.Utils.Wheel.Common
import com.nixo.afterworklife.Utils.Wheel.WheelView
import kotlinx.android.synthetic.main.fragment_splash_old.*
import kotlinx.android.synthetic.main.utils_dialog_one_wheelview.*
import java.time.Year
import java.util.*


class OldSplashFragment : BaseEmptyFragment(){

    override fun setLayoutParame(): Int = R.layout.fragment_splash_old

    private lateinit var infs: oldInterface

    var isBoy = 0

    interface  oldInterface{
        fun onOldFinish(isBoy:Int,old:String)
    }
    //回调给主Activity
    fun addSexInterface(oldInterface: oldInterface): OldSplashFragment {
        infs = oldInterface
        return this
    }

    override fun initView() {
        showWheelDateDialog()
        ll_gril.click {
            isBoy = 0
            iv_gril.setImageResource(R.mipmap.gril_selected)
            iv_boy.setImageResource(R.mipmap.boy_unselect)
        }
        ll_boy.click {
            isBoy = 1
            iv_gril.setImageResource(R.mipmap.gril_unselect)
            iv_boy.setImageResource(R.mipmap.boy_selected)
        }
        ll_next.click {
            var mSelectDate = www1.selectedItem
            var mSelectHour = www2.selectedItem
            var mSelectMin = www3.selectedItem
            mSelectDate = mSelectDate.substring(0, mSelectDate.length - 1)
            mSelectHour = mSelectHour.substring(0, mSelectHour.length - 1)
            mSelectMin = mSelectMin.substring(0, mSelectMin.length - 1)
            val pickUpDate = "$mSelectDate-$mSelectHour-$mSelectMin"
            infs.onOldFinish(isBoy,pickUpDate)
        }

    }




    /**
     * 适用于选择年月日
     *
     * @param mContext
     * @param type     类型
     * @param title    标题
     */
    fun showWheelDateDialog(  ) {
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

        //点击确定
        // {
//            var mSelectDate = www1.getSelectedItem()
//            var mSelectHour = www2.getSelectedItem()
//            var mSelectMin = www3.getSelectedItem()
//            mSelectDate = mSelectDate.substring(0, mSelectDate.length - 1)
//            mSelectHour = mSelectHour.substring(0, mSelectHour.length - 1)
//            mSelectMin = mSelectMin.substring(0, mSelectMin.length - 1)
//            val pickUpDate = mSelectDate + "-" + mSelectHour + "-" + mSelectMin
//
//            if (onSubmitListener != null) {
//                onSubmitListener!!.onSubmit(type, pickUpDate)
//            }
//        }
        //点击取消
//        tv_cancel.setOnClickListener { bottomDialog.dismiss() }

    }
}



