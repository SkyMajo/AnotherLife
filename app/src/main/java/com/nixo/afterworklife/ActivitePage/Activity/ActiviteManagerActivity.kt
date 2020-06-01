package com.nixo.afterworklife.ActivitePage.Activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.nixo.afterworklife.ActivitePage.Adapter.ActiiteGroupAdapter
import com.nixo.afterworklife.ActivitePage.DATA.GroupManagerData.DataItem
import com.nixo.afterworklife.ActivitePage.Presenter.ActiviteManagerPresent
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.ConfirmTextDialog
import com.nixo.afterworklife.Utils.Ext.click
import kotlinx.android.synthetic.main.activity_activite_manager.*

class ActiviteManagerActivity : BaseActivity<ActiviteManagerPresent>() ,XRecyclerView.LoadingListener, ActiiteGroupAdapter.onGroupActiviteInfs ,ConfirmTextDialog.ConfirmExitOnClickListener{
    override fun onLoadMore() {
        xrv_group_activite.loadMoreComplete()
    }

    override fun onRefresh() {
        presenter.getActiviteListForGroup(groupId)
        xrv_group_activite.refreshComplete()
    }

    override fun confirmExit(string: String) {
        presenter.getSignUp(activiteId)
    }

    override fun onEdit(id: Int) {
    }

    override fun onDelete(id: Int) {
        activiteId = id
        ConfirmTextDialog(this,"您确认结束该活动的报名吗？").setConfirExitmOnClickListener(this).show()
    }

    var activiteId = 0
    var premsioons = ""
    var groupId = 0
    override fun onLayout(): Int = R.layout.activity_activite_manager

    override fun onState() {
        premsioons = intent.getStringExtra("premsioons")
        groupId = intent.getIntExtra("groupId",0)
        presenter.getActiviteListForGroup(groupId)
        xrv_group_activite.setLoadingListener(this)
        tv_finish.click {
            finish()
//            xrv_group_activite
        }
        tv_create.click {
        var bundle = Bundle()
            bundle.putInt("groupId",intent.getIntExtra("groupId",0))
            paramerAction(CreateActiviteActivity::class.java,bundle)
        }
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}

    fun getSuccess(data: MutableList<DataItem>?) {
        xrv_group_activite.layoutManager = LinearLayoutManager(this).also {
            it.orientation = LinearLayoutManager.VERTICAL
        }
        xrv_group_activite.adapter = ActiiteGroupAdapter(this,R.layout.item_group_activitive,data!!,premsioons).setInfs(this)
    }

    fun RefushData() {
        presenter.getActiviteListForGroup(groupId)
    }


}
