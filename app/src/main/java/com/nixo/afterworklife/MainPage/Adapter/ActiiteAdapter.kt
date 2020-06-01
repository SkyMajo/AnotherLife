package com.nixo.afterworklife.MainPage.Adapter

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.ActivitePage.DATA.DataItem
import com.nixo.afterworklife.MainPage.Acivity.GroupInfoActivity
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Utils.TestData
import de.hdodenhof.circleimageview.CircleImageView
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class ActiiteAdapter(var context: Context, layout:Int, list:MutableList<DataItem>) : BaseAdapter<DataItem>(context,layout,list){


    var infs:onJoinInfs? = null

    fun setInfs(infs:onJoinInfs):ActiiteAdapter{
        this.infs = infs
        return this
    }

    interface onJoinInfs{
        fun onJoin(id:Int)
    }

    override fun initView(p0: BaseHolder, position: Int) {
        var civ_Head = p0.getView<ImageView>(R.id.iv_head)
        var tv_Title = p0.getView<TextView>(R.id.tv_title)
        var tv_local = p0.getView<TextView>(R.id.tv_local)
        var tv_group = p0.getView<TextView>(R.id.tv_group)
        var iv_join = p0.getView<ImageView>(R.id.iv_join)

        Glide.with(context).load(mDataList[position].logo).into(civ_Head)
        tv_Title.text = mDataList[position].title
        tv_local.text = mDataList[position].address
        tv_group.text = mDataList[position].group.name
        tv_group.click {
            var intent = Intent(context,GroupInfoActivity::class.java)
            intent.putExtra("name",mDataList[position].group.id)
            context.startActivity(intent)
        }
        if(infs != null){
            p0.itemView.click {
                infs!!.onJoin(mDataList[position].id)
            }
        }


    }


}