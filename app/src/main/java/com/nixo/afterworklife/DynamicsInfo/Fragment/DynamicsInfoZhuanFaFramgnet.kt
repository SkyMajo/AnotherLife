package com.nixo.afterworklife.DynamicsInfo.Fragment


import android.view.View
import com.nixo.afterworklife.Common.BaseEmptyFragment
import com.nixo.afterworklife.R
import kotlinx.android.synthetic.main.include_no_content.*

class DynamicsInfoZhuanFaFramgnet : BaseEmptyFragment(){

    override fun setLayoutParame(): Int = R.layout.fragment_info_zhuanfa

    override fun initView() {
        ll_no_content.visibility = View.VISIBLE
    }

}