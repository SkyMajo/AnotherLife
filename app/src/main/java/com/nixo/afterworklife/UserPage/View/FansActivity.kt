package com.nixo.afterworklife.UserPage.View

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyf.immersionbar.ImmersionBar
import com.jerey.loglib.Klog
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.GroupPage.GroupAdapter.GroupUserInfoAdapter
import com.nixo.afterworklife.R
import com.nixo.afterworklife.UserPage.Adapter.FansAdapter
import com.nixo.afterworklife.UserPage.Adapter.IdolAdapter
import com.nixo.afterworklife.UserPage.Moudle.DataItem
import com.nixo.afterworklife.UserPage.Present.FansPresent
import com.nixo.afterworklife.Utils.Ext.click
import kotlinx.android.synthetic.main.activity_dynamics_info.*
import kotlinx.android.synthetic.main.activity_fans.*
import kotlinx.android.synthetic.main.include_titlebar.*

class FansActivity :BaseActivity<FansPresent>(),FansAdapter.FansApi,IdolAdapter.FansApi,GroupUserInfoAdapter.FansApi{
    override fun pm(userid: String) {
        presenter.onFollow(userid)
    }


    override fun onFollow(userid: String) {
        presenter.onFollow(userid)
    }

    override fun onLayout(): Int = R.layout.activity_fans

    override fun onState() {
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()
        var stringExtra = intent.getStringExtra("startType")
        var gid = intent.getIntExtra("groupId",0)
        if(stringExtra == "1"){
            tv_title.text = "我的粉丝"
            presenter.onGetFansData()
        }else if(stringExtra =="group_person") {
            tv_title.text = "社团成员"
            presenter.getGroupUser(gid)
        }else{
            tv_title.text = "我的关注"
            presenter.onIdol()
        }

        ll_title.click { finish() }


    }




    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}
    fun onGetFansSuccess(data: MutableList<DataItem>?) {
        Klog.e("Nixo",data!!.toString())
        xrv_fans.layoutManager = LinearLayoutManager(this)
        xrv_fans.adapter = FansAdapter(this,R.layout.item_fans, data).setFansInterface(this)

    }

    fun onIdolSuccess(data: MutableList<com.nixo.afterworklife.MainPage.Data.DataItem>) {
        xrv_fans.layoutManager = LinearLayoutManager(this)
        xrv_fans.adapter = IdolAdapter(this,R.layout.item_fans,data)!!.setIdolAdaptereeInterface(this)
    }

    fun UserSuccess(data: MutableList<com.nixo.afterworklife.GroupPage.GroupManagerData.GroupUser.DataItem>?) {
        xrv_fans.layoutManager = LinearLayoutManager(this)
        xrv_fans.adapter = GroupUserInfoAdapter(this,R.layout.item_fans,data!!)!!.setIdolAdaptereeInterface(this)
    }


}
