package com.nixo.afterworklife.MainPage.Fragement

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.OnTabSelectListener
import com.nixo.afterworklife.ActivitePage.Fragment.ActiviteFragment
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.GroupPage.GroupActivity.CreateGroupActivity
import com.nixo.afterworklife.GroupPage.GroupFragment.GroupActiviteFragment
import com.nixo.afterworklife.GroupPage.GroupFragment.MainActiviteFragment
import com.nixo.afterworklife.MainPage.Acivity.MessageActivity
import com.nixo.afterworklife.MainPage.Adapter.GroupMineJoinAdapter
import com.nixo.afterworklife.MainPage.Adapter.GroupCraeteAdapter
import com.nixo.afterworklife.MainPage.Data.Data
import com.nixo.afterworklife.MainPage.Present.MyGroupPresent
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.StringUtils
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_group_info.*
import kotlinx.android.synthetic.main.fragment_activite.*
import kotlinx.android.synthetic.main.fragment_activite.ll_unlogin
import kotlinx.android.synthetic.main.fragment_major.*
import kotlinx.android.synthetic.main.fragment_my_group.*
import kotlinx.android.synthetic.main.fragment_my_group.ll_no_josin
import kotlinx.android.synthetic.main.fragment_my_group.stl_major
import kotlinx.android.synthetic.main.fragment_my_group.vp_major
import kotlinx.android.synthetic.main.include_unlogin.*

class MyGroupFragment : BaseFragment<MyGroupPresent>() {
    override fun setLayoutParame(): Int  = R.layout.fragment_my_group

    var array = ArrayList<String>().also {
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
    }
    private var titles = arrayOf("动态","活动")
    private var index  = 1
    private var page = 1

    private var fragmentLists = ArrayList<Fragment>().also {
        it.add(MainJHFragment())
        it.add(MainActiviteFragment())
    }

    override fun onState() {
        AlertUtils.showProgress(false,activity!!)
        iv_create.click {
            Action(CreateGroupActivity::class.java)
        }
        presenter.getGroupJoinData()
        if(SharedUtils.getString("token").isNotEmpty()){

            presenter.getIsMsg()
        }
        if(SharedUtils.getString("token").isEmpty()){
            ll_unlogin.visibility = View.VISIBLE
            tv_to_login.click {
                Action(LoginActivity::class.java)
            }
        }else{
            ll_unlogin.visibility = View.GONE
            iv_message.click {
                if (SharedUtils.getString("token").isNotEmpty()) {
                    Action(MessageActivity::class.java)
                }else{
                    Action(LoginActivity::class.java)
                }
            }
            iv_create.click {
                Action(CreateGroupActivity::class.java)
            }
        }
        initSTL()
//        onGetCreateList()

    }

    private fun initSTL() {
        stl_major.setViewPager(vp_major,titles,this,fragmentLists)
        vp_major.setCurrentItem(1,true)
//        stl_major.getTitleView(1).textSize = StringUtils.dip2px(this,11.5f).toFloat()

        stl_major.setViewPager(vp_major,titles,this,fragmentLists)
        vp_major.setCurrentItem(1,true)
        stl_major.getTitleView(page).textSize = StringUtils.dip2px(activity!!,11.5f).toFloat()
        vp_major.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if(index != position){
                    page = position
                    stl_major.getTitleView(position).textSize = StringUtils.dip2px(activity!!,11.5f).toFloat()
                    stl_major.getTitleView(index).textSize = StringUtils.dip2px(activity!!,7f).toFloat()
                    index  = position
                }
            }

        })
        stl_major.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                stl_major.getTitleView(position).textSize = StringUtils.dip2px(activity!!,7f).toFloat()
            }

            override fun onTabReselect(position: Int) {
            }
        })
    }

    private fun onGetCreateList() {
        presenter.onGetMyCreateGroup()

    }

    //加入的社团
    fun onInitJoinList(data: MutableList<Data>) {
        if(tv_join_num != null){
            if (data.size == 0) {
                ll_no_josin.visibility = View.VISIBLE
            }else{
                ll_no_josin.visibility = View.GONE
            }
            tv_join_num.text = data.size.toString()
            srv_group_review.layoutManager = LinearLayoutManager(activity!!).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            srv_group_review.adapter = GroupMineJoinAdapter(activity!!, R.layout.item_group_review_style2,data)
        }
        AlertUtils.dismissProgress()



    }

//    //创建的社团
//    fun onInitCreateList(data: MutableList<Data>){
//        if (data.size == 0) {
//            if(ll_no_content_big != null) ll_no_content_big.visibility = View.VISIBLE
//        }else{
//            if(ll_no_content_big != null) ll_no_content_big.visibility = View.GONE
//            if(tv_see_all != null ){
//                if(data.size>=4){
//                    tv_see_all.visibility = View.VISIBLE
//                }else{
//                    tv_see_all.visibility = View.GONE
//                }
//                xrv_group_manager.layoutManager = LinearLayoutManager(activity!!)
//                xrv_group_manager.adapter = GroupCraeteAdapter(activity!!, R.layout.item_my_group,data)
//            }
//        }
//
//
//    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {

    }

    override fun onDestory() {

    }

    fun onSuccessJoin() {
        ToastUtils.newToastCenter(activity!!,"加入成功")
    }

    fun MsgCall(msg: String) {
        if(msg == "1"){
            v_msg.visibility = View.VISIBLE
        }else{
            v_msg.visibility = View.GONE
        }
    }
}