package com.nixo.afterworklife.MainPage.Adapter

import android.content.Context
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Utils.TestData
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class ActiviteReviewAdapter(context: Context, layout:Int, list:MutableList<String>) : BaseAdapter<String>(context,layout,list){

    override fun getItemCount(): Int {
        return 4
    }

    override fun initView(p0: BaseHolder, position: Int) {

    }


}