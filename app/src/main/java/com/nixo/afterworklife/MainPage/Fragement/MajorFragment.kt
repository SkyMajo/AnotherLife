package com.nixo.afterworklife.MainPage.Fragement

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.MainPage.Acivity.MessageActivity
import com.nixo.afterworklife.MainPage.Present.MajorPresent
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Search.SearchActivity
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_major.*
import kotlinx.android.synthetic.main.include_search_title.*

class MajorFragment : BaseFragment<MajorPresent>(){


    private var titles = arrayOf("发现","关注", "社团")
    private var index  = 0
    var findFragment = FindFragment()
    var groupFragment = GroupFragment()
    var specialFragment = FlowFragment()

    private var fragmentLists = ArrayList<Fragment>().also {
        it.add(FindFragment())
        it.add(FlowFragment())
        it.add(GroupFragment())
    }
    override fun setLayoutParame(): Int = R.layout.fragment_major

    override fun onState() {
        if(SharedUtils.getString("token").isNotEmpty()){
                presenter.getIsMsg()
        }
        iv_message.click {
            if (SharedUtils.getString("token").isNotEmpty()) {
                Action(MessageActivity::class.java)
            }else{
                Action(LoginActivity::class.java)
            }
        }
        iv_search.click {
            Action(SearchActivity::class.java)
        }
        stl_major.setViewPager(vp_major,titles,this,fragmentLists)
        vp_major.setCurrentItem(0,true)
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}
    fun MsgCall(msg: String) {
        if(msg == "1"){
            v_msg.visibility = View.VISIBLE
        }else{
            v_msg.visibility = View.GONE
        }
    }

}