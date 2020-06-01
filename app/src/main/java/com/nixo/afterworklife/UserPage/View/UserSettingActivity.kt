package com.nixo.afterworklife.UserPage.View

import android.os.Bundle
import android.view.View
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bumptech.glide.Glide
import com.gyf.immersionbar.ImmersionBar
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.MainPage.Data.User.Data
import com.nixo.afterworklife.R
import com.nixo.afterworklife.UserPage.Present.UserSettingPresent
import com.nixo.afterworklife.Utils.ConfirmDialog
import com.nixo.afterworklife.Utils.ConfirmExitDialog
import com.nixo.afterworklife.Utils.ConfirmOldDialog
import com.nixo.afterworklife.Utils.ConfirmsexDialog
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.include_titlebar.*
import java.text.SimpleDateFormat
import java.util.*


class UserSettingActivity : BaseActivity<UserSettingPresent>() ,
    ConfirmDialog.ConfirmOnClickListener
    ,ConfirmsexDialog.ConfirmSexOnClickListener,
    ConfirmOldDialog.ConfirmOldOnClickListener,ConfirmExitDialog.ConfirmExitOnClickListener
{
    override fun confirmExit(string: String) {
            SharedUtils.delete()
            Action(LoginActivity::class.java)
            finish()
    }

    override fun data(data: String) {

    }

    override fun confirm(string: Int) {
        tv_sex.text = if(string == 1){"男"}else {"女"}
        presenter.editInformationSex(string)
    }

    override fun confirm(string: String) {
        tv_name.text = string
        presenter.editInformationName(string)
    }

    override fun onState() {
        initStuatsBar()
        tv_exit.click {
            ConfirmExitDialog(this).setConfirExitmOnClickListener(this).show()
        }
        ll_title.click {
            finish()
        }

    }
    private fun initStuatsBar() {
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()
    }
    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }


    override fun onResume() {
        super.onResume()
        if(  SharedUtils.getString("username").isEmpty()||
            SharedUtils.getString("avatars").isEmpty()||
            SharedUtils.getInt("sex")== null||
            SharedUtils.getString("birthday").isEmpty()){
            AlertUtils.showProgress(false,this)
            presenter.getInformation()
        }else{
            Glide.with(this).load(SharedUtils.getString("avatars")).into(civ_head)
            tv_name.text = SharedUtils.getString("username")
            tv_old.text =  SharedUtils.getString("birthday")
            tv_sex.text = if(SharedUtils.getInt("sex") == 1){"男"}else {"女"}
        }
        initClick()
    }

    override fun onLayout(): Int = R.layout.activity_setting
    fun initInformation(data: Data) {
        tv_title.text = "头像与个人信息"
        Glide.with(this).load(data.avatar).into(civ_head)
        tv_name.text = data.username
        tv_old.text = data.birthday
        tv_sex.text = if(data.sex == 1){"男"}else {"女"}
        AlertUtils.dismissProgress()
        rl_head.click {
            var bunder = Bundle()
            bunder.putString("head",data.avatar)
            paramerAction(UserHeadReviewActivity::class.java,bunder)
        }

        rl_head.click {
            var bunder = Bundle()
            bunder.putString("head",data.avatar)
            paramerAction(UserHeadReviewActivity::class.java,bunder)
        }
        rl_name.click {
            ConfirmDialog(this).setConfirmOnClickListener(this).show()
//
        }
        rl_sex.click {
            ConfirmsexDialog(this).setConfirmOnClickListener(this).show()
        }
        rl_old.click {
            //时间选择器
            TimePickerBuilder(this,
                OnTimeSelectListener { dateTime, v ->
                    var time = SimpleDateFormat("YYYY-MM-dd").format(dateTime).toString()
                    tv_old.text  = time
                    presenter.editInformationbirthday(time)
                }).build().show()
        }

    }

    fun initClick(){
        rl_head.click {
            var bunder = Bundle()
            bunder.putString("head",SharedUtils.getString("avatar"))
            paramerAction(UserHeadReviewActivity::class.java,bunder)
        }
        rl_name.click {
            ConfirmDialog(this).setConfirmOnClickListener(this).show()
//
        }
        rl_sex.click {
            ConfirmsexDialog(this).setConfirmOnClickListener(this).show()
        }
        rl_old.click {
            //时间选择器
            TimePickerBuilder(this,
                OnTimeSelectListener { dateTime, v ->
                    var time = SimpleDateFormat("YYYY-MM-dd").format(dateTime).toString()
                    tv_old.text  = time
                    presenter.editInformationbirthday(time)
                }).build().show()
        }

    }
}


