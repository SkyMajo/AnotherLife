package com.nixo.afterworklife.MainPage.Adapter

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.MainPage.Acivity.GroupInfoActivity
import com.nixo.afterworklife.MainPage.Data.Data
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Utils.TestData
import com.nixo.afterworklife.Utils.View.RaduisImageView
import de.hdodenhof.circleimageview.CircleImageView
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class GroupJoinReviewAdapter(var context: Context, layout:Int, data: MutableList<Data>) : BaseAdapter<Data>(context,layout,data){
    override fun initView(p0: BaseHolder, position: Int) {


        p0.itemView.click {
            var intent = Intent(context, GroupInfoActivity::class.java)
            intent.putExtra("name",mDataList[position].id)
//            intent.putExtra("userId",mDataList[position])
            context.startActivity(intent)
        }

//        var isIng = p0.getView<TextView>(R.id.is_ing)
        var tvName = p0.getView<TextView>(R.id.tv_name)
        var tvNum = p0.getView<TextView>(R.id.tv_num)
        var rivBg = p0.getView<RaduisImageView>(R.id.riv_head)
        Glide.with(context).load(mDataList[position].avatar).into(rivBg)
        tvName.text = mDataList[position].name
        tvNum.text = "${mDataList[position].members}/${mDataList[position].maxMember}"
//        isIng.text = mDataList[position].logo
//        when (mDataList[position].status) {
//            0 -> {
//                isIng.visibility = View.GONE
//            }
//            else -> {
//                isIng.visibility = View.VISIBLE
//                isIng.text = mDataList[position].logo
//            }
//        }
    }


}