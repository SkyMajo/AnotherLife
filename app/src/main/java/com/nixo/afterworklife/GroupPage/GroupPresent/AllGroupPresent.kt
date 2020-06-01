package com.nixo.afterworklife.GroupPage.GroupPresent

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.GroupPage.GroupActivity.AllGroupActivity
import com.nixo.afterworklife.MainPage.Data.GroupData.LocationGroupData
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Query

class AllGroupPresent :BasePresenter<AllGroupActivity>(){

    interface AllGroupInfs{
        @GET("api/group/get/all")
        fun onGroupLocation(
            @Query("page") page :Int
        ): Deferred<LocationGroupData>
        object AllGroupServceApi : AllGroupInfs by RetrofitClient.retrofit.create(
            AllGroupInfs::class.java)
    }

    fun onGetAllGroup(){
        GlobalScope.launch (Dispatchers.Main){
            var await = AllGroupInfs.AllGroupServceApi.onGroupLocation(0).await()
            if(await.code == 200){
                view.initAllGroup(await.data!!)
            }
        }
    }




}