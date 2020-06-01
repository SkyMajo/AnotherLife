package com.nixo.afterworklife.Search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyf.immersionbar.ImmersionBar
import com.nixo.afterworklife.ActivitePage.Activity.ActiviteInfoActivity
import com.nixo.afterworklife.ActivitePage.DATA.DataItem
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.MainPage.Adapter.ActiiteAdapter
import com.nixo.afterworklife.MainPage.Adapter.FlowDynamicsAdapter
import com.nixo.afterworklife.MainPage.Adapter.LocationGroupAdapter
import com.nixo.afterworklife.MainPage.Data.GroupData.LocationDataItem
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.DynamicData
import com.yqjr.superviseapp.utils.publicUse.bankSwitch.BanrdSwitch
import com.yqjr.superviseapp.utils.publicUse.bankSwitch.SodaDialog
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : BaseActivity<SearchPresent>() ,FlowDynamicsAdapter.FlowDynamicsInfs,
    ActiiteAdapter.onJoinInfs,
    SodaDialog.menuInterface {
    override fun onJoin(id: Int) {
        var bundle = Bundle()
        bundle.putInt("id",id)
        paramerAction(ActiviteInfoActivity::class.java,bundle)
    }

    var index = ""

    override fun onSelect(str: String) {
        index = str
        xrv_search.visibility = View.GONE

    }

//     when(str){
//            "社团"->{}
//            "用户"->{}
//            "活动"->{}
//            "动态"->{}
//        }

    override fun onClickGood(explore_id: String) {
        presenter.onClickGood(explore_id)
    }

    override fun onLayout(): Int = R.layout.activity_search

    override fun onState() {
        tv_cancel.click {
            finish()
        }
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()

        initMenu()

        et_search.setOnEditorActionListener { textView, _, _ -> false
            var searchData = textView.text.toString().trim()
            if(searchData.isNotEmpty()){
                when(index){
                    "社团"->{presenter.onSearchGroup(searchData)}
                    "用户"->{presenter.onSearchUsers(searchData)}
                    "活动"->{presenter.onSearchActivite(searchData)}
                    "动态"->{presenter.onSearchDynamic(searchData)}
                    ""->{presenter.onSearchDynamic(searchData)}
                }
            }else{
                ToastUtils.newToastCenter(this,"请输入搜索内容")
            }
            false

        }


    }

    private fun initMenu() {
        ll_menu.click {
            BanrdSwitch.Builder(this).setInfs(this).setSwitchImg(iv_down).build().show()
        }
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}




    fun onSearchActiviteCallBack(data: MutableList<DataItem>?) {
        if(data!!.size == 0){
            ll_no_content.visibility = View.VISIBLE
        }else{
            xrv_search.visibility = View.VISIBLE
            ll_no_content.visibility = View.GONE
            xrv_search.layoutManager = LinearLayoutManager(this)
            xrv_search.adapter = ActiiteAdapter(this,R.layout.item_activitive,data!!).setInfs(this)
        }
    }

    fun onSearchDynamicCallBack(data: MutableList<DynamicData>) {
        if(data!!.size == 0){
            ll_no_content.visibility = View.VISIBLE
        }else {
            ll_no_content.visibility = View.GONE
            xrv_search.visibility = View.VISIBLE
            xrv_search.layoutManager = LinearLayoutManager(this)
            xrv_search.adapter = FlowDynamicsAdapter(this,R.layout.item_find,data).setFlowDynamicsInfs(this)
        }

    }

    fun onSearchGroupCallBack(data: MutableList<LocationDataItem>) {
        if(data!!.size == 0){
            ll_no_content.visibility = View.VISIBLE
        }else {
            ll_no_content.visibility = View.GONE
            xrv_search.visibility = View.VISIBLE
            xrv_search.layoutManager = LinearLayoutManager(this)
            xrv_search.adapter = LocationGroupAdapter(this,
                R.layout.item_group,data,0)
        }


    }

    fun onSearchUserCallBack(data: MutableList<com.nixo.afterworklife.Search.SearchData.UserData.DataItem>) {
        if(data!!.size == 0){
            ll_no_content.visibility = View.VISIBLE
        }else {
            ll_no_content.visibility = View.GONE
            xrv_search.visibility = View.VISIBLE
            xrv_search.layoutManager = LinearLayoutManager(this)
            xrv_search.adapter =  UserAdapter(this,R.layout.item_fans, data)
        }

    }


}
