package com.nixo.afterworklife.ActivitePage.Fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nixo.afterworklife.ActivitePage.Activity.ActiviteInfoActivity
import com.nixo.afterworklife.ActivitePage.DATA.DataItem
import com.nixo.afterworklife.ActivitePage.Presenter.ActivitePresenter
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.MainPage.Acivity.MessageActivity
import com.nixo.afterworklife.MainPage.Adapter.ActiiteAdapter
import com.nixo.afterworklife.MainPage.Adapter.ActiviteReviewAdapter
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Search.SearchActivity
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_activite.*
import kotlinx.android.synthetic.main.fragment_activite.iv_message
import kotlinx.android.synthetic.main.fragment_activite.ll_no_josin
import kotlinx.android.synthetic.main.fragment_activite.v_msg
import kotlinx.android.synthetic.main.fragment_my_group.*

class ActiviteFragment :BaseFragment<ActivitePresenter>(),ActiiteAdapter.onJoinInfs{
    override fun onJoin(id: Int) {
        var bundle = Bundle()
        bundle.putInt("id",id)
        paramerAction(ActiviteInfoActivity::class.java,bundle)
    }


    var array = ArrayList<String>().also {
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
    }

//https://i.loli.net/2019/11/14/kqv2CfEjgZcSzUo.png
    override fun setLayoutParame(): Int  = R.layout.fragment_activite

    override fun onState() {
        if(SharedUtils.getString("token").isEmpty()){
            ll_no_josin.visibility = View.GONE
            ll_unlogin.visibility = View.VISIBLE
            ll_unlogin.click { Action(LoginActivity::class.java) }
        }else{
            ll_unlogin.visibility =View.GONE
            ll_wqhd.visibility = View.GONE
            ll_no_josin.visibility = View.GONE
            iv_message.click {
                if (SharedUtils.getString("token").isNotEmpty()) {
                    Action(MessageActivity::class.java)
                }else{
                    Action(LoginActivity::class.java)
                }
            }
            rv_activite_review.layoutManager = LinearLayoutManager(activity!!).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            rv_activite_review.adapter = ActiviteReviewAdapter(activity!!,R.layout.item_activitive_review,array)
        }
        presenter.onGetList()
        ll_search.click {
            Action(SearchActivity::class.java)
        }
        if(SharedUtils.getString("token").isNotEmpty()){

            presenter.getIsMsg()
        }
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {

    }

    override fun onDestory() {

    }

    fun onSuccess(data: MutableList<DataItem>?) {
        rv_location.layoutManager = LinearLayoutManager(activity).also {
            it.orientation = LinearLayoutManager.VERTICAL
        }
        rv_location.adapter = ActiiteAdapter(activity!!,R.layout.item_activitive,data!!).setInfs(this)
    }

    fun MsgCall(msg: String) {
        if(msg == "1"){
            v_msg.visibility = View.VISIBLE
        }else{
            v_msg.visibility = View.GONE
        }
    }

}