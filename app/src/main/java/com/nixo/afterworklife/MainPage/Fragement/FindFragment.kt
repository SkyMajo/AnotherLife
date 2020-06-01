package com.nixo.afterworklife.MainPage.Fragement

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jerey.loglib.Klog
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.MainPage.Adapter.FlowDynamicsAdapter
import com.nixo.afterworklife.MainPage.Adapter.SimplerAdapter
import com.nixo.afterworklife.MainPage.Present.FindPresent
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.DynamicData
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_activite_manager.*
import kotlinx.android.synthetic.main.fragment_find.*

class FindFragment : BaseFragment<FindPresent>() ,XRecyclerView.LoadingListener,FlowDynamicsAdapter.FlowDynamicsInfs{
    override fun onLoadMore() {
        page++
        presenter.getFindList(page)
        isRefresh = false
        rv_find.loadMoreComplete()
    }

    override fun onRefresh() {
        rv_find.refreshComplete()
        page = 1
        arrayList = ArrayList<DynamicData>()
        isRefresh = true
        presenter.getFindList(page)
    }

    override fun onClickGood(explore_id: String) {
        presenter.onClickGood(explore_id)
    }
    var isRefresh = true
    private var arrayList = ArrayList<DynamicData>()
    private var page = 1
    override fun setLayoutParame(): Int = R.layout.fragment_find
    private var adapter:FlowDynamicsAdapter? = null




    @SuppressLint("WrongConstant")
    override fun onState() {
        rv_find.setLoadingListener(this)
        Klog.e("TestLife","onState")
//        rv_find.defaultFootView
        rv_find.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin)
//        xrv_group_activite.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin)
        rv_find.layoutManager = LinearLayoutManager(activity).also {
            it.orientation = LinearLayout.VERTICAL
        }
        adapter = FlowDynamicsAdapter(activity!!,R.layout.item_find,arrayList).setFlowDynamicsInfs(this)
        rv_find.adapter = adapter
//        AlertUtils.showProgress(false,activity!!)
        if (isRefresh) {
            presenter.getFindList(1)
        }

    }


    override fun onResume() {
        super.onResume()
        Klog.e("TestLife","onResume")

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Klog.e("TestLife","onHiddenChanged")
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}


    @SuppressLint("WrongConstant")
    fun onFindListSuccess(data: MutableList<DynamicData>) {
        SharedUtils.putInt("page",page)
        if(rv_find != null){
            if(data.size == 0){
//                ToastUtils.newToastCenter(activity!!,"已经到底了")
            }else{
                adapter!!.addDataList(data,isRefresh)
            }
        }
        AlertUtils.dismissProgress()

    }




}
