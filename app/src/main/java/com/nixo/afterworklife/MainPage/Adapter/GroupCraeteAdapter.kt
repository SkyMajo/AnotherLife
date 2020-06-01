package com.nixo.afterworklife.MainPage.Adapter

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.MainPage.Acivity.GroupInfoActivity
import com.nixo.afterworklife.MainPage.Data.Data
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Utils.TestData
import kotlinx.android.synthetic.main.item_my_group.view.*
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class GroupCraeteAdapter(var context: Context, layout:Int, dataList:MutableList<Data>) : BaseAdapter<Data>(context,layout,dataList){

    override fun getItemCount():Int{
        if(mDataList.size<=3){
            return mDataList.size
        }else{
            return 4
        }
    }

    override fun initView(p0: BaseHolder, position: Int) {
        var ivHead = p0.getView<ImageView>(R.id.iv_head)
        var tvTitle = p0.getView<TextView>(R.id.tv_title)
        var tvPersonNum = p0.getView<TextView>(R.id.tv_person_num)
        Glide.with(context).load(mDataList[position].avatar).into(ivHead)
        tvTitle.text = mDataList[position].name
        tvPersonNum.text = "${mDataList[position].members}/${mDataList[position].maxMember}"
        p0.itemView.click {
            var intent = Intent(context,GroupInfoActivity::class.java)
            intent.putExtra("name",mDataList[position].id)
//            var userId = SharedUtils.getString("userId").toInt()
            //-233默认为自己创建的社团，可以编辑
            intent.putExtra("userId",-233)
            context.startActivity(intent)
        }
    }


}