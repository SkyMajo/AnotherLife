package com.nixo.afterworklife.ActivitePage.Presenter

import com.nixo.afterworklife.ActivitePage.Activity.CreateActiviteActivity
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.UserPage.Present.UserHeadReviewPresnet
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import kotlinx.coroutines.*
import retrofit2.http.*

class CreateActivitePresent :BasePresenter<CreateActiviteActivity>(){
    interface createActiviteApi{
        @FormUrlEncoded
        @POST("api/activity/release/{group}")
        fun createActiviteI(
            @Path("group") group_id:Int,
            @Field("title")title:String,
            @Field("description")description:String,
            @Field("content")content:String,
            @Field("start_time")start_time:String,
            @Field("logo")logo:String,
            @Field("charge_amount")charge_amount:String,
            @Field("real_amount")real_amount:String,
            @Field("address")address:String
            ):Deferred<LoginData>

  @FormUrlEncoded
        @PATCH("api/activity/edit/{active}")
        fun editActiviteI(
            @Path("active") group_id:Int,
            @Field("title")title:String,
            @Field("description")description:String,
            @Field("content")content:String,
            @Field("start_time")start_time:String,
            @Field("logo")logo:String,
            @Field("charge_amount")charge_amount:String,
            @Field("real_amount")real_amount:String,
            @Field("address")address:String,
            @Field("status")status:Int
            ):Deferred<LoginData>


    }

    object ActiviteManageServce : createActiviteApi by RetrofitClient.retrofit.create(createActiviteApi::class.java)


    fun onCreateA( group_id:Int,title:String,description:String,content:String,start_time:String,logo:String,charge_amount:String,real_amount:String,address:String){
        GlobalScope.launch(Dispatchers.Main) {
            var await = ActiviteManageServce.createActiviteI(
                group_id,
                title,
                description,
                content,
                start_time,
                logo,
                charge_amount,
                real_amount,
                address
            ).await()
            if (await.code == 200) {
                view.OnCreateSuccess()
            }

        }
    } fun onEditA( group_id:Int,title:String,description:String,content:String,start_time:String,logo:String,charge_amount:String,real_amount:String,address:String,stuts:Int){
        GlobalScope.launch(Dispatchers.Main) {
            var await = ActiviteManageServce.editActiviteI(
                group_id,
                title,
                description,
                content,
                start_time,
                logo,
                charge_amount,
                real_amount,
                address,
                stuts
            ).await()
            if (await.code == 200) {
                view.OnCreateSuccess()
            }

        }
    }
    fun getInfoData(id:Int){
        GlobalScope.launch(Dispatchers.Main) {
            var await = ActiviteInfoPresent.onActiviteServceApi.onActiviteApi(id).await()
            if(await.code == 200){
                view.onSuccessInfo(await.data)
            }
        }
    }

    fun upDataHead(headImg:String)
    {
        GlobalScope.launch(Dispatchers.Main) {
            var await = UserHeadReviewPresnet.onUpdataServce.editInformation(
                SharedUtils.getString("username"),
                headImg,
                SharedUtils.getInt("sex"),
                SharedUtils.getString("birthday")
            ).await()
            if(await.code == 200){
                SharedUtils.putString("token",await.data.token)
                view.onSuccess()
            }
        }


    }

}