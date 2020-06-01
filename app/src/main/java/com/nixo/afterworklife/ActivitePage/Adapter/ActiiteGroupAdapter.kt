package com.nixo.afterworklife.ActivitePage.Adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.ActivitePage.Activity.ActiviteInfoActivity
import com.nixo.afterworklife.ActivitePage.Activity.CreateActiviteActivity
import com.nixo.afterworklife.ActivitePage.DATA.GroupManagerData.DataItem
import com.nixo.afterworklife.MainPage.Acivity.GroupInfoActivity
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Utils.TestData
import de.hdodenhof.circleimageview.CircleImageView
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

class ActiiteGroupAdapter(var context: Context, layout:Int, list:MutableList<DataItem>,var premsioons:String) : BaseAdapter<DataItem>(context,layout,list){


    var infs:onGroupActiviteInfs? = null

    fun setInfs(infs:onGroupActiviteInfs):ActiiteGroupAdapter{
        this.infs = infs
        return this
    }

    interface onGroupActiviteInfs{
        fun onEdit(id:Int)
        fun onDelete(id:Int)
    }

    override fun initView(p0: BaseHolder, position: Int) {
        var civ_Head = p0.getView<ImageView>(R.id.iv_head)
        var tv_Title = p0.getView<TextView>(R.id.tv_title)
        var tv_local = p0.getView<TextView>(R.id.tv_local)
        var tv_edit = p0.getView<TextView>(R.id.tv_edit)
        var tv_delete = p0.getView<TextView>(R.id.tv_delete)
        var tv_over = p0.getView<TextView>(R.id.tv_over)

        tv_Title.text = mDataList[position].title
        tv_local.text = mDataList[position].address
        Glide.with(context).load(mDataList[position].logo).into(civ_Head)
        if(mDataList[position].status == 0){
            tv_over.visibility = View.VISIBLE
        }else{
            tv_over.visibility = View.GONE
            if(premsioons == "holder" || premsioons == "administor"){
                tv_edit.visibility = View.VISIBLE
                tv_edit.text = "编辑"
                tv_edit.click {
                    var intent = Intent(context,CreateActiviteActivity::class.java)
                    intent.putExtra("id",mDataList[position].id)
                    intent.putExtra("startType","edit")
                    context.startActivity(intent)
                }

            }else {
                tv_edit.visibility = View.GONE
//            tv_edit.text = "查看"
//            tv_edit.click {
//                var intent = Intent(context,ActiviteInfoActivity::class.java)
//                intent.putExtra("id",mDataList[position].id)
//                context.startActivity(intent)
//            }
            }
            tv_delete.click {
                var intent = Intent(context,ActiviteInfoActivity::class.java)
                intent.putExtra("id",mDataList[position].id)
                context.startActivity(intent)
            }
        }
    }


}