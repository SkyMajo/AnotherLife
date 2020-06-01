package com.nixo.afterworklife.Welcome.View.Activity

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.MainPage.Acivity.MainActivity
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.View.Fragment.InterestSplashFragment
import com.nixo.afterworklife.Welcome.View.Fragment.OldSplashFragment
import com.nixo.afterworklife.Welcome.Present.SplashPresenter
import com.nixo.afterworklife.Welcome.View.Fragment.SexSplashFragment
import kotlinx.android.synthetic.main.splash_activity.*
import kotlinx.android.synthetic.main.splash_toolbar.*


class SplashActivity() : BaseActivity<SplashPresenter>(),
    InterestSplashFragment.InterestInterface,
    OldSplashFragment.oldInterface, Parcelable {


    var data = ""
    var sex = 0 //0女1男

    override fun onInterest(interests: ArrayList<Int>) {
        //选好兴趣，准备传给后台
        presenter.setInforMationFun(sex,data,interests)
    }

    override fun onOldFinish(isBoy: Int, old: String) {
        //选好年龄
        sex = isBoy
        data = old
        vp_fragment.setCurrentItem(2,true)
    }






    var fragmentList = ArrayList<Fragment>()

    constructor(parcel: Parcel) : this() {

    }


    override fun onLayout(): Int = R.layout.splash_activity

    override fun onState() {
        initClick()
        initFragments()

    }


    private fun initFragments() {
        fragmentList.add(OldSplashFragment().addSexInterface(this))
        fragmentList.add(InterestSplashFragment().addInterestInterface(this))
        vp_fragment.offscreenPageLimit = 3 //规定预先加载页面数量
        vp_fragment.adapter = ViewPagerAdapter(supportFragmentManager)
        vp_fragment.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                if(position == 2){
                    tv_skip.visibility = View.GONE
                }else{
                    tv_skip.visibility = View.VISIBLE
                }
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    private fun initClick() {
        tv_skip.click {
            Action(MainActivity::class.java)
        }
    }

    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private var fm: FragmentManager? = null

        init {
            this.fm = fm
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {



    }

    override fun onDestory() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    fun onSetSuccess(msg: String) {
        ToastUtils.newToastCenter(this,msg)
        Action(MainActivity::class.java)
    }

    companion object CREATOR : Parcelable.Creator<SplashActivity> {
        override fun createFromParcel(parcel: Parcel): SplashActivity {
            return SplashActivity(parcel)
        }

        override fun newArray(size: Int): Array<SplashActivity?> {
            return arrayOfNulls(size)
        }
    }


}