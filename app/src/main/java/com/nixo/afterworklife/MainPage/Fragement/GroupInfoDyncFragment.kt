package com.nixo.afterworklife.MainPage.Fragement

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.MainPage.Adapter.FlowDynamicsAdapter
import com.nixo.afterworklife.MainPage.Adapter.SimplerAdapter
import com.nixo.afterworklife.MainPage.Present.FindPresent
import com.nixo.afterworklife.MainPage.Present.GroupInfoDyncPresent
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.SendBottomDialog
import com.nixo.afterworklife.Welcome.Moudle.DynamicData
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.fragment_info_find.*

class GroupInfoDyncFragment : BaseFragment<GroupInfoDyncPresent>() ,FlowDynamicsAdapter.FlowDynamicsInfs{


    override fun onClickGood(explore_id: String) {
        presenter.onClickGood(explore_id)
    }

    override fun setLayoutParame(): Int = R.layout.fragment_info_find

    @SuppressLint("WrongConstant")
    override fun onState() {
//        AlertUtils.showProgress(false,activity!!)
        presenter.getFindList(SharedUtils.getString("info_group_id").toInt())
    }



    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}


    @SuppressLint("WrongConstant")
    fun onFindListSuccess(data: MutableList<DynamicData>) {
        if(rv_find != null){
            if(data.size!=0){
                no_dong.visibility = View.GONE
                rv_find.visibility = View.VISIBLE
                rv_find.layoutManager = LinearLayoutManager(activity).also {
                    it.orientation = LinearLayout.VERTICAL
                }
                rv_find.adapter = FlowDynamicsAdapter(activity!!,R.layout.item_find,data).setFlowDynamicsInfs(this)
            }else{
                rv_find.visibility = View.GONE
                no_dong.visibility = View.VISIBLE
                if(SharedUtils.getString("token").isEmpty()){
                    tv_send_dong.click { Action(LoginActivity::class.java) }
                }else {
                    tv_send_dong.click {
                        SendBottomDialog(activity!!).show()
                    }
                }
            }

        }
        AlertUtils.dismissProgress()

    }




}
