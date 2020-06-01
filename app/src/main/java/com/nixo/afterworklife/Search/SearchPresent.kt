package com.nixo.afterworklife.Search

import android.service.autofill.UserData
import com.nixo.afterworklife.ActivitePage.DATA.ActiviteListData
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.MainPage.Data.GroupData.LocationGroupData
import com.nixo.afterworklife.MainPage.Data.GroupInfo.GroupInfoData
import com.nixo.afterworklife.MainPage.Data.IdolData
import com.nixo.afterworklife.MainPage.Data.MyCreateGroupData
import com.nixo.afterworklife.UserPage.Moudle.DataItem
import com.nixo.afterworklife.MainPage.Present.FlowPresent
import com.nixo.afterworklife.Search.SearchData.UserData.SearchUserData
import com.nixo.afterworklife.UserPage.Adapter.IdolAdapter
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.DynamicData
import com.nixo.afterworklife.Welcome.Moudle.FlowDynamicsData
import com.nixo.afterworklife.Welcome.Moudle.LikesMoudle
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.http.*

class SearchPresent : BasePresenter<SearchActivity>() {
    interface SearchInfs {


        @GET("api/serach/activity")
        fun getActiviteSearch(
            @Query("keyword") keyword: String
        ): Deferred<ActiviteListData>

        @GET("api/serach/explores")
        fun getExploresSearch(
            @Query("keyword") keyword: String
        ): Deferred<FlowDynamicsData>

        @GET("api/serach/groups")
        fun getGroupsSearch(
            @Query("keyword") keyword: String
        ): Deferred<LocationGroupData>


        @GET("api/serach/users")
        fun getUsersSearch(
            @Query("keyword") keyword: String
        ): Deferred<SearchUserData>

        @POST("api/explore/like/{explore_id}")
        fun onClickGood(
            @Path("explore_id") explore_id: String
        ): Deferred<LoginData>

    }

    object SearchPresentObj : SearchInfs by RetrofitClient.retrofit.create(SearchInfs::class.java)


    fun onSearchActivite(keyword: String) {
        GlobalScope.launch(Dispatchers.Main) {
            var await = SearchPresentObj.getActiviteSearch(keyword).await()
            if (await.code == 200) {
                view.onSearchActiviteCallBack(await.data)
            } else {
                ToastUtils.newToastCenter(view, await.msg)
            }

        }
    }

    fun onSearchDynamic(keyword: String) {
        GlobalScope.launch(Dispatchers.Main) {
            var await = SearchPresentObj.getExploresSearch(keyword).await()
            if (await.code == 200) {
                view.onSearchDynamicCallBack(await.data)
            } else {
                ToastUtils.newToastCenter(view, await.msg)
            }

        }


    }

    fun onSearchGroup(keyword: String) {
        GlobalScope.launch(Dispatchers.Main) {
            var await = SearchPresentObj.getGroupsSearch(keyword).await()
            if (await.code == 200) {
                view.onSearchGroupCallBack(await.data!!)
            } else {
                ToastUtils.newToastCenter(view, await.msg)
            }

        }
    }
    fun onSearchUsers(keyword: String) {
        GlobalScope.launch(Dispatchers.Main) {
            var await = SearchPresentObj.getUsersSearch(keyword).await()
            if (await.code == 200) {
                view.onSearchUserCallBack(await.data!!)
            } else {
                ToastUtils.newToastCenter(view, await.msg)
            }

        }
    }

    fun onClickGood(exploreId: String) {
        GlobalScope.launch(Dispatchers.Main) {
            var await = FlowPresent.FlowApi.onClickGood(exploreId).await()
            if (await.code == 200) {
                ToastUtils.newToastCenter(view, await.msg ?: "点赞")
            } else {
                ToastUtils.newToastCenter(view, await.msg ?: "网络异常")
            }
        }

    }
}
