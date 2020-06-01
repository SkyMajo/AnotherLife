package com.nixo.afterworklife.UserPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.Common.FileUpdataData
import com.nixo.afterworklife.UserPage.View.UserHeadReviewActivity
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.http.*

class UserHeadReviewPresnet : BasePresenter<UserHeadReviewActivity>() {


    interface FileUpDataInterface {
//        @Multipart
        @POST("api/files/upload")
        fun onUpdataImg(
            @Body multipartBody: MultipartBody
        ): Deferred<FileUpdataData>
        @FormUrlEncoded
        @POST("api/user/edit/my/information")
        fun editInformation(
            @Field ("username") username:String,
            @Field("avatar")avatar:String,
            @Field("sex")sex:Int,
            @Field("birthday")birthday:String
        ): Deferred<LoginData>

    }

    object onUpdataServce :
        FileUpDataInterface by RetrofitClient.retrofit.create(FileUpDataInterface::class.java)


    fun upDataHead(headImg:String)
    {
        GlobalScope.launch(Dispatchers.Main) {
            var await = onUpdataServce.editInformation(
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