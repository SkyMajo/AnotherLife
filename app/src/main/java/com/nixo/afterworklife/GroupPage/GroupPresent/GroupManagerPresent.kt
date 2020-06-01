package com.nixo.afterworklife.GroupPage.GroupPresent

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.GroupPage.GroupActivity.GroupManagerActivity
import com.nixo.afterworklife.GroupPage.GroupManagerData.GroupUser.GroupUser
import com.nixo.afterworklife.MainPage.Present.GroupInfoPresent
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Welcome.Moudle.LikesMoudle
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class GroupManagerPresent : BasePresenter<GroupManagerActivity>(){

    interface GroupManagerInfs{
        @GET("api/group/get/members/{group}")
        fun getUserLikeTags(
            @Path("group") groupId:Int
        ): Deferred<GroupUser>

        @DELETE("api/group/quit/{group_id}")
        fun onDeleteGroup(
            @Path("group_id") groupId:Int
        ): Deferred<GroupUser>
        @FormUrlEncoded
        @PATCH("api/group/edit/announcement/{group_id}")
        fun onAnnouncementGroup(
            @Path("group_id") groupId:Int,
            @Field("announcement")announcement:String
        ): Deferred<GroupUser>
    }
    object GroupManagerServce : GroupManagerInfs by RetrofitClient.retrofit.create(GroupManagerInfs::class.java)

  fun getGroupUser(groupId: Int){
      GlobalScope.launch(Dispatchers.Main) {
          var await = GroupManagerServce.getUserLikeTags(groupId).await()
          if(await.code == 200){
              view.UserSuccess(await.data)
          }
      }
  }
    fun onJoinS(id:Int,reason:String){
        GlobalScope.launch (Dispatchers.Main){
            var await = GroupInfoPresent.GroupInfoServceApi.onJoin(id, "").await()
            if(await.code == 200){
                view.onSuccessJoin()
            }
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

    fun deleteGroup(id: Int){
        GlobalScope.launch(Dispatchers.Main) {
            var await = GroupManagerPresent.GroupManagerServce.onDeleteGroup(id).await()
            if(await.code == 200){
                view.deleteSuccess()
            }
        }
    }

    fun onAnnouncementGroups(id: Int,annoum:String){
        GlobalScope.launch (Dispatchers.Main){
            var await = GroupManagerPresent.GroupManagerServce.onAnnouncementGroup(id,annoum).await()
            if(await.code == 200){
                view.onAnnoumceSuccess()
            }
        }
    }


}