package com.nixo.afterworklife.DynamicsInfo.Adapter

import android.content.Context
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.MainPage.Data.CommentPeople
import com.nixo.afterworklife.MainPage.Data.LikePeople
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Utils.TestData
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_replay.view.*
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class GoodsAdapter(var context: Context, layout:Int, list:MutableList<LikePeople>) : BaseAdapter<LikePeople>(context,layout,list){
    override fun initView(p0: BaseHolder, position: Int) {
        p0.getView<TextView>(R.id.tv_name).text = mDataList[position].user.username?:""
        Glide.with(context).load(mDataList[position].user.avatar?:"https://a.ppy.sh/1460342?1573724768.jpeg").into(p0.getView(R.id.civ_head))
    }


}