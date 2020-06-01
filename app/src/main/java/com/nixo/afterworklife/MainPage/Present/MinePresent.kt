package com.nixo.afterworklife.MainPage.Present

import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.MainPage.Data.InfoNumMoudle
import com.nixo.afterworklife.MainPage.Data.User.InformationData
import com.nixo.afterworklife.MainPage.Fragement.MineFragment
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.NetWork.RetrofitClient
import com.nixo.afterworklife.Utils.ToastUtils
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET

class MinePresent : BasePresenter<MineFragment>(){

    interface MineServce{
        @GET("api/user/my/info/num")
        fun onInfoNum(
        ): Deferred<InfoNumMoudle>

        @GET("api/user/my/information")
        fun getInformation():Deferred<InformationData>


    }

    object MineServceApi : MineServce by RetrofitClient.retrofit.create(MineServce::class.java)

    fun getInfoNum(){
        GlobalScope.launch(Dispatchers.Main) {
            var mMineNumInfo = MineServceApi.onInfoNum().await()
            if(mMineNumInfo.code == 200){
                view.setInfoNum(mMineNumInfo.data)
            }
        }
    }

    fun onGetMyCreateGroup() {
        GlobalScope.launch(Dispatchers.Main) {
            var mCraeteGroup = MyGroupPresent.myGroupApi.onGetMyGroupList().await()
            if(mCraeteGroup != null && mCraeteGroup.code == 200 && mCraeteGroup.data != null){
               view.onInitCreateList(mCraeteGroup)
            }else{
                ToastUtils.newToastCenter(view.activity!!,"网络异常")
            }
//            println("${mCraeteGroup.data}")
        }
    }


    fun getInformation(){
       GlobalScope.launch(Dispatchers.Main) {
           var await = MineServceApi.getInformation().await()
           if(await.code != 200){

               ToastUtils.newToastCenter(view.activity!!,await.msg)
           }else{
               SharedUtils.putString("username",await.data.username)
               SharedUtils.putString("avatar",await.data.avatar)
               SharedUtils.putInt("sex",await.data.sex)
               SharedUtils.putString("birthday",await.data.birthday)
               view.initInformation(await.data)
           }
       }
    }


}