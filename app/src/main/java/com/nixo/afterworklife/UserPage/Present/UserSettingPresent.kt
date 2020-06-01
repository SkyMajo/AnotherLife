package com.nixo.afterworklife.UserPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.UserPage.View.UserSettingActivity
import com.nixo.afterworklife.MainPage.Data.User.InformationData
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

class UserSettingPresent : BasePresenter<UserSettingActivity>(){
    interface MineServce{


        @GET("api/user/my/information")
        fun getInformation(): Deferred<InformationData>

        @FormUrlEncoded
        @POST("api/user/edit/my/information")
        fun editInformation(
            @Field ("username") username:String,
            @Field("avatar")avatar:String,
            @Field("sex")sex:Int,
            @Field("birthday")birthday:String
        ): Deferred<LoginData>


    }

    object MineServceApi : MineServce by RetrofitClient.retrofit.create(
        MineServce::class.java)



    fun getInformation(){
        GlobalScope.launch(Dispatchers.Main) {
            var await = MineServceApi.getInformation().await()
            if(await.code != 200){
                ToastUtils.newToastCenter(view,await.msg)
            }else{
                view.initInformation(await.data)
            }
        }
    }

    fun editInformationName(username:String){
        GlobalScope.launch (Dispatchers.Main) {
            var await = MineServceApi.editInformation(
                username,
                SharedUtils.getString("avatar"),
                SharedUtils.getInt("sex"),
                SharedUtils.getString("birthday")
            ).await()
            if(await.code == 200){
                SharedUtils.putString("username",username)
                ToastUtils.newToastCenter(view,await.msg)
            }
        }
    }
    fun editInformationAvatar(avatar:String){
        GlobalScope.launch (Dispatchers.Main) {
            var await = MineServceApi.editInformation(
                SharedUtils.getString("username"),
                avatar,
                SharedUtils.getInt("sex"),
                SharedUtils.getString("birthday")
            ).await()
            if(await.code == 200){
                ToastUtils.newToastCenter(view,await.msg)
            }
        }
    }
    fun editInformationSex(sex:Int){
        GlobalScope.launch (Dispatchers.Main) {
            var await = MineServceApi.editInformation(
                SharedUtils.getString("username"),
                SharedUtils.getString("avatar"),
                sex,
                SharedUtils.getString("birthday")
            ).await()
            if(await.code == 200){
                SharedUtils.putInt("sex",sex)
                ToastUtils.newToastCenter(view,await.msg)
            }
        }
    }
    fun editInformationbirthday(birthday:String){
        GlobalScope.launch (Dispatchers.Main) {
            var await = MineServceApi.editInformation(
                SharedUtils.getString("username"),
                SharedUtils.getString("avatar"),
                SharedUtils.getInt("sex"),
                birthday
            ).await()
            if(await.code == 200){
                SharedUtils.putString("birthday",birthday)
                ToastUtils.newToastCenter(view,await.msg)
            }
        }
    }

}