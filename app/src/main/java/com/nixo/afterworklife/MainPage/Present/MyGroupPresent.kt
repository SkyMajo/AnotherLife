package com.nixo.afterworklife.MainPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.MainPage.Data.MyCreateGroupData
import com.nixo.afterworklife.MainPage.Fragement.MyGroupFragment
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class MyGroupPresent : BasePresenter<MyGroupFragment>(){
//我创建的社团
//api/group/get/me/create

    interface MyGroupServce {
        @GET("api/group/get/me/create")
        fun onGetMyGroupList(
        ): Deferred<MyCreateGroupData>
        @GET("api/group/get/me/join")
        fun onGroupJoinReview():Deferred<MyCreateGroupData>



    }
    object myGroupApi : MyGroupServce by RetrofitClient.retrofit.create(MyGroupServce::class.java)




    fun onGetMyCreateGroup() {
        GlobalScope.launch(Dispatchers.Main) {
            var mCraeteGroup = myGroupApi.onGetMyGroupList().await()
            if(mCraeteGroup != null && mCraeteGroup.code == 200 && mCraeteGroup.data != null){
//               view.onInitCreateList(mCraeteGroup.data)
            }else{
                ToastUtils.newToastCenter(view.activity!!,"网络异常")
            }
//            println("${mCraeteGroup.data}")
        }
    }
    fun getGroupJoinData() {
        GlobalScope.launch(Dispatchers.Main) {
            var mJoinGroup = GroupPresent.MajorGroupServceApi.onGroupJoinReview().await()
            var mCraeteGroup = myGroupApi.onGetMyGroupList().await()
            if(mCraeteGroup.code == 200 && mJoinGroup != null && mJoinGroup.code == 200 && mJoinGroup.data != null){
                mJoinGroup.data.addAll(mCraeteGroup.data)
                view.onInitJoinList(mJoinGroup.data)
            }else{
                ToastUtils.newToastCenter(view.activity!!,"网络异常")
            }
//            println("${mJoinGroup.data}")
        }
    }

    fun getIsMsg(){
        GlobalScope.launch (Dispatchers.Main){
            var await = MajorPresent.MajorP.onInfoNum().await()
            view.MsgCall( await.msg)
        }
    }




}