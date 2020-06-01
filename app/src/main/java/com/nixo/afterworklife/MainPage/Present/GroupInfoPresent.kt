package com.nixo.afterworklife.MainPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.GroupPage.GroupManagerData.GroupUser.VisitorData
import com.nixo.afterworklife.GroupPage.GroupPresent.GroupManagerPresent
import com.nixo.afterworklife.MainPage.Acivity.GroupInfoActivity
import com.nixo.afterworklife.MainPage.Data.GroupInfo.GroupInfoData
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class GroupInfoPresent : BasePresenter<GroupInfoActivity>(){

    interface GroupInfServce {
        @GET("api/group/detail/{group_id}")
        fun onFindApi(
            @Path("group_id") group_id :Int
        ): Deferred<GroupInfoData>
        @FormUrlEncoded
        @POST("api/group/join/{group_id}")
        fun onJoin(
            @Path("group_id") group_id :Int,
            @Field("reason") reason : String
        ):Deferred<LoginData>


        @GET("api/group/is/administor/{group_id}")
        fun isAdmin(
            @Path("group_id")group_id :Int
        ):Deferred<VisitorData>

    }

    object GroupInfoServceApi : GroupInfServce by RetrofitClient.retrofit.create(
        GroupInfServce::class.java
    )
    fun onJoinS(id:Int,reason:String){
        GlobalScope.launch (Dispatchers.Main){
            var await = GroupInfoServceApi.onJoin(id, "").await()
            if(await.code == 200){
                view.onSuccessJoin()
            }
        }
    }


    fun getGroupInfo(id:Int){
        GlobalScope.launch (Dispatchers.Main){
            var await = GroupInfoServceApi.onFindApi(id).await()

            view.onSuccess(await)
        }
    }
    fun isAdmin(id: Int){
        GlobalScope.launch(Dispatchers.Main) {
            var await = GroupInfoPresent.GroupInfoServceApi.isAdmin(id).await()
            if(await.code == 200){
                view.checkPremisson(await.data.is_administor)
            }
        }
    }




}