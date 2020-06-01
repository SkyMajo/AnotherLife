package com.nixo.afterworklife.MainPage.Adapter

import android.content.Context
import android.content.Intent
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.DynamicsInfo.Activity.DynamicsInfoActivity
import com.nixo.afterworklife.MainPage.Data.MessageInfo.CommentList.NewData.CommentsItem
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import de.hdodenhof.circleimageview.CircleImageView
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class MessageInfoCommentAdatper(var context: Context, layout:Int, list:MutableList<com.nixo.afterworklife.MainPage.Data.MessageInfo.CommentList.NewData.CommentsItem>) : BaseAdapter<CommentsItem>(context,layout,list) {
    override fun initView(p0: BaseHolder, position: Int) {
        var tv_name = p0.getView<TextView>(R.id.tv_name)
        var civ_head = p0.getView<CircleImageView>(R.id.civ_head)
        var tv_content = p0.getView<TextView>(R.id.tv_content)
        var tv_replay = p0.getView<TextView>(R.id.tv_replay)
        var ll_all = p0.getView<LinearLayout>(R.id.ll_all)
        Glide.with(context).load(mDataList[position].user.avatar).into(civ_head)
        tv_name.text = mDataList[position].user.username
        tv_content.text = mDataList[position].explore.content
        tv_replay.text = mDataList[position].content
        ll_all.click {
            var intent = Intent(context, DynamicsInfoActivity::class.java)
            intent.putExtra("dynamicasId",mDataList[position].explore.id)
            context.startActivity(intent)
        }
    }
}