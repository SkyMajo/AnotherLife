package com.nixo.afterworklife.UserPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.MainPage.Adapter.FlowDynamicsAdapter
import com.nixo.afterworklife.MainPage.Adapter.FlowsReviewAdapter
import com.nixo.afterworklife.R
import com.nixo.afterworklife.UserPage.Moudle.PublicUser.Data
import com.nixo.afterworklife.UserPage.Present.PublicUserPresnet
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Welcome.Moudle.DynamicData
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_public_user.*
import kotlinx.android.synthetic.main.activity_public_user.rv_flow
import kotlinx.android.synthetic.main.fragment_flow.*

class PublicUserActivity : BaseActivity<PublicUserPresnet>() ,FlowDynamicsAdapter.FlowDynamicsInfs{
    override fun onClickGood(explore_id: String) {
        presenter.onClickGood(explore_id)
    }

    override fun onLayout(): Int = R.layout.activity_public_user

    override fun onState() {
        ll_back.click { finish() }
        var intExtra = intent.getIntExtra("userId", 0)
        var startType = intent.getStringExtra("startType")
        if(startType == "me"){
            tv_dymicse.text = "我的动态"
        }else{
            tv_dymicse.text = "他的动态"
        }
        AlertUtils.showProgress(false,this)
        presenter.getUserInfo(intExtra)
        presenter.onGetFlowDyamics(intExtra)
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}
    fun onUserSuccess(data: Data) {
        Glide.with(this).load(data.avatar).into(iv_head)
        tv_name.text = data.username+"       "+if(data.sex == 1){"(男)"}else{"(女)"}
        tv_old.text = "出生于:"+data.birthday
        AlertUtils.dismissProgress()
    }

    fun onDynamicsCallBack(data: MutableList<DynamicData>) {
        if(rv_flow != null){
            rv_flow.layoutManager = LinearLayoutManager(this).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            rv_flow.adapter = FlowDynamicsAdapter(this!!,R.layout.item_find,data).setFlowDynamicsInfs(this)
        }

        AlertUtils.dismissProgress()
    }

}
