package com.nixo.afterworklife.ActivitePage.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyf.immersionbar.ImmersionBar
import com.nixo.afterworklife.ActivitePage.Adapter.GroupChoseAdapter
import com.nixo.afterworklife.ActivitePage.Presenter.GroupChosePresent
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.MainPage.Data.Data
import com.nixo.afterworklife.R
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_group_chose.*
import kotlinx.android.synthetic.main.include_no_content.*
import kotlinx.android.synthetic.main.include_titlebar.*

class GroupChoseActivity : BaseActivity<GroupChosePresent>() ,GroupChoseAdapter.GroupChoseCallBack{
    override fun onResult(string: String, id: Int) {
        var intent = Intent()
        intent.putExtra("name",string)
        intent.putExtra("id",id)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    override fun onLayout(): Int = R.layout.activity_group_chose

    override fun onState() {
        AlertUtils.showProgress(false,this)
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()
        tv_title.text = "选择社团"
        presenter.getGroupJoinData()

    }

    //加入的社团
    fun onInitJoinList(data: MutableList<Data>) {
        if(data.size == 0){
            ll_no_content.visibility = View.VISIBLE
        }else{
                ll_no_content.visibility = View.GONE
                srv_group_review.layoutManager = LinearLayoutManager(this).also {
                    it.orientation = LinearLayoutManager.VERTICAL
                }
                srv_group_review.adapter = GroupChoseAdapter(this, R.layout.item_group_chose,data,this)

        }

        AlertUtils.dismissProgress()
    }
    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}


}
