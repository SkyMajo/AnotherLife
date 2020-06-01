package com.nixo.afterworklife.GroupPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.gyf.immersionbar.ImmersionBar
import com.nixo.afterworklife.MainPage.Adapter.LocationGroupAdapter
import com.nixo.afterworklife.MainPage.Adapter.MyAllGroupAdapter
import com.nixo.afterworklife.MainPage.Data.MyCreateGroupData
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.safframework.log.json
import kotlinx.android.synthetic.main.activity_all_group.*
import kotlinx.android.synthetic.main.include_titlebar.*

class MyGroupListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_group_list)
        var stringExtra = intent.getStringExtra("json")
        var fromJson =
            Gson().fromJson<MyCreateGroupData>(stringExtra, MyCreateGroupData::class.java)
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()
        tv_title.text = "我的社团"
        ll_title.click { finish() }
        rv_all.layoutManager = LinearLayoutManager(this)
        rv_all.adapter = MyAllGroupAdapter(this, R.layout.item_group,fromJson.data,1)

    }
}
