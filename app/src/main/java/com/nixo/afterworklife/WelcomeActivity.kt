package com.nixo.afterworklife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.gyf.immersionbar.ImmersionBar
import com.nixo.afterworklife.MainPage.Acivity.MainActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_555555)
            .init()
        GlobalScope.launch (Dispatchers.Main){
            Glide.with(this@WelcomeActivity).load(R.mipmap.splash_bg).into(iv_splash)
            Thread.sleep(2000)
            startActivity(Intent(this@WelcomeActivity,MainActivity::class.java))
            finish()
        }
    }
}
