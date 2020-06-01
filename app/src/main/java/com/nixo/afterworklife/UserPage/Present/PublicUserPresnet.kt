package com.nixo.afterworklife.UserPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.MainPage.Present.FlowPresent
import com.nixo.afterworklife.UserPage.Moudle.PublicUser.PublucUserData
import com.nixo.afterworklife.UserPage.PublicUserActivity
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.FlowDynamicsData
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class PublicUserPresnet : BasePresenter<PublicUserActivity>(){
    interface PublicUserServceApi{
        @GET("api/user/other/{user}")
        fun onFlowDynamics(@Path("user") user : Int): Deferred<PublucUserData>
        @GET("api/explore/user/release/{user_id}")
        fun onLikeDynamics(@Path("user_id") user : Int): Deferred<FlowDynamicsData>

        @POST("api/explore/like/{explore_id}")
        fun onClickGood(
            @Path("explore_id") explore_id:String
        ): Deferred<LoginData>
    }

    object publicUserApi :PublicUserServceApi by RetrofitClient.retrofit.create(PublicUserServceApi::class.java)

    fun getUserInfo(id:Int){
        GlobalScope.launch (Dispatchers.Main){
            var await = publicUserApi.onFlowDynamics(id).await()
            if(await.code == 200){
                view.onUserSuccess(await.data)
            }
        }
    }

    fun onClickGood(exploreId:String){
        GlobalScope.launch (Dispatchers.Main){
            var await = FlowPresent.FlowApi.onClickGood(exploreId).await()
            if(await.code == 200){
                ToastUtils.newToastCenter(view,await.msg?:"点赞")
            }else{
                ToastUtils.newToastCenter(view,await.msg?:"网络异常")
            }
        }
    }

    fun onGetFlowDyamics(id:Int){
        GlobalScope.launch (Dispatchers.Main){
            var FlowDynamicsResult = publicUserApi.onLikeDynamics(id).await()
            if(FlowDynamicsResult.code != 200){
                ToastUtils.newToastCenter(view,FlowDynamicsResult.msg)
            }else{
                if(FlowDynamicsResult.data.isEmpty()){

                }else{
                    view.onDynamicsCallBack(FlowDynamicsResult.data)
                }
            }
        }

    }

}