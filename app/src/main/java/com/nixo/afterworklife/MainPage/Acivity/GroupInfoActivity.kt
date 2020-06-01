package com.nixo.afterworklife.MainPage.Acivity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.flyco.tablayout.listener.OnTabSelectListener
import com.gyf.immersionbar.ImmersionBar
import com.jerey.loglib.Klog
import com.nixo.afterworklife.ActivitePage.Activity.ActiviteInfoActivity
import com.nixo.afterworklife.ActivitePage.Fragment.ActiviteFragment
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.GroupPage.GroupActivity.CreateGroupActivity
import com.nixo.afterworklife.GroupPage.GroupActivity.GroupManagerActivity
import com.nixo.afterworklife.GroupPage.GroupActivity.HistoryGroupMessageFragment
import com.nixo.afterworklife.GroupPage.GroupFragment.GroupActiviteFragment
import com.nixo.afterworklife.MainPage.Data.GroupInfo.GroupInfoData
import com.nixo.afterworklife.MainPage.Fragement.FindFragment
import com.nixo.afterworklife.MainPage.Fragement.FlowFragment
import com.nixo.afterworklife.MainPage.Fragement.GroupInfoDyncFragment
import com.nixo.afterworklife.MainPage.Present.GroupInfoPresent
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.ConfirmExitDialog
import com.nixo.afterworklife.Utils.ConfirmTextDialog
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.StringUtils
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_group_info.*
import kotlinx.android.synthetic.main.activity_group_info.stl_major
import kotlinx.android.synthetic.main.activity_group_info.vp_major
import kotlinx.android.synthetic.main.activity_group_manager.*
import kotlinx.android.synthetic.main.fragment_major.*
import kotlinx.android.synthetic.main.include_activity_info.*

class GroupInfoActivity : BaseActivity<GroupInfoPresent>() {


    override fun onLayout(): Int = R.layout.activity_group_info



    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}


    private var titles = arrayOf("综合","精华","活动","热门")
    private var index  = 1
    private var groupId = 0
    private var fragmentLists = ArrayList<Fragment>()

    override fun onResume() {
        super.onResume()
        presenter.getGroupInfo(intent.getIntExtra("name",1))
    }

    override fun onState() {
        groupId = intent.getIntExtra("name",1)
        SharedUtils.putString("info_group_id",groupId.toString())
        fragmentLists.add( GroupInfoDyncFragment())
        fragmentLists.add( GroupInfoDyncFragment())
        fragmentLists.add( GroupActiviteFragment())
        fragmentLists.add( GroupInfoDyncFragment())
        iv_finish.click { finish() }
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_f25531)
            .init()
        AlertUtils.showProgress(false,this)
        if(SharedUtils.getString("token").isEmpty()){
            ll_group_manger.visibility  =  View.GONE
            tv_join.visibility = View.GONE
        }else{
            tv_join.visibility = View.VISIBLE
            ll_group_manger.visibility = View.VISIBLE
        }
//        AlertUtils.showProgress(false,this)

//        initFragmentList()
        stl_major.setViewPager(vp_major,titles,this,fragmentLists)
        vp_major.setCurrentItem(1,true)
//        stl_major.getTitleView(1).textSize = StringUtils.dip2px(this,11.5f).toFloat()
//        if(userId == SharedUtils.getInt("userId") || userId == -233){
//            tv_join.visibility = View.GONE
//            tv_edit.visibility = View.VISIBLE
//        }else{
//            tv_edit.visibility = View.GONE
//            tv_join.visibility = View.VISIBLE
//        }
        ll_group_manger.click {
            var intent = Intent(this, GroupManagerActivity::class.java)
            intent.putExtra("group_id",groupId)
            intent.putExtra("is_join",getIntent())

            startActivity(intent)
        }
//        sv.resetHeight(stl_major,vp_major)
        stl_major.setViewPager(vp_major,titles,this,fragmentLists)
        vp_major.setCurrentItem(1,true)
        stl_major.getTitleView(1).textSize = StringUtils.dip2px(this@GroupInfoActivity,11.5f).toFloat()
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
                    stl_major.getTitleView(position).textSize = StringUtils.dip2px(this@GroupInfoActivity,11.5f).toFloat()
                    stl_major.getTitleView(index).textSize = StringUtils.dip2px(this@GroupInfoActivity,7f).toFloat()
                    index  = position
                }
            }

        })
        stl_major.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                stl_major.getTitleView(position).textSize = StringUtils.dip2px(this@GroupInfoActivity,7f).toFloat()
            }

            override fun onTabReselect(position: Int) {
            }
        })


    }

    fun onSuccess(await: GroupInfoData) {
        AlertUtils.dismissProgress()
        Klog.e("okhttp",await)
        presenter.isAdmin( await.data.id)

        tv_group_name.text = await.data.name
        tv_group_desc.text = await.data.description
        tv_gg.text = await.data.announcement?:"暂无社团通知"
        if(await.data.new_activity != null){
            Glide.with(this).load(await.data.new_activity.logo).into(iv_head)
            tv_title.text = await.data.new_activity.title
            tv_location.text = await.data.new_activity.address
            cv_activite.click {
                if(SharedUtils.getString("token").isEmpty()){
                    Action(LoginActivity::class.java)
                }else{
                    var bundle = Bundle()
                    bundle.putInt("id",await.data.new_activity.id)
                    paramerAction(ActiviteInfoActivity::class.java,bundle)
                }
            }
        }else{
            cv_activite.visibility = View.GONE
        }
        Glide.with(this).load(await.data.avatar).into(iv_group_head)

        AlertUtils.dismissProgress()
    }

    fun onSuccessJoin() {
        ToastUtils.newToastCenter(this,"加入成功")
        tv_join.visibility = View.GONE
        ll_group_manger.visibility = View.VISIBLE

        AlertUtils.dismissProgress()
    }

    fun checkPremisson(isAdministor: String) {
        if(isAdministor == "visitor"){
            tv_join.visibility = View.VISIBLE
            ll_group_manger.visibility = View.GONE
                    tv_join.click {
            if(SharedUtils.getString("token").isEmpty()){
                Action(LoginActivity::class.java)
            }else{
                AlertUtils.showProgress(false,this)
                presenter.onJoinS(intent.getIntExtra("name",-100),"")
            }
//
        }
            ll_group_manger.visibility = View.GONE
        }else{
            tv_join.visibility = View.GONE
            ll_group_manger.visibility = View.VISIBLE
        }
    }


}
