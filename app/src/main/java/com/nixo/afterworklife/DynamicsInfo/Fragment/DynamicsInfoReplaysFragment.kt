package com.nixo.afterworklife.DynamicsInfo.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jerey.loglib.Klog
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.DynamicsInfo.Adapter.ReplayAdapter
import com.nixo.afterworklife.DynamicsInfo.Present.DynamicsInfoReplayPresent
import com.nixo.afterworklife.MainPage.Data.CommentPeople
import com.nixo.afterworklife.MainPage.Data.DynamicsInfoData
import com.nixo.afterworklife.R
import kotlinx.android.synthetic.main.fragment_info_replay.*
import kotlinx.android.synthetic.main.include_no_content.*

class DynamicsInfoReplaysFragment : BaseFragment<DynamicsInfoReplayPresent>(){
    override fun onState() {
        presenter.getDynamicsInfo(index)

    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {

    }

    override fun onDestory() {
    }


    fun onRefash(){
        presenter.getDynamicsInfo(index)
    }

    var index = 0
    fun setDyanmicsReplayData(data: Int):Fragment{
        this.index = data
        return this
    }

    override fun onResume() {
        super.onResume()
        presenter.getDynamicsInfo(index)
    }

    fun onReplayDataSuccess(data: MutableList<CommentPeople>){
        Klog.i("Sustain -- dataSize",data.size)
        if(data.size == 0){
            ll_no_content.visibility = View.VISIBLE
        }else {
            ll_no_content.visibility = View.GONE
            rv_replays.layoutManager = LinearLayoutManager(activity!!)
            rv_replays.isNestedScrollingEnabled = false
            rv_replays.setHasFixedSize(true)
//解决数据加载完成后, 没有停留在顶部的问题
//            rv_replays.isFocusable = false
            rv_replays.adapter = ReplayAdapter(activity!!,R.layout.item_replay, data)
        }

    }

    override fun setLayoutParame(): Int = R.layout.fragment_info_replay




}