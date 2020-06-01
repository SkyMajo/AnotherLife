package com.nixo.afterworklife.ActivitePage.Adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nixo.afterworklife.Common.IView
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.RecyclerView.BaseAdapter
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import kotlinx.android.synthetic.main.item_group_review_style2.view.*
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder
import java.io.FileInputStream

class NineImgSimperAdapter(var context: Context,layout:Int,imgs:ArrayList<String>):BaseAdapter<String>(context,layout,imgs){
    override fun initView(p0: BaseHolder, position: Int) {
        var view = p0.getView<ImageView>(R.id.iv_img)
        if(mDataList[position] == "null"){
            Glide.with(context).load(R.mipmap.plus).into(view)
            view.click {
                // 自由配置选项
                var config =  ISListConfig.Builder()
                    // 是否多选, 默认true
//                    .multiSelect(false)
                    // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                    .rememberSelected(false)
                    // “确定”按钮背景色
                    .btnBgColor(Color.GRAY)
                    // “确定”按钮文字颜色
                    .btnTextColor(Color.BLUE)
                    // 使用沉浸式状态栏
                    .statusBarColor(Color.parseColor("#f25531"))
                    // 返回图标ResId
                    .backResId(com.nixo.afterworklife.R.mipmap.left_enter)
                    // 标题
                    .title("选择图片")
                    // 标题文字颜色
                    .titleColor(Color.WHITE)
                    // TitleBar背景色
                    .titleBgColor(Color.parseColor("#f25531"))
                    // 裁剪大小。needCrop为true的时候配置
                    .needCrop(true)
                    .cropSize(1, 1, 200, 200)
                    .needCrop(false)
                    // 第一个是否显示相机，默认true
                    .needCamera(false)
                    // 最大选择图片数量，默认9
                    .maxNum(9)
                    .build()
                ISNav.getInstance().toListActivity(context, config, 200)
            }
        }else{
            var decodeStream = BitmapFactory.decodeStream(FileInputStream(mDataList[position]))
            Glide.with(context).load(decodeStream).into(view)
        }

    }


}
