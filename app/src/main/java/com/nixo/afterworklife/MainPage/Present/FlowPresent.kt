package com.nixo.afterworklife.MainPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.MainPage.Data.IdolData
import com.nixo.afterworklife.MainPage.Fragement.FlowFragment
import com.nixo.afterworklife.UserPage.Present.FansPresent
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

class FlowPresent : BasePresenter<FlowFragment>() {
    interface FlowSever{
        @GET("api/explore/get/follow")
        fun onFlowDynamics(): Deferred<FlowDynamicsData>

        @POST("api/explore/like/{explore_id}")
        fun onClickGood(
            @Path("explore_id") explore_id:String
        ): Deferred<LoginData>
        @GET("api/user/my/idol")
        fun  onIdolApi(
        ): Deferred<IdolData>

    }

    object FlowApi :FlowSever by RetrofitClient.retrofit.create(FlowSever::class.java)


    fun onGetFlowDyamics(){
        GlobalScope.launch (Dispatchers.Main){
            var FlowDynamicsResult = FlowApi.onFlowDynamics().await()
            if(FlowDynamicsResult.code != 200){
                ToastUtils.newToastCenter(view.activity!!,FlowDynamicsResult.msg)
            }else{
                if(FlowDynamicsResult.data.isEmpty()){

                }else{
                    view.onDynamicsCallBack(FlowDynamicsResult.data)
                }
            }
        }

    }

    fun onClickGood(exploreId:String){
        GlobalScope.launch (Dispatchers.Main){
            var await = FlowApi.onClickGood(exploreId).await()
            if(await.code == 200){
                ToastUtils.newToastCenter(view.activity!!,await.msg?:"点赞")
                view.onClickGoodSuccess()
            }else{
                ToastUtils.newToastCenter(view.activity!!,await.msg?:"网络异常")
            }
        }
    }

    fun onIdol(){
        GlobalScope.launch (Dispatchers.Main ){
            var await = FansPresent.FansServceApi.onIdolApi().await()
            if(await.code != 200){
                ToastUtils.newToastCenter(view.activity!!,await.msg)
            }
            if(await.data != null)
            {
                view.onIdolSuccess(await.data!!)
            }
        }
    }

}
