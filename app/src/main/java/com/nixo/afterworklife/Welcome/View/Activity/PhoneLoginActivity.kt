package com.nixo.afterworklife.Welcome.View.Activity

import android.os.Bundle
import com.nixo.afterworklife.Common.AppManager
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.MainPage.Acivity.MainActivity
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Present.PhoneLoginPresent
import com.yqjr.superviseapp.utils.CountDownUtils
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_phone_login.*

class PhoneLoginActivity :BaseActivity<PhoneLoginPresent>(){

    override fun onLayout(): Int  = R.layout.activity_phone_login

    override fun onState() {
        tv_send_code.click {
            var phoneStrign = et_phone.text.toString()
            if(phoneStrign.isEmpty()){
                ToastUtils.newToastCenter(this,"请输入手机号")
            }else{
                CountDownUtils(tv_send_code,60,1).start()
//                AlertUtils.showProgress(true,this)
                presenter.onVsCode(phoneStrign)
            }
        }

        register.click {
            var phoneStrign = et_phone.text.toString()
            if(phoneStrign.isEmpty()){
                ToastUtils.newToastCenter(this,"请输入手机号")
            }else{
                if(et_code.text.toString().isEmpty()){
                    ToastUtils.newToastCenter(this,"请输入验证码")
                }else{
                    CountDownUtils(tv_send_code,60,1).start()
                    AlertUtils.showProgress(true,this)
                    presenter.onPhoneLogin(phoneStrign,et_code.text.toString())
                }

            }

        }
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    fun onCutDownTimeFucation(){}

    fun onLoginSuccess(){
        AlertUtils.dismissProgress()
        Action(MainActivity::class.java)
        AppManager.appManager!!.finishAllActivity()
    }

    override fun onDestory() {}
    fun onSplashSuccess() {
        Action(SplashActivity::class.java)
        AppManager.appManager!!.finishAllActivity()
    }
}
