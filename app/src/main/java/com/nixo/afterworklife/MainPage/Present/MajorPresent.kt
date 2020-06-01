package com.nixo.afterworklife.MainPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.MainPage.Fragement.MajorFragment
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET

class MajorPresent :BasePresenter<MajorFragment>(){

    interface MajorS{
        @GET("api/message/have/new/message")
        fun onInfoNum(
        ): Deferred<LoginData>




    }

    object MajorP : MajorS by RetrofitClient.retrofit.create(MajorS::class.java)


    fun getIsMsg(){
        GlobalScope.launch (Dispatchers.Main){
            var await = MajorP.onInfoNum().await()
            view.MsgCall( await.msg)
        }
    }

}