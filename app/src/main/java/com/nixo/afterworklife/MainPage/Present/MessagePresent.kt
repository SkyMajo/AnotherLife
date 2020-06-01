package com.nixo.afterworklife.MainPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.MainPage.Acivity.MessageActivity
import com.nixo.afterworklife.MainPage.Data.MyCreateGroupData
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET


class MessagePresent : BasePresenter<MessageActivity>(){


    interface MessageServce{

        @GET("api/group/get/me/join")
        fun onGroupJoinReview(): Deferred<MyCreateGroupData>

@GET("api/message/getGroupMemberCount")
        fun onGroupMemberCount(): Deferred<LoginData>
@GET("api/message/getLikeCount")
        fun getLikeCount(): Deferred<LoginData>
@GET("api/message/getCommentCount")
        fun getCommentCount(): Deferred<LoginData>


    }
    object MessageServceApi : MessageServce by RetrofitClient.retrofit.create(MessageServce::class.java)

    fun getGroupJoinData() {
        GlobalScope.launch(Dispatchers.Main) {
            var mJoinGroup = GroupPresent.MajorGroupServceApi.onGroupJoinReview().await()
            if(mJoinGroup != null && mJoinGroup.code == 200 && mJoinGroup.data != null){
                view.onInitCreateList(mJoinGroup.data)
            }else{
                ToastUtils.newToastCenter(view,"网络异常")
            }
            println("${mJoinGroup.data}")
        }
    }

    fun getGroupMemberCount(){
        GlobalScope.launch (Dispatchers.Main){
            var await = MessageServceApi.onGroupMemberCount().await()
            view.GroupMemberCountSuccess(await.data.count?:"0")
        }
    }
    fun getLikeCount(){
        GlobalScope.launch (Dispatchers.Main){
            var await = MessageServceApi.getLikeCount().await()
            view.getLikeCountSuccess(await.data.count?:"0")
        }
    }
    fun getCommentCount(){
        GlobalScope.launch (Dispatchers.Main){
            var await = MessageServceApi.getCommentCount().await()
            view.getCommentCountSuccess(await.data.count?:"0")
        }
    }

}