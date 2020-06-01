package com.nixo.afterworklife.Welcome.View.Fragment

import android.annotation.SuppressLint
import android.graphics.Color
import com.nixo.afterworklife.Common.BaseEmptyFragment
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import kotlinx.android.synthetic.main.fragment_splash_sex.*

class SexSplashFragment : BaseEmptyFragment(){

    private lateinit var infs: sexInterface



    override fun setLayoutParame(): Int = R.layout.fragment_splash_sex
    interface sexInterface{
        fun onSelect(sex:Int)
    }

    //回调给主Activity
    fun addSexInterface(sexInterface: sexInterface): SexSplashFragment {
        infs = sexInterface
        return this
    }

    @SuppressLint("ResourceAsColor")
    override fun initView() {
        tv_gril.click {
            //选女
            tv_gril.setBackgroundResource(R.drawable.pinkful_sex_select)
            tv_gril.setTextColor(Color.WHITE)
            tv_boy.setBackgroundResource(R.drawable.blue_sex_select)
            tv_boy.setTextColor(Color.parseColor("#03A9F4"))
            infs.onSelect(0)
        }
        tv_boy.click {
            //选男
            tv_boy.setBackgroundResource(R.drawable.blueful_sex_select)
            tv_boy.setTextColor(Color.WHITE)
            tv_gril.setBackgroundResource(R.drawable.pink_sex_select)
            tv_gril.setTextColor(Color.parseColor("#F567B0"))
            infs.onSelect(1)
        }
    }



}

