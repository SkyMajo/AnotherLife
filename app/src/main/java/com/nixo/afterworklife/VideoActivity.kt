package com.nixo.afterworklife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_121212)
            .init()
        var videoUri = intent.getStringExtra("uri")
        var videoTitle = intent.getStringExtra("title")
        videocontroller1.setUpForFullscreen(videoUri,
           "https://i.loli.net/2019/11/28/MKeu9rWdCzJVp5x.png" ,videoTitle)
    }

    @Override
    override fun onPause() {
        super.onPause()
        videocontroller1.removeAllViews();
    }

    @Override
    public
    override fun onBackPressed() {
        super.onBackPressed()
    }

}
