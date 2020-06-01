package com.nixo.afterworklife.UserPage.View

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.jerey.loglib.Klog
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.UserPage.Present.UserHeadReviewPresnet
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.ImageRepostion
import com.nixo.afterworklife.Utils.ImageUploadManager
import com.nixo.afterworklife.Utils.ToastUtils
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import kotlinx.android.synthetic.main.activity_user_head_review.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.File
import java.io.FileInputStream
import com.nixo.afterworklife.Utils.ImageUploadManager.ImageUploadListener as ImageUploadListener1


class UserHeadReviewActivity : BaseActivity<UserHeadReviewPresnet>() {
    override fun onLayout(): Int = com.nixo.afterworklife.R.layout.activity_user_head_review

    override fun onState() {
        Glide.with(this).load(intent.getStringExtra("head")?:"").into(iv_head_review)
        tv_select.click {
            // 自由配置选项
            val config = ISListConfig.Builder()
                // 是否多选, 默认true
                .multiSelect(false)
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
//                .needCrop(true)
                // 第一个是否显示相机，默认true
//                .needCamera(false)
                // 最大选择图片数量，默认9
                .maxNum(1)
                .build()

// 跳转到图片选择器
            ISNav.getInstance().toListActivity(this, config, 200)

        }
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 图片选择结果回调
        if (requestCode == 200 && resultCode == Activity.RESULT_OK && data != null) {
            val pathList = data.getStringArrayListExtra("result")
            var parse = MediaType.parse("image/png")
            var builder = MultipartBody.Builder()
            var decodeStream = BitmapFactory.decodeStream(FileInputStream(pathList[0]))
            Glide.with(this).load(decodeStream).into(iv_head_review)
            builder.setType(MultipartBody.FORM)
            var arrayList = ArrayList<String>()
            for (path in pathList!!) {
                var file = File(path)
                if(file == null){
                    ToastUtils.newToastCenter(this,"文件是空的")
                }else{
                    builder.addPart(MultipartBody.Part.createFormData("file[]", file.name, RequestBody.create(MediaType.parse("image/png"),file)))
                    builder.addFormDataPart("type","avatars")
                }
            }
//            arrayList.add("${Environment.getExternalStorageState()}/Tencent/QQ_Images/-602add69ae8aeb52 jpg ")
            ImageUploadManager.instance().imageUpLoadRequest(this,pathList,
                object : ImageUploadManager.ImageUploadListener {
                    override fun onSuccess(fromJson: ImageRepostion?) {
                        GlobalScope.launch(Dispatchers.Main) {
                            if (fromJson != null) {
                                SharedUtils.putString("avatars",fromJson.data!![0])
                                presenter.upDataHead(fromJson.data!![0])
                            }
                            if (fromJson != null) {

                            }
//                        ToastUtils.newToastCenter(this@UserHeadReviewActivity, fromJson.msg)
                            AlertUtils.showProgress(false,this@UserHeadReviewActivity)
                        }

                    }





                    override fun onFailed(reason: String?) {
                        Klog.e("ImageUploadManager",reason.toString())
                    }


                })

//            presenter.upDataHead(builder.build())

        }
    }
    fun onSuccess(){
        AlertUtils.dismissProgress()
    }
    override fun onDestory() {
    }

}
