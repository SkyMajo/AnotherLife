package com.nixo.afterworklife.GroupPage.GroupPresent

import com.nixo.afterworklife.ActivitePage.DATA.ActiviteListData
import com.nixo.afterworklife.ActivitePage.DATA.GroupManagerData.EditActiviteListData
import com.nixo.afterworklife.ActivitePage.Presenter.ActivitePresenter
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.GroupPage.GroupFragment.GroupActiviteFragment
import com.nixo.afterworklife.GroupPage.GroupFragment.MainActiviteFragment
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Path

class MainActivitePresenter : BasePresenter<MainActiviteFragment>(){
    interface ActiviteApi{
        @GET("api/activity/list/{group_id}")
        fun getUserLikeTags(
            @Path("group_id") groupId:Int
        ): Deferred<EditActiviteListData>
        @GET("api/activity/list/")
        fun onActiviteList(): Deferred<ActiviteListData>
    }

    object FlowApi :
        ActiviteApi by RetrofitClient.retrofit.create(ActiviteApi::class.java)

    fun onGetList(){
        GlobalScope.launch (Dispatchers.Main){
            var onActiviteList = ActivitePresenter.FlowApi.onActiviteList().await()
            if(onActiviteList.code == 200)
            {
                view.onSuccess(onActiviteList.data)
            }
        }
    }
    fun getActiviteListForGroup(id:Int){
        GlobalScope.launch(Dispatchers.Main) {
            var await = FlowApi.onActiviteList().await()
            if(await.code == 200){
                view.onSuccess(await.data)
            }else{
                ToastUtils.newToastCenter(view.activity!!,await.msg)
            }
        }
    }


}