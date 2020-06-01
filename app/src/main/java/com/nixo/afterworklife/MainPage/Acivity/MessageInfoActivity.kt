package com.nixo.afterworklife.MainPage.Acivity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyf.immersionbar.ImmersionBar
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.DynamicsInfo.Adapter.GoodsinfoAdapter
import com.nixo.afterworklife.DynamicsInfo.Adapter.GroupShenqingAdapter
import com.nixo.afterworklife.DynamicsInfo.Adapter.ShenqingAdapter
import com.nixo.afterworklife.MainPage.Adapter.MessageInfoCommentAdatper
import com.nixo.afterworklife.MainPage.Data.MessageInfo.DataItem
import com.nixo.afterworklife.MainPage.Present.MessageInfoPresent
import com.nixo.afterworklife.R
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_message_info.*
import kotlinx.android.synthetic.main.include_no_content_big.*
import kotlinx.android.synthetic.main.include_titlebar.*

class MessageInfoActivity : BaseActivity<MessageInfoPresent>() , ShenqingAdapter.applyInfs ,GroupShenqingAdapter.applyInfs,XRecyclerView.LoadingListener{
    override fun onRefresh() {
        xrv_message_info.refreshComplete()
    }

    override fun onLoadMore() {
       xrv_message_info.loadMoreComplete()
    }

    override fun onDeny(string: String) {
        AlertUtils.showProgress(false,this)
        presenter.apply(string,"refuse")
    }

    override fun onApply(string: String) {
        AlertUtils.showProgress(false,this)
        presenter.apply(string,"agree")
    }

    override fun onLayout(): Int = R.layout.activity_message_info

    override fun onState() {
        AlertUtils.showProgress(false,this)
        xrv_message_info.setLoadingListener(this)
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()
        when(intent.getStringExtra("startType")){
            "全部"->{
                tv_title.text = "全部通知"
                AlertUtils.dismissProgress()
                ll_no_content.visibility = View.VISIBLE
            }
            "申请"->{
                tv_title.text = "社团申请"
                presenter.getSQData()
            }
            "评论"->{
                tv_title.text = "动态评论"
                presenter.getLikeCommentList()
            }
            "点赞"->{
                tv_title.text = "我的点赞"
                presenter.getLikeList()
            }
            "group"->{
                tv_title.text = "审核列表"
                presenter.getGroupRequest(intent.getIntExtra("id",0))
            }
        }
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }





    fun onCommentList(data: com.nixo.afterworklife.MainPage.Data.MessageInfo.CommentList.NewData.Data) {
        if(data == null || data.comments!!.size  == 0){
            ll_no_content.visibility = View.VISIBLE
        }else{
            xrv_message_info.layoutManager = LinearLayoutManager(this)
            xrv_message_info.adapter = MessageInfoCommentAdatper(this,R.layout.item_message_replay,data.comments!!)
        }
        AlertUtils.dismissProgress()


    }



    fun onLikeSuccess(data: com.nixo.afterworklife.MainPage.Data.MessageInfo.Goods.Data) {
        if(data == null || data.likes!!.size  == 0){
            ll_no_content.visibility = View.VISIBLE
        }else{
            xrv_message_info.layoutManager = LinearLayoutManager(this)
            xrv_message_info.adapter = GoodsinfoAdapter(this,R.layout.item_goods,data.likes!!)
        }
        AlertUtils.dismissProgress()
    }

    fun onSQSuccess(data: MutableList<DataItem>) {
        if(data == null || data!!.size  == 0){
            ll_no_content.visibility = View.VISIBLE
        }else{
            xrv_message_info.layoutManager = LinearLayoutManager(this)
            xrv_message_info.adapter = ShenqingAdapter(this,R.layout.item_shenqing,data).setInfs(this)
        }

        AlertUtils.dismissProgress()
    }

    fun dismiss() {
        AlertUtils.dismissProgress()
    }

    fun onGroupApplySuccess(data: MutableList<com.nixo.afterworklife.GroupPage.GroupManagerData.GroupApplyData.DataItem>) {
        if(data == null || data!!.isEmpty()){
            ll_no_content.visibility = View.VISIBLE
        }else{
            xrv_message_info.layoutManager = LinearLayoutManager(this)
            xrv_message_info.adapter = GroupShenqingAdapter(this,R.layout.item_shenqing,data).setInfs(this)
        }

        AlertUtils.dismissProgress()
    }
}
