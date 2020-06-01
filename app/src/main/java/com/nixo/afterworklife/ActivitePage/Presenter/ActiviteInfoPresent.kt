package com.nixo.afterworklife.ActivitePage.Presenter

import com.nixo.afterworklife.ActivitePage.Activity.ActiviteInfoActivity
import com.nixo.afterworklife.ActivitePage.DATA.AInfoData.ActiviteInfoData
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class ActiviteInfoPresent :BasePresenter<ActiviteInfoActivity>(){


//    api/activity/detail/{active}
interface ActiviteInfoApi{
    @GET("api/activity/detail/{active}")
    fun onActiviteApi( @Path("active") active:Int): Deferred<ActiviteInfoData>


 @POST("api/activity/cancel/signup/{active_id}")
    fun onCancelApi( @Path("active_id") active:Int): Deferred<LoginData>

    @FormUrlEncoded
    @POST("api/activity/sign/up/{active_id}")
    fun onStartApi(
        @Path("active_id") active:Int,
        @Field("phone") phone: String,
        @Field("realname") realname: String
        ): Deferred<LoginData>


}

    object onActiviteServceApi :ActiviteInfoApi by RetrofitClient.retrofit.create(ActiviteInfoApi::class.java)


    fun getInfoData(id:Int){
        GlobalScope.launch(Dispatchers.Main) {
            var await = onActiviteServceApi.onActiviteApi(id).await()
            if(await.code == 200){
                view.onSuccess(await.data)
            }
        }
    }
    fun onStart(id: Int,phone: String,name:String){
        GlobalScope.launch(Dispatchers.Main) {
            var await = onActiviteServceApi.onStartApi(id, phone, name).await()
            if(await.code == 200){
                view.OnJoinSuccess()
            }else{
                ToastUtils.newToastCenter(view,"网络异常")
            }
        }
    }

  fun onCalcen(id: Int){
        GlobalScope.launch(Dispatchers.Main) {
            var await = onActiviteServceApi.onCancelApi(id).await()
            if(await.code == 200){
                view.onCancelSuccess()
            }
        }
    }


}