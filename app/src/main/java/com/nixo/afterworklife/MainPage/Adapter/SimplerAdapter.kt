package com.nixo.afterworklife.MainPage.Adapter

import android.content.Context
import android.content.Intent
import com.nixo.afterworklife.DynamicsInfo.Activity.DynamicsInfoActivity
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class SimplerAdapter(var context: Context, layout:Int, list:MutableList<String>) : BaseAdapter<String>(context,layout,list){
    override fun initView(p0: BaseHolder, position: Int) {
        p0.itemView.click {
            context.startActivity(Intent(context,
                DynamicsInfoActivity::class.java))
        }
    }


}