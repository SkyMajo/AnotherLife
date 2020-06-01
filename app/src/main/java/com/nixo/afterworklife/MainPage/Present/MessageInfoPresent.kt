package com.nixo.afterworklife.MainPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.GroupPage.GroupManagerData.GroupApplyData.GroupApplyData
import com.nixo.afterworklife.MainPage.Acivity.MessageInfoActivity
import com.nixo.afterworklife.MainPage.Data.MessageInfo.CommentList.NewData.NewCommentListData
import com.nixo.afterworklife.MainPage.Data.MessageInfo.Goods.GoodsInfo
import com.nixo.afterworklife.MainPage.Data.MessageInfo.ShenqingInfoData
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class MessageInfoPresent : BasePresenter<MessageInfoActivity>(){

    interface MessageInfoInfs {
        @GET("api/message/getGroupMemberList")
        fun onShenqingApi(
        ): Deferred<ShenqingInfoData>
        @GET("api/message/getLikeList")
        fun getLikeList(
        ): Deferred<GoodsInfo>
        @GET("api/group/wait/verify/members/{group}")
        fun getGroupRequest(
            @Path("group") group:Int
        ): Deferred<GroupApplyData>
        @GET("api/message/getCommentList")
        fun getCommentList(
        ): Deferred<NewCommentListData>
        @FormUrlEncoded
        @POST("api/group/verify/member/{group_member_id}")
        fun applay(
            @Path("group_member_id") group_member_id :String,
            @Field("result") result:String
        ): Deferred<LoginData>



    }

    object MessageInfoServce : MessageInfoInfs by RetrofitClient.retrofit.create(
        MessageInfoInfs::class.java
    )

    fun getSQData(){
        GlobalScope.launch (Dispatchers.Main){
            var await = MessageInfoServce.onShenqingApi().await()
            if(await.code == 200){
                view.onSQSuccess(await.data!!)
            }
        }
    }

    fun getLikeList(){
        GlobalScope.launch (Dispatchers.Main){
            var await = MessageInfoServce.getLikeList().await()
            if(await.code == 200){
                view.onLikeSuccess(await.data)
            }
        }
    }
    fun getLikeCommentList(){
        GlobalScope.launch (Dispatchers.Main){
            var await = MessageInfoServce.getCommentList().await()
            if(await.code == 200){
                view.onCommentList(await.data)
            }
        }
    }

    fun apply(id:String,isApply : String){
        GlobalScope.launch(Dispatchers.Main) {
            var await = MessageInfoServce.applay(id,isApply).await()
            if(await.code == 200){
                view.dismiss()
                ToastUtils.newToastCenter(view,await.msg)
            }
        }

    }

    fun getGroupRequest(id:Int){
        GlobalScope.launch (Dispatchers.Main){
            var await = MessageInfoServce.getGroupRequest(id).await()
            if(await.code == 200){
                view.onGroupApplySuccess(await.data!!)
            }
        }
    }

}