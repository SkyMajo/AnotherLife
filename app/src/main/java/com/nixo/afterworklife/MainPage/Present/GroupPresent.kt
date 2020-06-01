package com.nixo.afterworklife.MainPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.GroupPage.GroupPresent.AllGroupPresent
import com.nixo.afterworklife.MainPage.Data.GroupData.LocationGroupData
import com.nixo.afterworklife.MainPage.Data.MyCreateGroupData
import com.nixo.afterworklife.MainPage.Fragement.GroupFragment
import com.nixo.afterworklife.MainPage.Fragement.LocationGroupFragment
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Query

class GroupPresent : BasePresenter<GroupFragment>() {



    interface MajorGroupServce{
        @GET("api/group/get/me/join")
        fun onGroupJoinReview():Deferred<MyCreateGroupData>
        @GET("api/group/get/near")
        fun onGroupLocation(
            @Query("lng") lng:String,
            @Query("lat") lat:String
        ):Deferred<LocationGroupData>

            @GET("api/group/get/all")
            fun onGroupAllLocation(
                @Query("page") page :Int
            ): Deferred<LocationGroupData>



    }
    object MajorGroupServceApi : MajorGroupServce by RetrofitClient.retrofit.create(MajorGroupServce::class.java)

    fun getGroupJoinData() {
        GlobalScope.launch(Dispatchers.Main) {
            var mJoinGroup = MajorGroupServceApi.onGroupAllLocation(0).await()
            if(mJoinGroup != null && mJoinGroup.code == 200 && mJoinGroup.data != null){
                view.onInitCreateList(mJoinGroup.data!!)
            }else{
                ToastUtils.newToastCenter(view.activity!!,"网络异常")
            }
            println("${mJoinGroup.data}")
        }
    }


    fun getLocationGroupData(lng:String,lat:String){
        GlobalScope.launch (Dispatchers.Main){
            var onGroupLocation = MajorGroupServceApi.onGroupLocation(lng, lat).await()
            if(onGroupLocation.code != 200){
                ToastUtils.newToastCenter(view.activity!!,"${onGroupLocation.msg}")
            }else{
                view.onLocationSuccess(onGroupLocation.data!!)

            }
        }
    }


}
