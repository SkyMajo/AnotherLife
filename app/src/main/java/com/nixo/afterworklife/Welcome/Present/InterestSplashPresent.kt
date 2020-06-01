package com.nixo.afterworklife.Welcome.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Welcome.Moudle.InteresBean
import com.nixo.afterworklife.Welcome.View.Fragment.InterestSplashFragment
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Query

class InterestSplashPresent :BasePresenter<InterestSplashFragment>() {

    interface InterestSplashServe {
        @GET("api/tags")
        fun onInteresSplashApi(
            @Query("page") page: Int
        ): Deferred<InteresBean>

    }

    object InterestSplashServeApi :
        InterestSplashServe by RetrofitClient.retrofit.create(InterestSplashServe::class.java)

    fun onGetInterestTag() {
        GlobalScope.launch(Dispatchers.Main) {
            var await = InterestSplashServeApi.onInteresSplashApi(1).await().data
            await.data!!.addAll(InterestSplashServeApi.onInteresSplashApi(2).await().data.data!!)
            view onDataSuccess(await.data!!)
        }


    }
}