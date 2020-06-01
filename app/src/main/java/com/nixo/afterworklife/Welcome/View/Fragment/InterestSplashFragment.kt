package com.nixo.afterworklife.Welcome.View.Fragment

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Welcome.Adapter.LinkLabelAdapter
import com.nixo.afterworklife.Welcome.Moudle.DataItem
import com.nixo.afterworklife.Welcome.Moudle.UserDataTagItem
import com.nixo.afterworklife.Welcome.Present.InterestSplashPresent
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.fragment_splash_interest.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class InterestSplashFragment : BaseFragment<InterestSplashPresent>() {


    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}

    override fun setLayoutParame(): Int = R.layout.fragment_splash_interest


    var linkLabelAdapter : LinkLabelAdapter? = null

    private fun initViews() {

    }

    private lateinit var infs: InterestInterface
    interface  InterestInterface{
        fun onInterest(interests : ArrayList<Int>)
    }


    override fun onState() {
        initViews()
        AlertUtils.showProgress(true ,activity!!)
        presenter.onGetInterestTag()
    }

    //回调给主Activity
    fun addInterestInterface(oldInterface: InterestInterface): InterestSplashFragment {
        infs = oldInterface
        return this
    }
    infix fun onDataSuccess(datas:MutableList<DataItem>){
        GlobalScope.launch (Dispatchers.Main){
            AlertUtils.dismissProgress()
            val mLayoutManager = GridLayoutManager(activity!!, 4)
            mLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val type = recyclerview.adapter!!.getItemViewType(position)//获得返回值
                    return if (type == 99) {
                        mLayoutManager.spanCount
                    } else {
                        1
                    }
                }
            }
            recyclerview.layoutManager = mLayoutManager
            linkLabelAdapter = LinkLabelAdapter(activity!!, datas)
            recyclerview.adapter = linkLabelAdapter
        }
        btn_enter.click {
//            ToastUtils.newToastCenter(activity!!,"${linkLabelAdapter!!.data}")
//            Klog.e("Show Data","${linkLabelAdapter!!.data}")
            infs.onInterest(linkLabelAdapter!!.data)
        }
    }






}