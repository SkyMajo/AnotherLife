package com.nixo.afterworklife.ActivitePage.Adapter

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.MainPage.Data.Data
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import de.hdodenhof.circleimageview.CircleImageView
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder
import javax.security.auth.callback.Callback

class GroupChoseAdapter (var context: Context, layout:Int, data: MutableList<Data>,var infs:GroupChoseCallBack) : BaseAdapter<Data>(context,layout,data){

    interface GroupChoseCallBack{
        fun onResult(string: String,id:Int)
    }

    override fun initView(p0: BaseHolder, position: Int) {
        var view = p0.getView<CircleImageView>(R.id.civ_head)
        var tv_name = p0.getView<TextView>(R.id.tv_name)
        var ll_choose = p0.getView<LinearLayout>(R.id.ll_choose)
        Glide.with(context).load(mDataList[position].avatar).into(view)
        tv_name.text = mDataList[position].name
        ll_choose.click {
            infs.onResult(mDataList[position].name,mDataList[position].id)
        }
    }

}