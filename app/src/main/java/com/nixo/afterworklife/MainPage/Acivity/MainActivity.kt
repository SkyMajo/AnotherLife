package com.nixo.afterworklife.MainPage.Acivity

import android.annotation.SuppressLint
import android.os.Bundle
import com.nixo.afterworklife.ActivitePage.Fragment.ActiviteFragment
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.MainPage.Fragement.MajorFragment
import com.nixo.afterworklife.MainPage.Fragement.MineFragment
import com.nixo.afterworklife.MainPage.Fragement.MyGroupFragment
import com.nixo.afterworklife.MainPage.Present.MainPresent
import com.nixo.afterworklife.MainPage.Present.MinePresent
import com.nixo.afterworklife.R
import android.provider.Settings
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.Ext.inT
import com.nixo.afterworklife.Utils.JCameraView.JCameraActivity
import com.nixo.afterworklife.Utils.ToastUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yqjr.superviseapp.utils.GpsUtil
import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import androidx.core.app.ActivityCompat
import com.gyf.immersionbar.ImmersionBar
import com.jerey.loglib.Klog
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.SendBottomDialog
import com.yqjr.superviseapp.utils.dialog.CommonDialog


class MainActivity : BaseActivity<MainPresent>(){

    override fun onLayout(): Int = R.layout.activity_main

    private var rxPermissions: RxPermissions? = null
    private var majorFragment = MajorFragment()
    private var activiteFragment = ActiviteFragment()
    private var MineFragment = MineFragment()
    private var myGroupFragment = MyGroupFragment()

    override fun onState() {
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()
        rxPermissions = RxPermissions(this)
        initClick()
        requestPermissionStarget()
        supportFragmentManager.inT { replace(R.id.rv_fragment,majorFragment) }

    }

     @SuppressLint("CheckResult")
    private fun requestPermissionStarget() {
        rxPermissions!!.requestEachCombined(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe { permission ->
                if (permission.granted) {
                    val isopen = GpsUtil.isOPen(this)
                    if (isopen) {
                    } else {
                        SharedUtils.putString("location_isOpen","0")
                        Klog.e("Nixo --- 权限isOpen",isopen)
                        var PERMISSIONS_STORAGE = arrayOf("android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE")
                        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,1)                    }
                }
            }
    }



    private fun initClick() {
        ll_main.click {
            //首页
            if(!majorFragment.isAdded){
                supportFragmentManager.inT { replace(R.id.rv_fragment,majorFragment) }
            }
            iv_main.setImageResource(R.mipmap.main_main_visiable)
            iv_activie.setImageResource(R.mipmap.main_activie_gone)
            iv_group.setImageResource(R.mipmap.main_group_show_gone)
            iv_me.setImageResource(R.mipmap.main_me_gone)
        }
        ll_group.click {
            //社团
            supportFragmentManager.inT { replace(R.id.rv_fragment,myGroupFragment) }
            iv_main.setImageResource(R.mipmap.main_main_gone)
            iv_activie.setImageResource(R.mipmap.main_activie_gone)
            iv_group.setImageResource(R.mipmap.main_group_show_visiable)
            iv_me.setImageResource(R.mipmap.main_me_gone)
        }
        rv_add.click {
            //加号
            SendBottomDialog(this).show()

        }
        ll_activie.click {
            //消息
            supportFragmentManager.inT { replace(R.id.rv_fragment,activiteFragment) }
            iv_main.setImageResource(R.mipmap.main_main_gone)
            iv_activie.setImageResource(R.mipmap.main_activie_visiable)
            iv_group.setImageResource(R.mipmap.main_group_show_gone)
            iv_me.setImageResource(R.mipmap.main_me_gone)
        }
        ll_me.click {
            //我的
            if(!MineFragment.isAdded){
                supportFragmentManager.inT { replace(R.id.rv_fragment,MineFragment) }
            }
            iv_main.setImageResource(R.mipmap.main_main_gone)
            iv_activie.setImageResource(R.mipmap.main_activie_gone)
            iv_group.setImageResource(R.mipmap.main_group_show_gone)
            iv_me.setImageResource(R.mipmap.main_me_visiable)
        }




    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}



}
