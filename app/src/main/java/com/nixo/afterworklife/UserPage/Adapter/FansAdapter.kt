package com.nixo.afterworklife.UserPage.Adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.nixo.afterworklife.R
import com.nixo.afterworklife.UserPage.Moudle.DataItem
import com.nixo.afterworklife.UserPage.Moudle.FansItem
import com.nixo.afterworklife.UserPage.PublicUserActivity
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_fans.view.*
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class FansAdapter(var context: Context, layout:Int, dataList:MutableList<DataItem>) : BaseAdapter<DataItem>(context,layout,dataList){

    interface FansApi{
        fun onFollow(userid:String)
    }

    var infs : FansApi? = null

    fun setFansInterface(infs : FansApi):FansAdapter{
        this.infs = infs
        return this
    }

    override fun initView(p0: BaseHolder, position: Int) {
        var tvName = p0.getView<TextView>(R.id.tv_name)
        var tvFlowed = p0.getView<TextView>(R.id.tv_flowed)
        var tvUnflow = p0.getView<TextView>(R.id.tv_unflow)
        var civHead = p0.getView<CircleImageView>(R.id.civ_head)
        var ll_jump = p0.getView<LinearLayout>(R.id.ll_jump)
        ll_jump.click {
            var intent = Intent(context,PublicUserActivity::class.java)
            intent.putExtra("userId",mDataList[position].id)
            context.startActivity(intent)
        }
        if(mDataList[position].is_follow){
            tvUnflow.visibility = View.VISIBLE
            tvFlowed.visibility = View.GONE
        }else{
            tvFlowed.visibility = View.VISIBLE
            tvUnflow.visibility = View.GONE
        }
        tvUnflow.click {
            tvFlowed.visibility = View.VISIBLE
            tvUnflow.visibility = View.GONE
            infs!!.onFollow(mDataList[position].fansId.toString())
        }
        tvFlowed.click {
            tvUnflow.visibility = View.VISIBLE
            tvFlowed.visibility = View.GONE
            infs!!.onFollow(mDataList[position].fansId.toString())
        }
        if(mDataList[position]!!.fans!!.size != 0){
            tvName.text = mDataList[position]!!.fans?.get(0)!!.username?:"Nixo"
            Glide.with(context).load(mDataList[position].fans!![0].avatar?:"https://i.loli.net/2019/11/18/cDbRnx3AJGUhTpf.jpg").into(civHead)
        }


    }

}