package com.nixo.afterworklife.MainPage.Fragement

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.GroupPage.MyGroupListActivity
import com.nixo.afterworklife.UserPage.View.UserSettingActivity
import com.nixo.afterworklife.MainPage.Data.InfoNumData
import com.nixo.afterworklife.MainPage.Data.MyCreateGroupData
import com.nixo.afterworklife.MainPage.Data.User.Data
import com.nixo.afterworklife.MainPage.Present.MinePresent
import com.nixo.afterworklife.R
import com.nixo.afterworklife.UserPage.PublicUserActivity
import com.nixo.afterworklife.UserPage.View.FansActivity
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment :BaseFragment<MinePresent>(){


    override fun setLayoutParame(): Int = R.layout.fragment_mine

    override fun onState() {

        if(SharedUtils.getString("token").isEmpty()){
            tv_info.visibility = View.VISIBLE
            ll_setting.click {
                Action(LoginActivity::class.java)
            }
            ll_group.visibility = View.GONE
            ll_unlogin.visibility = View.VISIBLE
            iv_setting.click {
                Action(LoginActivity::class.java)
            }
            cv_group.click {
                Action(LoginActivity::class.java)
            }
        }else{
            ll_group.visibility = View.VISIBLE
            ll_unlogin.visibility = View.GONE
            AlertUtils.showProgress(true,activity!!)
            iv_setting.click {
                Action(UserSettingActivity::class.java)
            }
            presenter.getInfoNum()
            presenter.onGetMyCreateGroup()
        }


    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onResume() {
        super.onResume()
        if(SharedUtils.getString("token").isEmpty()){

        }else{
            presenter.getInformation()
        }

    }

    fun setInfoNum(data: InfoNumData) {
        if(tv_idol_num == null ||tv_fans_num == null){

        }else{
            tv_idol_num!!.text = data.follows.toString()?:""
            tv_fans_num!!.text = data.fans.toString()?:""
//            tv_dynamic_num!!.text = data.explores.toString()?:""
            ll_fans.click {
                var bundle = Bundle()
                bundle.putString("startType","1")
                paramerAction(FansActivity::class.java,bundle)
            }
            ll_idol.click {
                var bundle = Bundle()
                bundle.putString("startType","2")
                paramerAction(FansActivity::class.java,bundle)
            }
        }

        AlertUtils.dismissProgress()
    }

    override fun onDestory() {
    }

    fun initInformation(data: Data) {
        if(civ_head == null || tv_name == null){

        }else{
            SharedUtils.putString("avatar",data.avatar?:"")
            Glide.with(activity!!).load(data.avatar?:"").into(civ_head)
            tv_name.text = data.username?:""
            ll_setting.click {
                var bundle = Bundle()
                bundle.putInt("userId",data.id)
                bundle.putString("startType","me")
                paramerAction(PublicUserActivity::class.java,bundle)
            }
        }

        AlertUtils.dismissProgress()
    }

    fun onInitCreateList(data: MyCreateGroupData) {
        if(data.data.size == 1){
            tv_group1.text = data.data[0].name
            Glide.with(this).load(data.data[0].avatar).into(iv_group1)
            tv_group2.visibility = View.GONE
            iv_group2.visibility = View.GONE
        }else if(data.data.size == 2){
            tv_group1.text = data.data[0].name
            Glide.with(this).load(data.data[0].avatar).into(iv_group1)
            tv_group2.text = data.data[1].name
            Glide.with(this).load(data.data[1].avatar).into(iv_group2)
        }else if(data.data.size == 0){
            tv_group1.visibility = View.GONE
            tv_group2.visibility = View.GONE
            iv_group1.visibility = View.GONE
            iv_group2.visibility = View.GONE
        }

        ll_group.click {
            var toJson = Gson().toJson(data)
            var bundle = Bundle()
            bundle.putString("json",toJson)
            paramerAction(MyGroupListActivity::class.java,bundle)
        }
    }

}