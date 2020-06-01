package com.nixo.afterworklife.MainPage.Adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.MainPage.Acivity.GroupInfoActivity
import com.nixo.afterworklife.MainPage.Data.Data
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Utils.TestData
import de.hdodenhof.circleimageview.CircleImageView
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class GroupMineJoinAdapter(var context: Context, layout:Int, data: MutableList<Data>) : BaseAdapter<Data>(context,layout,data){
    override fun initView(p0: BaseHolder, position: Int) {
        p0.itemView.click {
            var intent = Intent(context, GroupInfoActivity::class.java)
            intent.putExtra("name",mDataList[position].id)
            context.startActivity(intent)
        }

        var civHead = p0.getView<CircleImageView>(R.id.civ_head)
        var ivMsg = p0.getView<ImageView>(R.id.iv_msg)
        var ivTfEnd = p0.getView<ImageView>(R.id.iv_tf_end)
        Glide.with(context).load(mDataList[position].avatar).into(civHead)
        var tvName = p0.getView<TextView>(R.id.tv_name)
        tvName.text = mDataList[position].name
        when (mDataList[position].status) {
            0 -> {
                ivMsg.visibility = View.GONE
                ivTfEnd.visibility = View.VISIBLE
            }
            1 -> {
                ivMsg.visibility = View.VISIBLE
                ivTfEnd.visibility = View.GONE
            }

            else -> {
                ivMsg.visibility = View.GONE
                ivTfEnd.visibility = View.GONE
            }

        }

    }
}