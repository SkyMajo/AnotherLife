package com.nixo.afterworklife.MainPage.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.MainPage.Acivity.GroupInfoActivity
import com.nixo.afterworklife.MainPage.Data.GroupData.LocationDataItem
import com.nixo.afterworklife.MainPage.Data.MyCreateGroupData
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import de.hdodenhof.circleimageview.CircleImageView
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class MyAllGroupAdapter(var context: Context, layout:Int, list:MutableList<com.nixo.afterworklife.MainPage.Data.Data>, var isLocal : Int) : BaseAdapter<com.nixo.afterworklife.MainPage.Data.Data>(context,layout,list) {
    @SuppressLint("SetTextI18n")
    override fun initView(p0: BaseHolder, position: Int) {
        var ivHead = p0.getView<ImageView>(R.id.iv_head)
        var llLocal = p0.getView<LinearLayout>(R.id.ll_local)
        var civFqr = p0.getView<CircleImageView>(R.id.civ_fqr)
        var tvGroupName = p0.getView<TextView>(R.id.tv_group_name)
        var tvPersonNum = p0.getView<TextView>(R.id.tv_person_num)
        var tvGroupInfo = p0.getView<TextView>(R.id.tv_group_info)
        var tvFqrName = p0.getView<TextView>(R.id.tv_fqr_name)
        var tvDesication = p0.getView<TextView>(R.id.tv_desication)
        p0.itemView.click {
            var intent = Intent(context, GroupInfoActivity::class.java)
            intent.putExtra("name",mDataList[position].id)
            intent.putExtra("userId",mDataList[position].id)
            context.startActivity(intent)
        }
        if(isLocal == 0 ){
            llLocal.visibility = View.VISIBLE
            tvDesication.text = mDataList[position].distence.toString()+"KM"
        }else{
            llLocal.visibility = View.GONE
        }
        Glide.with(context).load(mDataList[position].avatar?:"").into(ivHead)
        Glide.with(context).load(mDataList[position].avatar?:"").into(civFqr)
        tvGroupName.text = mDataList[position].name
        tvPersonNum.text ="${mDataList[position].members}"+"/"+"${ mDataList[position].maxMember}"
        tvGroupInfo.text = "${if(mDataList[position].announcement == ""){"暂无内容"}else{mDataList[position].announcement}}"
        tvFqrName.text = mDataList[position].name
    }
}