package com.nixo.afterworklife.Welcome.View.Activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.nixo.afterworklife.Common.AppManager
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.MainPage.Acivity.MainActivity
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.StringUtils
import com.nixo.afterworklife.Utils.ToastUtils
import com.nixo.afterworklife.Welcome.Present.LoginPresent
import com.yqjr.superviseapp.utils.CountDownUtils
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_code
import kotlinx.android.synthetic.main.activity_login.tv_send_code
import kotlinx.android.synthetic.main.activity_phone_login.*


class LoginActivity : BaseActivity<LoginPresent>() {

    override fun onLayout(): Int = R.layout.activity_login

    override fun onState() {
        if(SharedUtils.getString("token").isNotEmpty()) {
            Action(MainActivity::class.java)
            finish()
        }
//        ll_phone_login.click {
//            Action(PhoneLoginActivity::class.java)
//        }
        tv_send_code.click {
            if(et_account_login.text.toString().isEmpty()){
                ToastUtils.newToastCenter(this,"请输入手机号")
            }else{
                if(et_account_login.text.toString().toCharArray().size == 11){
                    CountDownUtils(tv_send_code,60,1).start()
//                AlertUtils.showProgress(true,this)
                    presenter.onVsCode(et_account_login.text.toString().trim())
                }else{
                    ToastUtils.newToastCenter(this,"请输入11位手机号")
                }
            }
        }

        login.click {
            if(et_account_login.text.toString().isEmpty() || et_code.text.toString().isEmpty()){
                ToastUtils.newToastCenter(this,"手机号或验证码不能为空")
            }else{
                AlertUtils.showProgress(true,this)
                presenter.onPhoneLogin(et_account_login.text.toString(), et_code.text.toString())
            }
        }
//        register.click {
//            if(et_account_register.text.toString().isEmpty() || et_pass_register.text.toString().isEmpty()){
//                ToastUtils.newToastCenter(this,"账号或密码不能为空")
//            }else{
//                if (et_pass_register2.text.toString().isEmpty()){
//                    ToastUtils.newToastCenter(this,"请输入确认密码")
//                }else{
//                    if(et_pass_register2.text.toString() != et_pass_register.text.toString()){
//                        ToastUtils.newToastCenter(this,"两次密码不相同，请重新输入")
//                    }else{
//                        AlertUtils.showProgress(true,this)
//                        presenter.onRegister(et_account_register.text.toString(),et_pass_register.text.toString(),et_pass_register2.text.toString())
//                    }
//                }
//            }
//        }


        tv_login_title.click {
            tv_login_title.textSize = StringUtils.dp2px(this,10).toFloat()
            tv_register_title.textSize = StringUtils.dp2px(this,7).toFloat()
            tv_login_title.setTextColor(Color.parseColor("#ffffff"))
            tv_register_title.setTextColor(Color.parseColor("#ABFFFFFF"))
            ll_login.visibility = View.VISIBLE
            ll_register.visibility = View.GONE
        }
//        tv_register_title.click {
//            tv_register_title.textSize = StringUtils.dp2px(this,10).toFloat()
//            tv_login_title.textSize = StringUtils.dp2px(this,7).toFloat()
//            tv_register_title.setTextColor(Color.parseColor("#ffffff"))
//            tv_login_title.setTextColor(Color.parseColor("#ABFFFFFF"))
//            ll_login.visibility = View.GONE
//            ll_register.visibility = View.VISIBLE
//        }

    }

    fun onLoginSusscess (){
        AlertUtils.dismissProgress()
        AppManager.appManager!!.finishAllActivity()
        Action(MainActivity::class.java)
    }
//    fun onRegisterSusscess(){
//        Action(MainActivity::class.java)
//        AppManager.appManager!!.finishAllActivity()
//    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}
    fun onSplashSuccess() {
        Action(SplashActivity::class.java)
        finish()
    }

    fun onCutDownTimeFucation() {


    }


}
