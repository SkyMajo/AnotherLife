package com.nixo.afterworklife.GroupPage.GroupPresent

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.GroupPage.GroupActivity.CreateGroupActivity
import com.nixo.afterworklife.MainPage.Present.GroupInfoPresent
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class CreateGroupPresent : BasePresenter<CreateGroupActivity>(){

    interface CreateGroupInfs {

        @FormUrlEncoded
        @POST("api/group/create")
        fun onCreateGroup(
            @Field("name") name: String,
            @Field("description") description: String,
            @Field("lng") lng: String,
            @Field("lat") lat: String,
            @Field("avatar") avatar: String,
            @Field("logo") logo: String,
            @Field("max_member") max_member: String
//            @Field("username") username: String
        ): Deferred<LoginData>
        @FormUrlEncoded
        @PATCH("api/group/edit/info/{group_id}")
        fun onEditGroup(
            @Path("group_id")group_id:Int,
            @Field("name") name: String,
            @Field("description") description: String,
            @Field("lng") lng: String,
            @Field("lat") lat: String,
            @Field("avatar") avatar: String,
            @Field("logo") logo: String,
            @Field("max_member") max_member: String
//            @Field("username") username: String
        ): Deferred<LoginData>


    }
    object createGroupServce : CreateGroupInfs by RetrofitClient.retrofit.create(CreateGroupInfs::class.java)

    fun onCreate(username: String, description: String,lng: String, lat: String,avatar: String,logo: String, max_member: String){
        GlobalScope.launch (Dispatchers.Main){
            var await = createGroupServce.onCreateGroup(
                username,
                description,
                lng,
                lat,
                avatar,
                logo,
                max_member
//              SharedUtils.getString("username")
            ).await()
            if(await.code == 200){
                view.onSuccess()
            }else{
                view.onFail()
                ToastUtils.newToastCenter(view,await.msg)
            }
        }

    }
    fun getGroupInfo(id:Int){
        GlobalScope.launch (Dispatchers.Main){
            var await = GroupInfoPresent.GroupInfoServceApi.onFindApi(id).await()

            view.onSuccessInfo(await)
        }
    }
    fun onEdit(id: Int,username: String, description: String,lng: String, lat: String,avatar: String,logo: String, max_member: String){
        GlobalScope.launch (Dispatchers.Main){
            var await = createGroupServce.onEditGroup(
                id,
                username,
                description,
                lng,
                lat,
                avatar,
                logo,
                max_member
//              SharedUtils.getString("username")
            ).await()
            if(await.code == 200){
                view.onEditSussece()
            }else{
                view.onFail()
                ToastUtils.newToastCenter(view,await.msg)
            }
        }

    }


}