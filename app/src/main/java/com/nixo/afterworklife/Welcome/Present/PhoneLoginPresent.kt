package com.nixo.afterworklife.Welcome.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.otherwise
import com.nixo.afterworklife.Utils.Ext.yes
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LikesMoudle
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import com.nixo.afterworklife.Welcome.View.Activity.PhoneLoginActivity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

class PhoneLoginPresent : BasePresenter<PhoneLoginActivity>() {

    //The Dream of white Star

    interface PhoneVSCodeServce {
        @FormUrlEncoded
        @POST("api/user/register/verifycode")
        fun onVsCode(
            @Field("phone") phone: String
        ): Deferred<LoginData>

        @FormUrlEncoded
        @POST("api/user/phone/login")
        fun onPhoneLogin(
            @Field("phone") phone: String,
            @Field("verify_code") verify_code: String
        ): Deferred<LoginData>

        @GET("api/tag/user/like")
        fun getUserLikeTags(): Deferred<LikesMoudle>

    }

    object LoginServce : PhoneVSCodeServce by RetrofitClient.retrofit.create(
        PhoneVSCodeServce::class.java
    )


    fun onVsCode(phone: String) {
        GlobalScope.launch(Dispatchers.Main) {
            var await = LoginServce.onVsCode(phone).await()
            if (await.code == 200) {
                ToastUtils.newToastCenter(view, await.msg)
            } else {
                view.onCutDownTimeFucation()
            }
        }
    }


    fun onPhoneLogin(phone: String, vsCode: String) {
        GlobalScope.launch(Dispatchers.Main) {
            var await = LoginServce.onPhoneLogin(phone, vsCode).await()
            if (await.code == 599) {
                ToastUtils.newToastCenter(view, await.msg)
            } else {
                SharedUtils.putString("token", await.data.token ?: "")
                SharedUtils.putInt("userId",await.data.info.id)
                var likesAwait = LoginPresent.LoginServce.getUserLikeTags().await()

                if (likesAwait.code == 200) {
                    likesAwait.data!!.isNotEmpty().yes {
                        view.onLoginSuccess()
                    }.otherwise {
                        view.onSplashSuccess()
                    }
                }
            }
        }

    }
}