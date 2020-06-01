package com.nixo.afterworklife.MainPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.MainPage.Fragement.FindFragment
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.FlowDynamicsData
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class FindPresent : BasePresenter<FindFragment>() {
    interface FindServce {
        @GET("api/explore/list/paginate")
        fun onFindApi(
            @Query("page")page:Int
        ): Deferred<FlowDynamicsData>

        @POST("api/explore/like/{explore_id}")
        fun onClickGood(
            @Path("explore_id") explore_id: String
        ): Deferred<LoginData>

    }

    object FindServceApi : FindServce by RetrofitClient.retrofit.create(
        FindServce::class.java
    )


    fun getFindList(page: Int) {

        GlobalScope.launch(Dispatchers.Main) {
            var await = FindServceApi.onFindApi(page).await()
            if (await.code == 200) {
                view.onFindListSuccess(await.data)
            }
        }
    }

    fun onClickGood(exploreId: String) {
        GlobalScope.launch(Dispatchers.Main) {
            var await = FlowPresent.FlowApi.onClickGood(exploreId).await()
            if (await.code == 200) {
                ToastUtils.newToastCenter(view.activity!!, await.msg ?: "点赞")
            } else {
                ToastUtils.newToastCenter(view.activity!!, await.msg ?: "网络异常")
            }
        }

    }
}
