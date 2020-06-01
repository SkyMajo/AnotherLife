package com.nixo.afterworklife.GroupPage.GroupAdapter

import android.content.Context
import android.content.Intent
import com.bumptech.glide.Glide
import com.jerey.loglib.Klog
import com.nixo.afterworklife.R
import com.nixo.afterworklife.UserPage.PublicUserActivity
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_group_user.view.*
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class GroupUserAdapter(var context: Context,layout:Int,data:MutableList<com.nixo.afterworklife.GroupPage.GroupManagerData.GroupUser.DataItem>):BaseAdapter<com.nixo.afterworklife.GroupPage.GroupManagerData.GroupUser.DataItem>(context,layout,data) {
    override fun initView(p0: BaseHolder, position: Int) {
        var view = p0.getView<CircleImageView>(R.id.civ_head)
        Klog.e("Nixo---------",mDataList[position].users.avatar)
        view.click {
            var intent = Intent(context , PublicUserActivity::class.java)
            intent.putExtra("userId",mDataList[position].id)
            context.startActivity(intent)
        }
        if(mDataList[position].users.avatar != "null"){
            Glide.with(context).load(mDataList[position].users.avatar).into(view)
        }else{
            Glide.with(context).load(R.mipmap.plus).into(view)
        }

    }
}
