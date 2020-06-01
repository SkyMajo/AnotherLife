package com.nixo.afterworklife.GroupPage.GroupActivity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyf.immersionbar.ImmersionBar
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.GroupPage.GroupPresent.AllGroupPresent
import com.nixo.afterworklife.MainPage.Adapter.LocationGroupAdapter
import com.nixo.afterworklife.MainPage.Data.GroupData.LocationDataItem
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_all_group.*
import kotlinx.android.synthetic.main.include_titlebar.*

class AllGroupActivity : BaseActivity<AllGroupPresent>(),OnRefreshListener,OnLoadMoreListener {
    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh()

    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        refreshLayout.finishLoadMore()
    }

    override fun onLayout(): Int = R.layout.activity_all_group




    override fun onState() {
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()
        tv_title.text = "所有社团"
        ll_title.click { finish() }
        AlertUtils.showProgress(false,this)
        presenter.onGetAllGroup()
        rv_all.layoutManager = LinearLayoutManager(this)

    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}
    fun initAllGroup(data: MutableList<LocationDataItem>) {
        rv_all.adapter = LocationGroupAdapter(this, R.layout.item_group,data,1)
        AlertUtils.dismissProgress()
    }


}
