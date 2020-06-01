package com.nixo.afterworklife.ActivitePage.Activity

import android.os.Bundle
import android.text.InputType
import com.bumptech.glide.Glide
import com.jerey.loglib.Klog
import com.nixo.afterworklife.ActivitePage.DATA.AInfoData.Data
import com.nixo.afterworklife.ActivitePage.Presenter.ActiviteInfoPresent
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.R
import com.nixo.afterworklife.UserPage.PublicUserActivity
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import kotlinx.android.synthetic.main.activity_activite_info.*
import kotlinx.android.synthetic.main.include_titlebar.*

class ActiviteInfoActivity : BaseActivity<ActiviteInfoPresent>() {
    override fun onLayout(): Int = R.layout.activity_activite_info

    var index = 1


    override fun onState() {
        ll_title.click { finish() }
        tv_title.text = "活动详情"
        if(SharedUtils.getString("token").isEmpty()){
            iv_start.click {
                Action(LoginActivity::class.java)
            }
        }else{
            presenter.getInfoData(intent.getIntExtra("id", 1))
            Klog.e("Nixo", intent.getIntExtra("id", 1))
        }
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}

    fun onSuccess(data: Data) {
        if(data.info != null){
            et_name.setText(data.info.realname)
            et_phone.setText(data.info.phone)
        }
        tv_name.text = data.title
        tv_time.text = data.startTime ?: "2019-11-29 15:30:44"
        tv_group_name.text = "#" + data.group.name
        tv_desc.text = data.content
        tv_desc_ac.text = data.description
        tv_time_in.text = data.startTime ?: "2019-11-29 15:30:54"
        tv_local_in.text = data.address
        tv_person_in.text = data.members.toString() + "人"
        tv_name_user.text = data.user.username
        Glide.with(this).load(data.user.avatar).into(civ_head)
        ll_user.click {

            var bundle = Bundle()
            bundle.putInt("userId",data.user.id)
            paramerAction(PublicUserActivity::class.java,bundle)
        }
        if (data.is_signup) {
            index = 0
            et_name.inputType =  InputType.TYPE_NULL
            et_phone.inputType = InputType.TYPE_NULL
            Glide.with(this).load(R.mipmap.cancel_start).into(iv_start)
        } else {
            index = 1

        }
        iv_start.click {
            if (index == 1) {
                if (et_name.text.isEmpty()) {
                    ToastUtils.newToastCenter(this, "请填写姓名")

                } else {
                    if (et_phone.text.isEmpty()) {
                        ToastUtils.newToastCenter(this, "请填写联系人电话")
                    } else {
                        presenter.onStart(
                            data.id,
                            et_phone.text.toString(),
                            et_name.text.toString()
                        )
                    }
                }
            } else {
                presenter.onCalcen(data.id)
            }


        }
    }

    fun OnJoinSuccess() {
        ToastUtils.newToastCenter(this, "加入成功")
        et_name.inputType =  InputType.TYPE_NULL
        et_phone.inputType = InputType.TYPE_NULL
        Glide.with(this).load(R.mipmap.cancel_start).into(iv_start)
        index = 0
    }

    fun onCancelSuccess() {
        ToastUtils.newToastCenter(this, "取消成功")
        et_name.inputType =  InputType.TYPE_CLASS_TEXT
        et_phone.inputType = InputType.TYPE_CLASS_TEXT
        Glide.with(this).load(R.mipmap.want_join).into(iv_start)
        index = 1
    }
}



