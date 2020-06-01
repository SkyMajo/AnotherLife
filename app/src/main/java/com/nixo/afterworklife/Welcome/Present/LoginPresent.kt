package com.nixo.afterworklife.Welcome.Present

import com.jerey.loglib.Klog
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.otherwise
import com.nixo.afterworklife.Utils.Ext.yes
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LikesMoudle
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class LoginPresent :BasePresenter<LoginActivity>(){

    interface UserServce {
        @FormUrlEncoded
        @POST("api/user/password/login")
        fun onLoginApi(
            @Field("username") username : String,
            @Field("password") password : String
        ): Deferred<LoginData>

        @FormUrlEncoded
        @POST("api/user/password/register")
        fun onLoginApi(
            @Field("username") username: String,
            @Field("password") password: String,
            @Field("password_again") password_again: String
        ): Deferred<LoginData>

        @GET("api/tag/user/like")
        fun getUserLikeTags():Deferred<LikesMoudle>

    }
    object LoginServce : UserServce by RetrofitClient.retrofit.create(UserServce::class.java)

    fun onLogin(account : String , password:String){
        AlertUtils.dismissProgress()
        GlobalScope.launch(Dispatchers.Main){
            var onLoginApi = LoginServce.onLoginApi(account, password).await()
            Klog.e("debug_login",onLoginApi.toString())
            if(onLoginApi.code != 200){
                ToastUtils.newToastCenter(view,onLoginApi.msg?:"网络异常")

            }else{
                SharedUtils.putInt("userId",onLoginApi.data.info.id)
                SharedUtils.putString("username",onLoginApi.data.info.username)
                Klog.e("isToken",onLoginApi.data.token)
                SharedUtils.putString("token",onLoginApi.data.token?:"")
                var likesAwait = LoginServce.getUserLikeTags().await()
                if(likesAwait.code == 200){
                    likesAwait.data!!.isNotEmpty().yes {
                        view.onLoginSusscess()

                    }.otherwise {
                        view.onSplashSuccess()
                    }
                }

            }

        }

    }

    fun onVsCode(phone: String) {
        GlobalScope.launch(Dispatchers.Main) {
            var await = PhoneLoginPresent.LoginServce.onVsCode(phone).await()
            if (await.code == 200) {
                ToastUtils.newToastCenter(view, await.msg)
            } else {
                view.onCutDownTimeFucation()
            }
        }
    }

    fun onRegister(account: String, password: String,password_again:String) {
        AlertUtils.dismissProgress()
        GlobalScope.launch(Dispatchers.Main) {
            var onLoginApi = LoginServce.onLoginApi(account, password,password_again).await()
            Klog.e("debug_login", onLoginApi.toString())
            if (onLoginApi.code != 200) {
                ToastUtils.newToastCenter(view, onLoginApi.msg ?: "网络异常")
            } else {
                onLogin(account,password)
                var likesAwait = LoginServce.getUserLikeTags().await()
                if(likesAwait.code == 200){
                    likesAwait.data!!.isNotEmpty().yes {
                        view.onLoginSusscess()
                    }.otherwise {
                        view.onSplashSuccess()
                    }
                }
            }

        }
    }

    fun onPhoneLogin(phone: String, vsCode: String) {
        GlobalScope.launch(Dispatchers.Main) {
            var await = PhoneLoginPresent.LoginServce.onPhoneLogin(phone, vsCode).await()
            if (await.code == 599) {
                ToastUtils.newToastCenter(view, await.msg)
            } else {
                SharedUtils.putString("token", await.data.token ?: "")
                SharedUtils.putInt("userId",await.data.info.id)
                var likesAwait = LoginPresent.LoginServce.getUserLikeTags().await()

                if (likesAwait.code == 200) {
                    likesAwait.data!!.isNotEmpty().yes {
                        view.onLoginSusscess()
                    }.otherwise {
                        view.onSplashSuccess()
                    }
                }
            }
        }

    }

}