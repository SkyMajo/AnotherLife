package com.nixo.afterworklife.MainPage.Acivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.MainPage.Adapter.GroupJoinReviewAdapter
import com.nixo.afterworklife.MainPage.Adapter.MessageReviewAdapter
import com.nixo.afterworklife.MainPage.Data.Data
import com.nixo.afterworklife.MainPage.Present.MessagePresent
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import kotlinx.android.synthetic.main.activity_message.*
import kotlinx.android.synthetic.main.include_titlebar.*

class MessageActivity : BaseActivity<MessagePresent>() {
    override fun onLayout(): Int = R.layout.activity_message

    override fun onState() {
        tv_title.text = "消息"
        ll_title.click {
            finish()
        }
        presenter.getGroupJoinData()
        presenter.getCommentCount()
        presenter.getGroupMemberCount()
        presenter.getLikeCount()
        tv_tongzhi.click {
            var bundle = Bundle()
            bundle.putString("startType","全部")
            paramerAction(MessageInfoActivity::class.java,bundle)
        }
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {

    }

    override fun onDestory() {}


    fun onInitCreateList(data: MutableList<Data>) {
        xrv_message.layoutManager = LinearLayoutManager(this).also {
            it.orientation = LinearLayoutManager.VERTICAL
        }
        xrv_message.adapter = MessageReviewAdapter(this,
            R.layout.item_message_review,data)
    }

    fun GroupMemberCountSuccess(s: String) {
        tv_shetuan.text = "申请($s)"
        tv_shetuan.click {
            var bundle = Bundle()
            bundle.putString("startType","申请")
            paramerAction(MessageInfoActivity::class.java,bundle)

        }
    }

    fun getLikeCountSuccess(s: String) {
        tv_zan.text = "点赞($s)"
        tv_zan.click {
            var bundle = Bundle()
            bundle.putString("startType","点赞")
            paramerAction(MessageInfoActivity::class.java,bundle)
        }
    }

    fun getCommentCountSuccess(s: String) {
        tv_pinglun.text = "评论($s)"
        tv_pinglun.click {
            var bundle = Bundle()
            bundle.putString("startType","评论")
            paramerAction(MessageInfoActivity::class.java,bundle)
        }

    }

}
