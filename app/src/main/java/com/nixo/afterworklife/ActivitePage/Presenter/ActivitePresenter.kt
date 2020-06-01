package com.nixo.afterworklife.ActivitePage.Presenter

import com.nixo.afterworklife.ActivitePage.DATA.ActiviteListData
import com.nixo.afterworklife.ActivitePage.Fragment.ActiviteFragment
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.MainPage.Present.MajorPresent
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET

class ActivitePresenter : BasePresenter<ActiviteFragment>(){
    interface ActiviteApi{

        @GET("api/activity/list/")
        fun onActiviteList(): Deferred<ActiviteListData>
    }

    object FlowApi :
        ActiviteApi by RetrofitClient.retrofit.create(ActiviteApi::class.java)

    fun onGetList(){
        GlobalScope.launch (Dispatchers.Main){
            var onActiviteList = FlowApi.onActiviteList().await()
            if(onActiviteList.code == 200)
            {
                view.onSuccess(onActiviteList.data)
            }
        }
    }
    fun getIsMsg(){
        GlobalScope.launch (Dispatchers.Main){
            var await = MajorPresent.MajorP.onInfoNum().await()
            view.MsgCall( await.msg)
        }
    }

}