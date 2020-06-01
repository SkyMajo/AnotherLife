package com.nixo.afterworklife.GroupPage.GroupFragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nixo.afterworklife.ActivitePage.Activity.ActiviteInfoActivity
import com.nixo.afterworklife.ActivitePage.Adapter.ActiiteGroupAdapter
import com.nixo.afterworklife.ActivitePage.DATA.DataItem
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.GroupPage.GroupPresent.GroupActivitePresenter
import com.nixo.afterworklife.GroupPage.GroupPresent.MainActivitePresenter
import com.nixo.afterworklife.MainPage.Adapter.ActiiteAdapter
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import kotlinx.android.synthetic.main.activity_activite_manager.*
import kotlinx.android.synthetic.main.fragment_activite.*

class MainActiviteFragment :BaseFragment<MainActivitePresenter>(),ActiiteAdapter.onJoinInfs,ActiiteGroupAdapter.onGroupActiviteInfs{
    override fun onEdit(id: Int) {
    }

    override fun onDelete(id: Int) {
    }

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
    override fun setLayoutParame(): Int  = R.layout.fragment_activite_group

    override fun onState() {

        presenter.onGetList()
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
}

