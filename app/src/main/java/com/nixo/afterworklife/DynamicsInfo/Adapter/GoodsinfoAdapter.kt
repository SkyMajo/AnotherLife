package com.nixo.afterworklife.DynamicsInfo.Adapter

import android.content.Context
import android.content.Intent
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.DynamicsInfo.Activity.DynamicsInfoActivity
import com.nixo.afterworklife.MainPage.Data.MessageInfo.Goods.LikesItem
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class GoodsinfoAdapter(var context: Context, layout:Int, list:MutableList<com.nixo.afterworklife.MainPage.Data.MessageInfo.Goods.LikesItem>) : BaseAdapter<LikesItem>(context,layout,list){
    override fun initView(p0: BaseHolder, position: Int) {
        p0.getView<TextView>(R.id.tv_name).text = mDataList[position].user.username?:""
        Glide.with(context).load(mDataList[position].user.avatar?:"https://a.ppy.sh/1460342?1573724768.jpeg").into(p0.getView(R.id.civ_head))
        p0.itemView.click {
            var intent = Intent(context, DynamicsInfoActivity::class.java)
            intent.putExtra("dynamicasId",mDataList[position].explore.id)
            intent.putExtra("video","")
            context.startActivity(intent)
        }
    }


}