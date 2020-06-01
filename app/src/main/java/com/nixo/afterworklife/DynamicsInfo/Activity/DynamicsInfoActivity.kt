package com.nixo.afterworklife.DynamicsInfo.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar
import com.lzy.ninegrid.ImageInfo
import com.lzy.ninegrid.preview.NineGridViewClickAdapter
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.DynamicsInfo.Fragment.DynamicsInfoGoodsFragment
import com.nixo.afterworklife.DynamicsInfo.Fragment.DynamicsInfoReplaysFragment
import com.nixo.afterworklife.DynamicsInfo.Fragment.DynamicsInfoZhuanFaFramgnet
import com.nixo.afterworklife.DynamicsInfo.Present.DynamicsInfoPresent
import com.nixo.afterworklife.MainPage.Data.DynamicsInfoDataData
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.ReplayDialog
import com.nixo.afterworklife.Utils.SustainNineGridViewClickAdapter
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.VideoActivity
import com.nixo.afterworklife.Welcome.Moudle.DynamicData
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import kotlinx.android.synthetic.main.activity_dynamics_info.*
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.test_head_info.*
import kotlinx.android.synthetic.main.test_head.iv_single
import kotlinx.android.synthetic.main.test_head.iv_user_avatar
import kotlinx.android.synthetic.main.test_head.iv_video
import kotlinx.android.synthetic.main.test_head.rl_click
import kotlinx.android.synthetic.main.test_head.tv_address
import kotlinx.android.synthetic.main.test_head.tv_content
import kotlinx.android.synthetic.main.test_head.tv_name
import kotlinx.android.synthetic.main.test_head.tv_time_pre

class DynamicsInfoActivity : BaseActivity<DynamicsInfoPresent>(), ReplayDialog.replayInterface {
    override fun onReplay(string: String) {
        presenter.onReplay(string,dynamicasId.toString())
    }

    override fun onLayout(): Int = R.layout.activity_dynamics_info
    var replayIndex = 0
    var goodndex = 0

    private var fragmentLists = ArrayList<Fragment>()
    private var dynamicsInfoReplaysFragment : DynamicsInfoReplaysFragment? = null


    private var dynamicsInfoGoodsFragment : DynamicsInfoGoodsFragment? = null
    private var replayDialog:ReplayDialog? = null
    private var dynamicasId = 5
    fun initFragment(){
        fragmentLists.add(dynamicsInfoReplaysFragment!!)
            fragmentLists.add(dynamicsInfoGoodsFragment!!)
            fragmentLists.add(DynamicsInfoZhuanFaFramgnet())

        }


    override fun onState() {
        rl_title.click {
            finish()
        }
        initStuatsBar()
        replayDialog = ReplayDialog(this)
        replayDialog!!.infs = this
        dynamicasId = intent.getIntExtra("dynamicasId",5)
        presenter.getDynamicsInfo(dynamicasId)
        var userIdExtra = intent.getIntExtra("userId",0)
        //todo 展示info信息
        Glide.with(this).load(intent.getStringExtra("head")).into(iv_user_avatar)
        tv_name.text = intent.getStringExtra("username")?:""
        tv_content.text = intent.getStringExtra("info_text")?:""
        tv_time_pre.text = intent.getStringExtra("time")?:""
        tv_address.text = intent.getStringExtra("location")?:""
       var imgs  = Gson().fromJson(intent.getStringExtra("imgs")?:"", DynamicData::class.java)
        var userId = SharedUtils.getInt("userId")
        tv_titles.text = intent.getStringExtra("info_text")?:""
        var isLike = intent.getBooleanExtra("isLike",false)

        if(SharedUtils.getString("token").isEmpty()){
            ivGoodYes.click {
                Action(LoginActivity::class.java)
            }
            ll_text.click {
                Action(LoginActivity::class.java)
            }
            iv_follow.click {
                Action(LoginActivity::class.java)
            }

        }else{
            if(isLike){
                ivGoodYes.visibility = View.VISIBLE
                ivGoodGone.visibility = View.GONE
            }else{
                ivGoodYes.visibility = View.GONE
                ivGoodGone.visibility = View.VISIBLE
            }
            ivGoodYes.click {
                presenter.onClickGood(dynamicasId.toString())
                ivGoodYes.visibility = View.GONE
                ivGoodGone.visibility = View.VISIBLE
            }
            ivGoodGone.click {
                presenter.onClickGood(dynamicasId.toString())
                ivGoodYes.visibility = View.VISIBLE
                ivGoodGone.visibility = View.GONE
            }
            ll_text.click {
                replayDialog!!.show()
            }
        }





        if (intent.getStringExtra("type") == "replay") {
            replayDialog!!.show()
        }


    }

    private fun initStuatsBar() {
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()
    }

    //底部Fragnemtn数组封装
    private fun initTabLayout() {
        dynamicsInfoReplaysFragment = DynamicsInfoReplaysFragment()
        dynamicsInfoGoodsFragment = DynamicsInfoGoodsFragment()
        dynamicsInfoReplaysFragment!!.setDyanmicsReplayData(dynamicasId)
        dynamicsInfoGoodsFragment!!.setDyanmicsReplayData(dynamicasId)
        initFragment()
        stl_dynamics.setViewPager(vp_stl_dynamics,titles,this,fragmentLists)
        vp_stl_dynamics.setCurrentItem(0,true)
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {

    }



    override fun onDestory() {}
    private var titles:Array<String> = arrayOf("评论", "赞", "转发")
    fun onReuqestSuccess(replaySize:Int,likeSize:Int,zhuanFaSize:Int) {
        replayIndex = replaySize
        goodndex = likeSize
        titles[0] = "评论$replaySize"
        titles[1] = "赞$likeSize"
        titles[2] = "转发$zhuanFaSize"
        initTabLayout()
    }

    fun setNineGridView(imageInfo: java.util.ArrayList<ImageInfo>) {
        if(imageInfo.size != 1){
            iv_img.setAdapter(SustainNineGridViewClickAdapter(this.applicationContext, imageInfo))
        }

    }

    fun onReplaySuccess() {
        dynamicsInfoReplaysFragment!!.onRefash()
        replayDialog!!.dismiss()
        titles[0] = "评论${ replayIndex++}"
        dynamicsInfoReplaysFragment!!.view!!.invalidate()
//        stl_dynamics.setViewPager(vp_stl_dynamics,titles,this,fragmentLists)


    }

    fun onGoodsSuccess() {
        titles[1] = "赞${goodndex++}"
        dynamicsInfoGoodsFragment!!.onRefash()
    }

    fun setFlow(userId:Int,follow: Boolean) {
        var userIdExtra = SharedUtils.getInt("userId")
        if(userIdExtra == null || userId == userIdExtra  || SharedUtils.getString("token").isEmpty()){
            //是自己的情况
            iv_follow.visibility = View.GONE
        }else{
            if (follow){
                Glide.with(this).load(R.mipmap.flowed).into(iv_follow)
            }else{
                Glide.with(this).load(R.mipmap.flow_new).into(iv_follow)
            }
            iv_follow.click {
                presenter.onFlow(userId.toString())
            }
        }

    }

    fun onFlowSuccess(msg:String) {
        ToastUtils.newToastCenter(this,msg)
        if(msg.length == 3){
            Glide.with(this).load(R.mipmap.flowed).into(iv_follow)

        }else{
            Glide.with(this).load(R.mipmap.flow_new).into(iv_follow)
        }

    }

    fun onFlowFail() {
      ToastUtils.newToastCenter(this,"关注失败，请重新关注")
    }

    fun initViewImg(data: DynamicsInfoDataData) {
        //todo 展示info信息
        Glide.with(this).load(data.user.avatar).into(iv_user_avatar)
        tv_name.text = data.user.username?:""
        tv_content.text =data.content?:""
        tv_time_pre.text =data.createdAt?:""
        tv_address.text = data.address?:""
        var imgs  = Gson().fromJson(intent.getStringExtra("imgs")?:"", DynamicData::class.java)
        var userId = SharedUtils.getInt("userId")
        tv_titles.text = data.content
//        tv_titles.text = intent.getStringExtra("info_text")?:""
//        var isLike = data
        if(data.video.isNotEmpty()){
            rl_click.click {
                var intent = Intent(this, VideoActivity::class.java)
                intent.putExtra("uri",data.video)
                intent.putExtra("title",data.user.username)
                this.startActivity(intent)
            }
            Glide.with(this).load(intent.getStringExtra("first_frame")).into(iv_video)
            iv_video.visibility = View.VISIBLE
            rv_video.visibility = View.VISIBLE
            iv_single.visibility = View.GONE
            iv_img.visibility = View.GONE
        }else{
            if(data.images != null && data.images.size == 1){
                iv_video.visibility = View.GONE
                rv_video.visibility = View.GONE
                iv_single.visibility = View.VISIBLE
                iv_img.visibility = View.GONE
                Glide.with(this).load(data.images[0]).into(iv_single)
            }else {
                iv_video.visibility = View.GONE
                rv_video.visibility = View.GONE
                iv_single.visibility = View.GONE
                iv_img.visibility = View.VISIBLE
                val imageInfo = ArrayList<ImageInfo>()
                val imageDetails = data.images
                if (imageDetails != null) {
                    for (imageDetail in imageDetails!!) {
                        val info = ImageInfo()
                        info.setThumbnailUrl(imageDetail)
                        info.setBigImageUrl(imageDetail)
                        imageInfo.add(info)
                    }
                }
                iv_img.setAdapter(NineGridViewClickAdapter(this, imageInfo))
            }
//            }
        }
    }

}
