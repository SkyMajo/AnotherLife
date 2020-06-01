package com.nixo.afterworklife.UserPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.GroupPage.GroupPresent.GroupManagerPresent
import com.nixo.afterworklife.MainPage.Data.IdolData
import com.nixo.afterworklife.UserPage.Moudle.FansData
import com.nixo.afterworklife.UserPage.View.FansActivity
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class FansPresent:BasePresenter<FansActivity>(){

    interface FansServce {
        @GET("api/user/my/follow")
        fun onFanListRequest(
        ): Deferred<FansData>

        @POST("api/user/follow/{user_id}")
        fun  onFollowApi(
            @Path("user_id") userId:String
        ): Deferred<LoginData>
        @GET("api/user/my/idol")
        fun  onIdolApi(

        ): Deferred<IdolData>



    }
    object FansServceApi : FansServce by RetrofitClient.retrofit.create(FansServce::class.java)



    fun onGetFansData(){
        GlobalScope.launch (Dispatchers.Main){
            var await = FansServceApi.onFanListRequest().await()
            if(await.code != 200){
                ToastUtils.newToastCenter(view,await.msg?:"")
            }else{
                view.onGetFansSuccess(await.data)
            }
        }
    }

    fun onFollow(userId: String){
        GlobalScope.launch (Dispatchers.Main){
            var await = FansServceApi.onFollowApi(userId).await()
            ToastUtils.newToastCenter(view,await.msg?:"关注")
        }
    }

    fun onIdol(){
        GlobalScope.launch (Dispatchers.Main ){
            var await = FansServceApi.onIdolApi().await()
            if(await.code != 200){
                ToastUtils.newToastCenter(view,await.msg)
            }
            view.onIdolSuccess(await.data!!)
        }
    }
    fun getGroupUser(groupId: Int){
        GlobalScope.launch(Dispatchers.Main) {
            var await = GroupManagerPresent.GroupManagerServce.getUserLikeTags(groupId).await()
            if(await.code == 200){
                view.UserSuccess(await.data)
            }
        }
    }
}