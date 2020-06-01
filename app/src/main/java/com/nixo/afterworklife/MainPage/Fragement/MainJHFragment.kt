package com.nixo.afterworklife.MainPage.Fragement

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.MainPage.Adapter.FlowDynamicsAdapter
import com.nixo.afterworklife.MainPage.Adapter.SimplerAdapter
import com.nixo.afterworklife.MainPage.Present.FindJHPresent
import com.nixo.afterworklife.MainPage.Present.FindPresent
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Welcome.Moudle.DynamicData
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.fragment_find.*

class MainJHFragment : BaseFragment<FindJHPresent>() ,XRecyclerView.LoadingListener,FlowDynamicsAdapter.FlowDynamicsInfs{
    override fun onLoadMore() {
        rv_find.loadMoreComplete()
    }

    override fun onRefresh() {
        presenter.getFindList()
    }

    override fun onClickGood(explore_id: String) {
        presenter.onClickGood(explore_id)
    }

    override fun setLayoutParame(): Int = R.layout.fragment_find

    @SuppressLint("WrongConstant")
    override fun onState() {
        rv_find.setLoadingListener(this)

        AlertUtils.showProgress(false,activity!!)
        presenter.getFindList()
    }



    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}


    @SuppressLint("WrongConstant")
    fun onFindListSuccess(data: MutableList<DynamicData>) {
        if(rv_find != null){
            rv_find.refreshComplete()
            rv_find.layoutManager = LinearLayoutManager(activity).also {
                it.orientation = LinearLayout.VERTICAL
            }
            rv_find.adapter = FlowDynamicsAdapter(activity!!,R.layout.item_find,data).setFlowDynamicsInfs(this)
        }
        AlertUtils.dismissProgress()

    }




}
