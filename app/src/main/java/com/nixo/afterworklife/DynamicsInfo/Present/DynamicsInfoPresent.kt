package com.nixo.afterworklife.DynamicsInfo.Present

import android.view.View
import com.lzy.ninegrid.ImageInfo
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.DynamicsInfo.Activity.DynamicsInfoActivity
import com.nixo.afterworklife.MainPage.Data.DynamicsInfoData
import com.nixo.afterworklife.MainPage.Present.FlowPresent
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class DynamicsInfoPresent :BasePresenter<DynamicsInfoActivity>(){
    interface DynamicsInfoServce {

        @POST("api/explore/like/{explore_id}")
        fun onClickGood(
            @Path("explore_id") explore_id:String
        ): Deferred<LoginData>

        @GET("api/explore/info/{explore_id}")
        fun onDynamicsInfo(
            @Path("explore_id") explore_id : String): Deferred<DynamicsInfoData>

//        @FormUrlEncoded
        @POST("api/user/follow/{user_id}")
        fun onFlow(
            @Path("user_id") userId : String): Deferred<LoginData>


        @FormUrlEncoded
        @POST("api/explore/comment/{explore_id}")
        fun onReplay(
            @Path("explore_id") explore_id:String,
            @Field("content") content: String
//            @Field("reply_id") reply_id: String

        ): Deferred<DynamicsInfoData>
    }
    object DynamicsInfoServceApi : DynamicsInfoServce by RetrofitClient.retrofit.create(
        DynamicsInfoServce::class.java)

    interface DynamicsDataInfs{
       fun  onDynamicsInfoData(data :DynamicsInfoData)
    }

    var infs : DynamicsDataInfs? = null

    fun setDynamicsInfs(infs: DynamicsDataInfs){
        this.infs = infs
    }

    fun getDynamicsInfo(id:Int){
        GlobalScope.launch(Dispatchers.Main) {
            var data = DynamicsInfoServceApi.onDynamicsInfo(id.toString()).await()
            if(data.code == 200 && data.data != null){
                val imageInfo = ArrayList<ImageInfo>()
                val imageDetails = data.data.images
                if (imageDetails != null) {
                    for (imageDetail in imageDetails!!) {
                        val info = ImageInfo()
                        info.setThumbnailUrl(imageDetail)
                        info.setBigImageUrl(imageDetail)
                        imageInfo.add(info)
                    }
                    view.setNineGridView(imageInfo)
                }
                view.initViewImg(data.data)
                view.setFlow(data.data.userId,data.data.isFollow)
                view.onReuqestSuccess(data.data.commentPeoples.size,data.data.likePeoples!!.size,0)
            }
        }
    }
    fun onClickGood(exploreId:String){
        GlobalScope.launch (Dispatchers.Main){
            var await = FlowPresent.FlowApi.onClickGood(exploreId).await()
            if(await.code == 200){
                view.onGoodsSuccess()
                ToastUtils.newToastCenter(view,await.msg?:"点赞")

            }else{
                ToastUtils.newToastCenter(view,await.msg?:"网络异常")
            }
        }
    }
    fun onReplay(replays:String ,replayId:String){
        GlobalScope.launch (Dispatchers.Main){
            var await = DynamicsInfoServceApi.onReplay(replayId, replays).await()
            if(await.code == 200){
                view.onReplaySuccess()
                ToastUtils.newToastCenter(view,await.msg)
            }else{
                ToastUtils.newToastCenter(view,await.msg)
            }
        }
    }
    fun onFlow(userId: String){
        GlobalScope.launch (Dispatchers.Main){
            var await = DynamicsInfoServceApi.onFlow(userId).await()
            if(await.code == 200){
                view.onFlowSuccess(await.msg)
            }else{
                view.onFlowFail()
            }
        }
    }

}