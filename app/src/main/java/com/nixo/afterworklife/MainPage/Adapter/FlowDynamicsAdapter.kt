package com.nixo.afterworklife.MainPage.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import android.widget.*
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.lzy.ninegrid.ImageInfo
import com.lzy.ninegrid.NineGridView
import com.lzy.ninegrid.preview.ImagePreviewActivity
import com.lzy.ninegrid.preview.NineGridViewClickAdapter
import com.nixo.afterworklife.DynamicsInfo.Activity.DynamicsInfoActivity
import com.nixo.afterworklife.MainPage.Acivity.GroupInfoActivity
import com.nixo.afterworklife.R
import com.nixo.afterworklife.UserPage.PublicUserActivity
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.Ext.otherwise
import com.nixo.afterworklife.Utils.Ext.yes
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.nixo.afterworklife.Utils.TimeCalculator
import com.nixo.afterworklife.VideoActivity
import com.nixo.afterworklife.Welcome.Moudle.DynamicData
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder
import retrofit2.http.Url
import java.io.Serializable
import java.net.URL


class FlowDynamicsAdapter(var context: Context, layout:Int, list : MutableList<DynamicData>) : BaseAdapter<DynamicData>(context,layout,list) {

    interface FlowDynamicsInfs {
        fun onClickGood(explore_id: String)
    }

    var infs: FlowDynamicsInfs? = null

    fun setFlowDynamicsInfs(infs: FlowDynamicsInfs): FlowDynamicsAdapter {
        this.infs = infs
        return this
    }

    override fun initView(p0: BaseHolder, position: Int) {
        var tvContent = p0.getView<TextView>(R.id.tv_content)
        var ivImg = p0.getView<NineGridView>(R.id.iv_img)
        var ivReview = p0.getView<ImageView>(R.id.iv_review)
        var tvAddress = p0.getView<TextView>(R.id.tv_address)
        var ivUserAvatar = p0.getView<ImageView>(R.id.iv_user_avatar)
        var ivGoodYes = p0.getView<ImageView>(R.id.iv_good_yes)
        var ivGoodGone = p0.getView<ImageView>(R.id.iv_good_gone)
        var ivSingle = p0.getView<ImageView>(R.id.iv_single)
        var iv_video = p0.getView<ImageView>(R.id.iv_video)
        var iv_review_video = p0.getView<ImageView>(R.id.iv_review_video)
        var tvName = p0.getView<TextView>(R.id.tv_name)
        var tvLikeNum = p0.getView<TextView>(R.id.tv_like_num)
        var tvComments = p0.getView<TextView>(R.id.tv_commont_num)
        var tvTimePre = p0.getView<TextView>(R.id.tv_time_pre)
        var rlClick = p0.getView<RelativeLayout>(R.id.rl_click)
        var llGroup = p0.getView<LinearLayout>(R.id.ll_group)
        var ll_user = p0.getView<LinearLayout>(R.id.ll_user)
        var ll_replay = p0.getView<LinearLayout>(R.id.ll_replay)
        var ll_bg = p0.getView<LinearLayout>(R.id.ll_bg)
        var rl_video = p0.getView<RelativeLayout>(R.id.rv_video)
        var line = p0.getView<View>(R.id.line)
        if (mDataList[position].relation_group_id != null && mDataList[position].relation_group_id != 0) {
            llGroup.visibility = View.VISIBLE
            llGroup.click {
                var intent = Intent(context, GroupInfoActivity::class.java)
                intent.putExtra("name", mDataList[position].relation_group_id)
                context.startActivity(intent)
            }
        } else {
            llGroup.visibility = View.GONE
        }



        if (position == mDataList.size - 1) {
            line.visibility = View.GONE
        } else {
            line.visibility = View.VISIBLE
        }
        var tvGroupJump = p0.getView<TextView>(R.id.tv_group_jump)
        if(mDataList[position].relation_group != null){
            tvGroupJump.visibility = View.VISIBLE
            tvGroupJump.text = mDataList[position].relation_group.name
        }else{
            tvGroupJump.visibility = View.GONE
//            tvGroupJump.text = "#"+mDataList[position].relation_group.name
        }

        tvContent.text = mDataList[position].content ?: ""
        mDataList[position].address.isEmpty().yes {
            tvAddress.text = "未知"
        }.otherwise{
        tvAddress.text = mDataList[position].address ?: "未知"
        }
        tvLikeNum.text = mDataList[position].likes.toString() ?: ""
        tvComments.text = mDataList[position].comments.toString() ?: ""
        ll_replay.click {
            if (SharedUtils.getString("token").isEmpty()) {
                context.startActivity(Intent(context, LoginActivity::class.java))
            } else {
                var intent = Intent(context, DynamicsInfoActivity::class.java)
                intent.putExtra("type", "replay")
                intent.putExtra("dynamicasId", mDataList[position].id)
                intent.putExtra("video", mDataList[position].video)
                intent.putExtra("imgs", Gson().toJson(mDataList[position]))
                intent.putExtra("userId", mDataList[position].userId)
                if (mDataList[position].user != null) {
                    intent.putExtra("username", mDataList[position]!!.user!!.username)
                    intent.putExtra("head", mDataList[position]!!.user!!.avatar)
                }
                intent.putExtra("info_text", mDataList[position].content)
                intent.putExtra("isLike", mDataList[position].is_like)
                intent.putExtra("location", mDataList[position].address)
                intent.putExtra(
                    "time",
                    TimeCalculator.getTimeFormatText(mDataList[position].createdAt)
                )
                context.startActivity(intent)
            }
        }

        ll_bg.click {
                var intent = Intent(context, DynamicsInfoActivity::class.java)
//                intent.putExtra("type", "replay")
                intent.putExtra("dynamicasId", mDataList[position].id)
                intent.putExtra("video", mDataList[position].video)
                intent.putExtra("imgs", Gson().toJson(mDataList[position]))
                intent.putExtra("userId", mDataList[position].userId)
                if (mDataList[position].user != null) {
                    intent.putExtra("username", mDataList[position]!!.user!!.username)
                    intent.putExtra("head", mDataList[position]!!.user!!.avatar)
                }
                intent.putExtra("info_text", mDataList[position].content)
                intent.putExtra("isLike", mDataList[position].is_like)
                intent.putExtra("location", mDataList[position].address)
                intent.putExtra(
                    "time",
                    TimeCalculator.getTimeFormatText(mDataList[position].createdAt)
                )
                context.startActivity(intent)
        }

        ivUserAvatar.click {
            var intent = Intent(context, PublicUserActivity::class.java)
            intent.putExtra("userId", mDataList[position].userId)
            context.startActivity(intent)
        }
//
//        llGroup.click {
//            var intent = Intent(context, GroupInfoActivity::class.java)
//            intent.putExtra("name",mDataList[position].relationGroup.id)
//            context.startActivity(intent)
//
//        }
        if (mDataList[position].user != null) {
            tvName.text = mDataList[position].user.username ?: ""
            Glide.with(context).load(mDataList[position]!!.user!!.avatar).into(ivUserAvatar)
        }

        if (mDataList[position].images == null && mDataList[position].video.isEmpty() ) {
            iv_video.visibility = View.GONE
            rl_video.visibility = View.GONE
            ivSingle.visibility = View.GONE
            ivImg.visibility = View.GONE
        }else if(mDataList[position].images == null && mDataList[position].video.isNotEmpty()){
            iv_review_video.visibility = View.VISIBLE
            Glide.with(context).load(mDataList[position].first_frame).into(iv_video)
            rlClick.click {
                var intent = Intent(context, VideoActivity::class.java)
                intent.putExtra("uri", mDataList[position].video)
                intent.putExtra("first_frame", mDataList[position].first_frame)
                intent.putExtra("title", mDataList[position].user.username)
                context.startActivity(intent)
            }
            iv_video.visibility = View.VISIBLE
            rl_video.visibility = View.VISIBLE
            ivSingle.visibility = View.GONE
            ivImg.visibility = View.GONE
        }else if(mDataList[position].images != null && mDataList[position].video.isEmpty()){
            iv_video.visibility = View.GONE
            rl_video.visibility = View.VISIBLE
            if(mDataList[position].images.size>1){
                ivSingle.visibility = View.GONE
                ivImg.visibility = View.VISIBLE
                val imageInfo = ArrayList<ImageInfo>()
                val imageDetails = mDataList[position].images
                if (imageDetails != null) {
                    for (imageDetail in imageDetails!!) {
                        val info = ImageInfo()
                        info.setThumbnailUrl(imageDetail)
                        info.setBigImageUrl(imageDetail)
                        imageInfo.add(info)
                    }
                }
                ivImg.setAdapter(NineGridViewClickAdapter(context, imageInfo))
            }else{
                ivSingle.visibility = View.VISIBLE
                ivImg.visibility = View.GONE
                Glide.with(context).load(mDataList[position].images[0]).into(ivSingle)
                ivSingle.click {
                    val intent = Intent(context, ImagePreviewActivity::class.java)
                    val bundle = Bundle()
                    val info = ImageInfo()
                    info.setThumbnailUrl(mDataList[position].images[0])
                    info.setBigImageUrl(mDataList[position].images[0])
                    val imageInfo = ArrayList<ImageInfo>()
                    imageInfo.add(info)
                    bundle.putSerializable(
                        ImagePreviewActivity.IMAGE_INFO,
                        imageInfo as Serializable
                    )
                    bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, 0)
                    intent.putExtras(bundle)
                    context.startActivity(intent)
                    (context as Activity).overridePendingTransition(0, 0)
                }
            }
        }







        ivReview.click {
            var intent = Intent(context, DynamicsInfoActivity::class.java)
            intent.putExtra("dynamicasId", mDataList[position].id)
            intent.putExtra("video", mDataList[position].video)
            intent.putExtra("imgs", Gson().toJson(mDataList[position]))
            intent.putExtra("userId", mDataList[position].userId)
            if (mDataList[position].user != null) {
                intent.putExtra("username", mDataList[position]!!.user!!.username)
                intent.putExtra("head", mDataList[position]!!.user!!.avatar)
            }
            intent.putExtra("info_text", mDataList[position].content)
            intent.putExtra("isLike", mDataList[position].is_like)
            intent.putExtra("location", mDataList[position].address)
            intent.putExtra(
                "time",
                TimeCalculator.getTimeFormatText(mDataList[position].createdAt)
            )
            intent.putExtra("bg", "https://i.loli.net/2019/11/14/kqv2CfEjgZcSzUo.png")
            context.startActivity(intent)
        }

        if (mDataList[position].is_like) {
            ivGoodYes.visibility = View.VISIBLE
            ivGoodGone.visibility = View.GONE
        } else {
            ivGoodYes.visibility = View.GONE
            ivGoodGone.visibility = View.VISIBLE
        }
        ivGoodYes.click {
            if (SharedUtils.getString("token").isEmpty()) {
                context.startActivity(Intent(context, LoginActivity::class.java))
            } else {
                tvLikeNum.text = (--mDataList[position].likes ?: "").toString()
                infs!!.onClickGood(mDataList[position].id.toString() ?: "")
                ivGoodYes.visibility = View.GONE
                ivGoodGone.visibility = View.VISIBLE
            }
        }
        ivGoodGone.click {
            tvLikeNum.text = (++mDataList[position].likes).toString() ?: ""
            infs!!.onClickGood(mDataList[position].id.toString()) ?: ""
            ivGoodYes.visibility = View.VISIBLE
            ivGoodGone.visibility = View.GONE
        }

        Glide.with(context).load(mDataList[position].user.avatar ?: "").into(ivUserAvatar)
        tvTimePre.text = TimeCalculator.getTimeFormatText(mDataList[position].createdAt)

    }
}
