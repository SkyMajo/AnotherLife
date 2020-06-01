package com.nixo.afterworklife.Search

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.nixo.afterworklife.R
import com.nixo.afterworklife.UserPage.Moudle.DataItem
import com.nixo.afterworklife.UserPage.Moudle.FansItem
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_fans.view.*
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class UserAdapter(var context: Context, layout:Int, dataList:MutableList<com.nixo.afterworklife.Search.SearchData.UserData.DataItem>) : BaseAdapter<com.nixo.afterworklife.Search.SearchData.UserData.DataItem>(context,layout,dataList){

    interface FansApi{
       fun pm(userid:String)
    }

    var infs : FansApi? = null

    fun setIdolAdaptereeInterface(infs : FansApi):UserAdapter{
        this.infs = infs
        return this
    }

    override fun initView(p0: BaseHolder, position: Int) {
        var tvName = p0.getView<TextView>(R.id.tv_name)
        var tvFlowed = p0.getView<TextView>(R.id.tv_flowed)
        var tvUnflow = p0.getView<TextView>(R.id.tv_unflow)
        var civHead = p0.getView<CircleImageView>(R.id.civ_head)
        if(mDataList[position].isFollow){
            tvFlowed.visibility = View.VISIBLE
            tvUnflow.visibility = View.GONE
        }else{
            tvUnflow.visibility = View.VISIBLE
            tvFlowed.visibility = View.GONE
        }
        tvUnflow.click {
            tvFlowed.visibility = View.VISIBLE
            tvUnflow.visibility = View.GONE
            infs!!.pm(mDataList[position].id.toString())
        }
        tvFlowed.click {
            tvUnflow.visibility = View.VISIBLE
            tvFlowed.visibility = View.GONE
            infs!!.pm(mDataList[position].id.toString())
        }

            tvName.text = mDataList[position].username?:"Nixo"
            Glide.with(context).load(mDataList[position].avatar?:"https://i.loli.net/2019/11/18/cDbRnx3AJGUhTpf.jpg").into(civHead)


    }

}