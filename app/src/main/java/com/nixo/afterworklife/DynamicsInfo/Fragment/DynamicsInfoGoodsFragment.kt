package com.nixo.afterworklife.DynamicsInfo.Fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nixo.afterworklife.Common.BaseEmptyFragment
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.DynamicsInfo.Adapter.GoodsAdapter
import com.nixo.afterworklife.DynamicsInfo.Present.DynamicsInfoGoodPresent
import com.nixo.afterworklife.MainPage.Data.CommentPeople
import com.nixo.afterworklife.MainPage.Data.LikePeople
import com.nixo.afterworklife.R
import kotlinx.android.synthetic.main.fragment_info_good.*
import kotlinx.android.synthetic.main.include_no_content.*

class DynamicsInfoGoodsFragment : BaseFragment<DynamicsInfoGoodPresent>(){
    override fun onState() {}

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}

    override fun setLayoutParame(): Int = R.layout.fragment_info_good

    private var index = 5

    fun setDyanmicsReplayData(dynamicasId: Int) {
        index = dynamicasId
        presenter.getDynamicsInfo(dynamicasId)

    }
    fun onRefash(){
        presenter.getDynamicsInfo(index)
    }

    fun onReplayDataSuccess(goodsPeople: MutableList<LikePeople>) {
        if(goodsPeople.size == 0){
            ll_no_content.visibility = View.VISIBLE
        }else{
            xrv_goods.layoutManager = LinearLayoutManager(activity!!)
            xrv_goods.isNestedScrollingEnabled = false
            xrv_goods.setHasFixedSize(true)
//解决数据加载完成后, 没有停留在顶部的问题
            xrv_goods.isFocusable = false
            xrv_goods.adapter = GoodsAdapter(activity!!,R.layout.item_goods,goodsPeople)
            ll_no_content.visibility = View.GONE
        }
    }

}