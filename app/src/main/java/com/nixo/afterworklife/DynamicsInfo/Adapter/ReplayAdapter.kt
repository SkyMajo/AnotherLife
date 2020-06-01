package com.nixo.afterworklife.DynamicsInfo.Adapter

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.MainPage.Data.CommentPeople
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Utils.TestData
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_replay.view.*
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class ReplayAdapter(var context: Context, layout:Int, list:MutableList<CommentPeople>) : BaseAdapter<CommentPeople>(context,layout,list){
    override fun initView(p0: BaseHolder, position: Int) {
        Log.e("onBaseHolder","走了几次")
        var view = p0.getView<CircleImageView>(R.id.civ_head)
        p0.getView<TextView>(R.id.tv_name).text = mDataList[position].user.username?:""
        Glide.with(context).load(mDataList[position].user.avatar).into(p0.getView<CircleImageView>(R.id.civ_head))
        p0.getView<TextView>(R.id.tv_info).text = mDataList[position].content?:"无"
        p0.getView<TextView>(R.id.id_l).text = "#"+(position+1).toString()
        p0.getView<TextView>(R.id.tv_time).text = mDataList[position].created_at?:"11:53:49"

//        p0.getView<TextView>(R.id.tv_time).text = "11:53:49"

    }


}