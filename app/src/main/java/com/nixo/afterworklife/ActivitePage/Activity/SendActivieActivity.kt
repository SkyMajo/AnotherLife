package com.nixo.afterworklife.ActivitePage.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.gyf.immersionbar.ImmersionBar
import com.jerey.loglib.Klog
import com.lzy.ninegrid.preview.NineGridViewClickAdapter
import com.nixo.afterworklife.ActivitePage.Adapter.NineImgSimperAdapter
import com.nixo.afterworklife.ActivitePage.Presenter.SendActivitePresenter
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.Common.Common
import com.nixo.afterworklife.Common.Common.bitmap
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.ImageRepostion
import com.nixo.afterworklife.Utils.ImageUploadManager
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_send_activie.*
import kotlinx.android.synthetic.main.activity_video.*
import kotlinx.android.synthetic.main.include_titlebar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import java.io.File

class SendActivieActivity : BaseActivity<SendActivitePresenter>() {
    override fun onLayout(): Int = R.layout.activity_send_activie

    var arrayList = ArrayList<String>()
    var adapter :NineImgSimperAdapter? = null
    var video:String = ""
    var id = 0

    override fun onState() {
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()
        var startType = intent.getStringExtra("startType")
        tv_send.visibility = View.VISIBLE
        tv_send.text = "发布"
        when (startType) {
            "text" -> {
                tv_title.text = "文字动态"
                iv_video_j.visibility = View.GONE
                rv_photo_review.visibility = View.GONE
            }
            "photo" -> {
                tv_title.text = "图文动态"
                iv_video_j.visibility = View.GONE
                arrayList.add("null")
            }
            "video" -> {
                tv_title.text = "拍摄动态"
                iv_video_j.visibility = View.GONE
                arrayList.add("null")
            }
            "video_J"->{
                iv_video_j.visibility = View.VISIBLE
                video = intent.getStringExtra("video_J")
                iv_video_j.setUpForFullscreen(video,
                    "https://i.loli.net/2019/11/28/MKeu9rWdCzJVp5x.png" ,"预览")
                rv_photo_review.visibility = View.GONE
                tv_title.text = "拍摄动态"
            }
            "photo_J"->{
                arrayList = ArrayList<String>()
                tv_title.text = "拍摄动态"
                iv_photo_j.visibility = View.VISIBLE
                rv_photo_review.visibility = View.GONE
                var stringExtra = intent.getStringExtra("imageUrl")
                Klog.e("image",stringExtra)
                var options =  BitmapFactory.Options();
                options.inSampleSize = 2;
                var bitmap = BitmapFactory.decodeFile(stringExtra,options)
                Klog.e("image",bitmap==null)
                arrayList.add(stringExtra)
                Glide.with(this).load(File(stringExtra)).into(iv_photo_j)
            }
        }
        rv_photo_review.layoutManager = GridLayoutManager(this, 3)
        adapter = NineImgSimperAdapter(this, R.layout.item_nine_review, arrayList)
        rv_photo_review.adapter = adapter
        tv_clear.click {
            tv_clear.visibility = View.INVISIBLE
            arrayList.clear()
            arrayList.add("null")
            adapter!!.addDataList(arrayList, true)
        }
        tv_send.click {
            AlertUtils.showProgress(false, this)
            when {
                intent.getStringExtra("startType") == "text" -> {
                    presenter.onSendRequest(
                        ArrayList<String>(), et_content.text.toString(),
                        "", id.toString(), "上海")

                }
                intent.getStringExtra("startType") == "video_J" ->{
                    ImageUploadManager.instance().VideoUpLoadRequest(this, video,
                        object : ImageUploadManager.ImageUploadListener {
                            override fun onSuccess(fromJson: ImageRepostion?) {
                                GlobalScope.launch(Dispatchers.Main) {
                                    if (fromJson != null) {
                                        presenter.onSendRequest(
                                          ArrayList<String>() ,
                                            et_content.text.toString(),
                                            fromJson!!.data!![0],
                                            id.toString(),
                                            "上海"
                                        )
                                    }
                                    AlertUtils.showProgress(false, this@SendActivieActivity)
                                }
                            }
                            override fun onFailed(reason: String?) {
                                Klog.e("ImageUploadManager", reason.toString())
                                AlertUtils.dismissProgress()
                            }


                        })
                }
                intent.getStringExtra("startType") == "photo" ||
                        intent.getStringExtra("startType") == "photo_J"->
                    ImageUploadManager.instance().imageUpLoadRequest(this, arrayList,
                    object : ImageUploadManager.ImageUploadListener {
                        override fun onSuccess(fromJson: ImageRepostion?) {
                            GlobalScope.launch(Dispatchers.Main) {
                                if (fromJson != null) {
                                    presenter.onSendRequest(
                                        fromJson!!.data!!,
                                        et_content.text.toString(),
                                        video,
                                        id.toString(),
                                        "上海"
                                    )
                                }
                                AlertUtils.showProgress(false, this@SendActivieActivity)
                            }
                        }
                        override fun onFailed(reason: String?) {
                            Klog.e("ImageUploadManager", reason.toString())
                            AlertUtils.dismissProgress()
                        }


                    })
                else -> {
                    var arrayList = ArrayList<String>()
                    arrayList.add(video)
                    ImageUploadManager.instance().imageUpLoadRequest(this, arrayList,
                        object : ImageUploadManager.ImageUploadListener {
                            override fun onSuccess(fromJson: ImageRepostion?) {
                                GlobalScope.launch(Dispatchers.Main) {
                                    if (fromJson != null) {
                                        presenter.onSendRequest(
                                            ArrayList<String>(), et_content.text.toString(),
                                            fromJson!!.data?.get(0)!!, id.toString(), "上海"
                                        )
                                    }
                                    AlertUtils.showProgress(false, this@SendActivieActivity)
                                }
                            }
                            override fun onFailed(reason: String?) {
                                Klog.e("ImageUploadManager", reason.toString())
                                AlertUtils.dismissProgress()
                            }


                        })
                }
            }
        }
        tv_group.click {
            startActivityForResult(Intent(this,GroupChoseActivity::class.java),201)
        }
    }

    fun onSuccess() {
        AlertUtils.dismissProgress()
        finish()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 图片选择结果回调
        if (requestCode == 200 && resultCode == Activity.RESULT_OK && data != null) {
            tv_clear.visibility = View.VISIBLE
            tv_clear.text = "清除图片"

            val pathList = data.getStringArrayListExtra("result")
            var parse = MediaType.parse("image/png")
            var builder = MultipartBody.Builder()
            builder.setType(MultipartBody.FORM)
            var arrayList = ArrayList<String>()
            arrayList.clear()
            if(pathList.size <= 8){
                arrayList.add("null")
            }
            arrayList.addAll(pathList)
            adapter!!.addDataList(arrayList,true)
        }
        if (requestCode == 201 && resultCode == Activity.RESULT_OK && data != null) {
            tv_group.text = "#"+data!!.getStringExtra("name")
            id = data!!.getIntExtra("id",0)
        }
    }

    //将Uri中图片相关数据转换成bitmap
    private fun Uri_to_bitmap(imageView: ImageView) {
        val intent = intent
        if (intent != null) {
            val uri = intent.data
            if (uri == null) {
                Log.i("tag", "The uri is not exist.")
                return
            }
            intent.setDataAndType(uri, "image/*")
            val extras = intent.extras
            if (extras != null) {
                val photo = extras.getParcelable<Bitmap>("intent")
                imageView.setImageBitmap(photo)
            } else {
                Log.i("extras", "The extras is null.")
            }
        } else {
            Log.i("intent", "The intent is null.")
        }
    }


    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}


}
