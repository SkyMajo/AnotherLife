package com.nixo.afterworklife.ActivitePage.Presenter

import com.nixo.afterworklife.ActivitePage.Activity.GroupChoseActivity
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.MainPage.Data.MyCreateGroupData
import com.nixo.afterworklife.MainPage.Present.GroupPresent
import com.nixo.afterworklife.MainPage.Present.MyGroupPresent
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET

class GroupChosePresent : BasePresenter<GroupChoseActivity>(){

    interface MyGroupServce {

        @GET("api/group/get/me/join")
        fun onGroupJoinReview(): Deferred<MyCreateGroupData>

    }
    object myChoseGroupApi : MyGroupServce by RetrofitClient.retrofit.create(MyGroupServce::class.java)
    fun getGroupJoinData() {
        GlobalScope.launch(Dispatchers.Main) {
            var mJoinGroup = myChoseGroupApi.onGroupJoinReview().await()
            var mCraeteGroup = MyGroupPresent.myGroupApi.onGetMyGroupList().await()
            if(mCraeteGroup.code == 200 && mJoinGroup != null && mJoinGroup.code == 200 && mJoinGroup.data != null){
                 mJoinGroup.data.addAll(mCraeteGroup.data)
                view.onInitJoinList(mJoinGroup.data)
            }else{
                ToastUtils.newToastCenter(view,"网络异常")
            }
            println("${mJoinGroup.data}")
        }
    }
}