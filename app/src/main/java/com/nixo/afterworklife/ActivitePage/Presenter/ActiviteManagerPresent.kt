package com.nixo.afterworklife.ActivitePage.Presenter

import android.view.View
import com.nixo.afterworklife.ActivitePage.Activity.ActiviteManagerActivity
import com.nixo.afterworklife.ActivitePage.DATA.GroupManagerData.EditActiviteListData
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class ActiviteManagerPresent :BasePresenter<ActiviteManagerActivity>(){
//    http://app.iyaa180.com/api/activity/list/9
interface ActiviteManagerInfs{
    @GET("api/activity/list/{group_id}")
    fun getUserLikeTags(
        @Path("group_id") groupId:Int
    ): Deferred<EditActiviteListData>

    @POST("api/activity/cancel/signup/{active_id}")
    fun getSignUp(
        @Path("active_id") active_id:Int
    ): Deferred<LoginData>


}
    object ActiviteManagerServce : ActiviteManagerInfs by RetrofitClient.retrofit.create(ActiviteManagerInfs::class.java)

    fun getActiviteListForGroup(id:Int){
        GlobalScope.launch(Dispatchers.Main) {
            var await = ActiviteManagerServce.getUserLikeTags(id).await()
            if(await.code == 200){
                view.getSuccess(await.data)
            }else{
                ToastUtils.newToastCenter(view,await.msg)
            }
        }
    }

    fun getSignUp(id: Int){
        GlobalScope.launch (Dispatchers.Main){
            var signUp = ActiviteManagerServce.getSignUp(id).await()
            if(signUp.code == 200){
                view.RefushData()
            }
        }
    }

}