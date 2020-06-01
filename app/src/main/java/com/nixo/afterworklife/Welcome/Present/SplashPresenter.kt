package com.nixo.afterworklife.Welcome.Present

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.jerey.loglib.Klog
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import com.nixo.afterworklife.Welcome.View.Activity.SplashActivity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class SplashPresenter : BasePresenter<SplashActivity>(){
    interface informationSever {



        @FormUrlEncoded
        @PATCH("api/user/edit/information")
        fun setInformation(@Field("sex" ) sex: Int,
                    @Field("birthday")birthday:String,
                    @Field("hobby")hobby:String
        ):Deferred<LoginData>





        }
    object informationSeverApi : informationSever by RetrofitClient.retrofit.create(
        informationSever::class.java)

    fun  setInforMationFun(sex : Int,old : String ,informations:ArrayList<Int>){
        Klog.e("Nixo",informations)
        GlobalScope.launch(Dispatchers.Main) {
            var informationsBody =  ""

            if(informations.isNotEmpty()){
                informationsBody = Gson().toJson(informations)
            }
            var information = informationSeverApi.setInformation(sex, old, informationsBody).await()

            if(information.code == 200){
                view.onSetSuccess(information.msg)
            }else{
                ToastUtils.newToastCenter(view,information.msg)
            }
        }
    }
    }
