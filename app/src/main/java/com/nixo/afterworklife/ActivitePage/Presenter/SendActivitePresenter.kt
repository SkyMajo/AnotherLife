package com.nixo.afterworklife.ActivitePage.Presenter

import android.util.Log
import com.google.gson.Gson
import com.nixo.afterworklife.ActivitePage.Activity.SendActivieActivity
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Moudle.LoginData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.*

class SendActivitePresenter :BasePresenter<SendActivieActivity>(){

    interface SendActiviteApi {
        @FormUrlEncoded
        @POST("api/explore/release")
        fun onSend(
//            @Body requestBody: RequestBody,
            @Field("images") images :String,
            @Field("content") content : String,
            @Field("video") video : String,
            @Field("relation_group_id") relation_group_id : String,
            @Field("address") address : String
        ): Deferred<LoginData>

    }
    object ActiviteServce :SendActiviteApi by RetrofitClient.retrofit.create(SendActiviteApi::class.java)

    fun onSendRequest(images : ArrayList<String>, content : String,
                      video : String,relation_group_id : String, address : String){
        GlobalScope.launch (Dispatchers.Main){
//            var map = HashMap<String , Any>()
//            map.put("images",images)
//            map.put("content",content)
//            map.put("video",video)
//            map.put("relation_group_id",relation_group_id)
//            map.put("address",address)
//            var obj = Gson().toJson(map)
//            val body =
//                RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj)
            var body = ""
            if(images.size != 0){
                body = Gson().toJson(images)
            }
            Log.e("NixoLocation",SharedUtils.getString("address")?:"")

            var result =
                ActiviteServce.onSend(body,content,video,relation_group_id,"31.025808460214524,121.43693436596678"?:"121.43693436596678,31.025808460214524")
                    .await()

            if (result.code == 200){
                view.onSuccess()
            }else{
                ToastUtils.newToastCenter(view,result.msg)
            }
        }

    }


}