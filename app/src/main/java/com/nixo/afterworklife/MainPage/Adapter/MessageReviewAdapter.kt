package com.nixo.afterworklife.MainPage.Adapter

import android.content.Context
import android.opengl.Visibility
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.MainPage.Data.Data
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Utils.TestData
import com.nixo.afterworklife.Utils.View.RaduisImageView
import de.hdodenhof.circleimageview.CircleImageView
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class MessageReviewAdapter(var context: Context, layout:Int, data: MutableList<Data>) : BaseAdapter<Data>(context,layout,data){
    override fun initView(p0: BaseHolder, position: Int) {
        var civHead = p0.getView<CircleImageView>(R.id.civ_head)
        var tvTitle = p0.getView<TextView>(R.id.tv_title)
        var tvInfo = p0.getView<TextView>(R.id.tv_info)
        Glide.with(context).load(mDataList[position].avatar).into(civHead)
        tvTitle.text = mDataList[position].name
        tvInfo.text = mDataList[position].description


    }


}