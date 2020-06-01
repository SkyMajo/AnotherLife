package com.nixo.afterworklife.DynamicsInfo.Adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.DynamicsInfo.Activity.DynamicsInfoActivity
import com.nixo.afterworklife.MainPage.Data.CommentPeople
import com.nixo.afterworklife.MainPage.Data.LikePeople
import com.nixo.afterworklife.MainPage.Data.MessageInfo.DataItem
import com.nixo.afterworklife.MainPage.Data.MessageInfo.LikesItem
import com.nixo.afterworklife.MainPage.Data.shenqing.SQDataItem
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Utils.TestData
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_replay.view.*
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class ShenqingAdapter(var context: Context, layout:Int, list:MutableList<DataItem>) : BaseAdapter<DataItem>(context,layout,list){
    var   applyInf: applyInfs? = null
    fun setInfs(applyInfs: applyInfs):ShenqingAdapter{
        applyInf = applyInfs
        return this
    }
    interface applyInfs{
        fun onApply(string: String)
        fun onDeny(string: String)
    }

    override fun initView(p0: BaseHolder, position: Int) {
        if(mDataList[position].users == null || mDataList[position].users.username.isEmpty()){
            p0.getView<TextView>(R.id.tv_name).text = " "
        }else{
            p0.getView<TextView>(R.id.tv_name).text = mDataList[position].users.username?:""
        }
        p0.getView<TextView>(R.id.tv_group_name).text = mDataList[position].group.name?:""
        Glide.with(context).load(mDataList[position].users.avatar?:"https://a.ppy.sh/1460342?1573724768.jpeg").into(p0.getView(R.id.civ_head))

        p0.getView<TextView>(R.id.tv_reason).text = "申请理由："+mDataList[position].reason?:"无"
        var deny = p0.getView<TextView>(R.id.tv_deny)
        deny.click {
            deny.text = "已拒绝"
            p0.getView<TextView>(R.id.tv_apply).visibility = View.GONE
            applyInf?.onDeny(mDataList[position].id.toString())
        }
        p0.getView<TextView>(R.id.tv_apply).click {
            deny.visibility = View.GONE
            p0.getView<TextView>(R.id.tv_apply).text = "已通过"
            applyInf?.onApply(mDataList[position].id.toString())
        }
        deny.visibility = View.VISIBLE
        var view = p0.getView<CircleImageView>(R.id.civ_head_group)
        Glide.with(context).load(mDataList[position].group.avatar).into(view)

    }


}