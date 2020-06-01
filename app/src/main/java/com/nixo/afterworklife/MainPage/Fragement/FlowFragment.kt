package com.nixo.afterworklife.MainPage.Fragement

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.MainPage.Adapter.FlowDynamicsAdapter
import com.nixo.afterworklife.MainPage.Adapter.FlowsReviewAdapter
import com.nixo.afterworklife.MainPage.Present.FlowPresent
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Welcome.Moudle.DynamicData
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_activite_manager.*
import kotlinx.android.synthetic.main.fragment_flow.*
import kotlinx.android.synthetic.main.fragment_flow.ll_no_content_big
import kotlinx.android.synthetic.main.include_unlogin.*

class FlowFragment : BaseFragment<FlowPresent>() , XRecyclerView.LoadingListener,FlowDynamicsAdapter.FlowDynamicsInfs{
    override fun onLoadMore() {

    }

    override fun onRefresh() {
//        presenter.onGetFlowDyamics()
    }


    var a = ArrayList<String>(20).apply {
        this.add("")
        this.add("")
        this.add("")
        this.add("")
        this.add("")
        this.add("")
        this.add("")
        this.add("")
        this.add("")
        this.add("")
        this.add("")
        this.add("")
    }


    override fun onState() {
        if(SharedUtils.getString("token").isEmpty()){
            //游客模式
            tv_to_login.click {
                Action(LoginActivity::class.java)
            }
            ll_unlogin.visibility = View.VISIBLE
        }else{
            AlertUtils.showProgress(false,activity!!)
//            rv_flow.setLoadingListener(this)
            ll_unlogin.visibility = View.GONE
            presenter.onGetFlowDyamics()
            presenter.onIdol()
        }



    }

    private fun initFragmentList() {
    }

    override fun setLayoutParame(): Int = R.layout.fragment_flow



    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}

    override fun onClickGood(explore_id: String) {
        presenter.onClickGood(explore_id)
    }

    fun onDynamicsCallBack(data: MutableList<DynamicData>) {
        if(rv_flow != null){
            rv_flow.layoutManager = LinearLayoutManager(activity).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            rv_flow.adapter = FlowDynamicsAdapter(activity!!,R.layout.item_find,data).setFlowDynamicsInfs(this)
        }
        AlertUtils.dismissProgress()
    }

    fun onClickGoodSuccess() {

    }


    fun onIdolSuccess(data: MutableList<com.nixo.afterworklife.MainPage.Data.DataItem>) {
        if(data.size == 0){
            if(ll_no_content_big != null){
                ll_no_content_big.visibility = View.VISIBLE
            }
        }else{
            if(ll_no_content_big != null){
                ll_no_content_big.visibility = View.GONE
            }
            if(rv_flow_review != null){
                rv_flow_review.layoutManager = LinearLayoutManager(activity!!).also {
                    it.orientation = LinearLayoutManager.HORIZONTAL
                }
                rv_flow_review.adapter = FlowsReviewAdapter(activity!!,R.layout.item_flow_review,data)
//                AlertUtils.dismissProgress()
            }
        }


    }

//    override fun onResume() {
//        super.onResume()
//        AlertUtils.showProgress(false,activity!!)
//        presenter.onGetFlowDyamics()
//    }


}