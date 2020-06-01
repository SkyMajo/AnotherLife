package com.nixo.afterworklife.DynamicsInfo.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.DynamicsInfo.Fragment.DynamicsInfoReplaysFragment
import com.nixo.afterworklife.MainPage.Data.DynamicsInfoData
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class DynamicsInfoReplayPresent :BasePresenter<DynamicsInfoReplaysFragment>(){
    interface DynamicsInfoServce {
        @GET("api/explore/info/{explore_id}")
        fun onDynamicsInfo(
            @Path("explore_id") explore_id : String): Deferred<DynamicsInfoData>
    }
    object DynamicsInfoServceApi : DynamicsInfoServce by RetrofitClient.retrofit.create(
        DynamicsInfoServce::class.java)

    interface DynamicsDataInfs{
       fun  onDynamicsInfoData(data :DynamicsInfoData)
    }

    var infs : DynamicsDataInfs? = null

    fun setDynamicsInfs(infs: DynamicsDataInfs){
        this.infs = infs
    }

    fun getDynamicsInfo(id:Int){
        GlobalScope.launch(Dispatchers.Main) {
            var data = DynamicsInfoServceApi.onDynamicsInfo(id.toString()).await()
            if(data.code == 200 && data.data != null){
                view.onReplayDataSuccess(data.data.commentPeoples)
            }
        }
    }


}