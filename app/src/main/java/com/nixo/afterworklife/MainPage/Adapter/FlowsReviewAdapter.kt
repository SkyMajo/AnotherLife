package com.nixo.afterworklife.MainPage.Adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Welcome.Moudle.DynamicData
import de.hdodenhof.circleimageview.CircleImageView
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class FlowsReviewAdapter (var context: Context, layout:Int, list : MutableList<com.nixo.afterworklife.MainPage.Data.DataItem>) : BaseAdapter<com.nixo.afterworklife.MainPage.Data.DataItem>(context,layout,list){
    override fun initView(p0: BaseHolder, position: Int) {
        var view = p0.getView<CircleImageView>(R.id.civ_head)

        Glide.with(context).load(mDataList[position].users.avatar?:"https://i.loli.net/2019/11/14/kqv2CfEjgZcSzUo.png").into(view)
//        Glide.with(context).load("https://i.loli.net/2019/11/18/cDbRnx3AJGUhTpf.jpg").into(view)
    }

}