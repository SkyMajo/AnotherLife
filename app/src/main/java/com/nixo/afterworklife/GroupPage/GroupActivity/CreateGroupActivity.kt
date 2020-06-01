package com.nixo.afterworklife.GroupPage.GroupActivity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.gyf.immersionbar.ImmersionBar
import com.jerey.loglib.Klog
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.GroupPage.GroupPresent.CreateGroupPresent
import com.nixo.afterworklife.MainPage.Data.GroupInfo.GroupInfoData
import com.nixo.afterworklife.MainPage.Present.GroupPresent
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.ImageRepostion
import com.nixo.afterworklife.Utils.ImageUploadManager
import com.nixo.afterworklife.Utils.ToastUtils
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import kotlinx.android.synthetic.main.activity_group.*
import kotlinx.android.synthetic.main.activity_user_head_review.*
import kotlinx.android.synthetic.main.include_titlebar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileInputStream

class CreateGroupActivity : BaseActivity<CreateGroupPresent>() {
    override fun onLayout(): Int = R.layout.activity_group

    var avatars = ""
    var groupId = 0
    override fun onState() {
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()
        groupId = intent.getIntExtra("id", 0)
        if(intent.getStringExtra("startType") == "edit"){
            tv_title.text = "编辑社团"
            tv_send .text = "提交更改"
            AlertUtils.showProgress(false,this)
            presenter.getGroupInfo(groupId)
        }else{
            tv_title.text = "创建社团"
            tv_send .text = "申请创建"
            tv_send.click {
                if(et_name.text.toString().isEmpty()){
                    ToastUtils.newToastCenter(this,"请输入群名!")
                    return@click
                }
                if(avatars.isEmpty()){
                    ToastUtils.newToastCenter(this,"请选择头像!")
                    return@click
                }
                if(et_logo.text.toString().isEmpty()){
                    ToastUtils.newToastCenter(this,"请输入标志语!")
                    return@click
                }
                if(et_max_member.text.toString().isEmpty()){
                    ToastUtils.newToastCenter(this,"请输入最大人数!")
                    return@click
                }
                AlertUtils.showProgress(false,this@CreateGroupActivity)
                var arrayList = ArrayList<String>()
                arrayList.add(avatars)
                ImageUploadManager.instance().imageUpLoadRequest(this,arrayList,
                    object : ImageUploadManager.ImageUploadListener {
                        override fun onSuccess(fromJson: ImageRepostion?) {
                            GlobalScope.launch(Dispatchers.Main) {
                                if (fromJson != null) {
                                    presenter.onCreate(et_name.text.toString(),et_description.text.toString(),"121.4256","31.0272",
                                        fromJson.data?.get(0)!!,et_logo.text.toString(),et_max_member.text.toString())
                                }

                            }

                        }





                        override fun onFailed(reason: String?) {
                            Klog.e("ImageUploadManager",reason.toString())
                            AlertUtils.dismissProgress()
                        }


                    })
            }
        }

        ll_title.click {
            finish()
        }
        tv_send.visibility = View.VISIBLE

        iv_head.click {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 图片选择结果回调
        if (requestCode == 200 && resultCode == Activity.RESULT_OK && data != null) {
            val pathList = data.getStringArrayListExtra("result")
            Glide.with(this).load(pathList[0]).into(iv_head)
            avatars = pathList[0]
        }
    }


    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}
    fun onSuccess() {
        finish()
    }

    fun onFail() {
        AlertUtils.dismissProgress()
    }

    fun onSuccessInfo(await: GroupInfoData) {
        if(await.code == 200){
            Glide.with(this).load(await.data.avatar).into(iv_head)
            avatars = await.data.avatar
            et_name.setText(await.data.name)
            et_description.setText(await.data.description)
            et_logo.setText(await.data.logo)
            et_max_member.setText(await.data.max_member.toString()?:"30")
            AlertUtils.dismissProgress()
            tv_send.click {
                if(et_name.text.toString().isEmpty()){
                    ToastUtils.newToastCenter(this,"请输入社团名!")
                    return@click
                }

                if(et_logo.text.toString().isEmpty()){
                    ToastUtils.newToastCenter(this,"请输入标志语!")
                    return@click
                }
                if(et_max_member.text.toString().isEmpty()){
                    ToastUtils.newToastCenter(this,"请输入最大人数!")
                    return@click
                }
                AlertUtils.showProgress(false,this@CreateGroupActivity)
                if(avatars == await.data.avatar){
                    presenter.onEdit(groupId,et_name.text.toString(),et_description.text.toString(),"121.4256","31.0272",
                       avatars!!,et_logo.text.toString(),et_max_member.text.toString())
                }else{
                    var arrayList = ArrayList<String>()
                    arrayList.add(avatars)
                    ImageUploadManager.instance().imageUpLoadRequest(this,arrayList,
                        object : ImageUploadManager.ImageUploadListener {
                            override fun onSuccess(fromJson: ImageRepostion?) {
                                GlobalScope.launch(Dispatchers.Main) {
                                    if (fromJson != null) {
                                        presenter.onEdit(groupId,et_name.text.toString(),et_description.text.toString(),"121.4256","31.0272",
                                            fromJson.data?.get(0)!!,et_logo.text.toString(),et_max_member.text.toString())
                                    }

                                }

                            }





                            override fun onFailed(reason: String?) {
                                Klog.e("ImageUploadManager",reason.toString())
                                AlertUtils.dismissProgress()
                            }


                        })
                }

            }
        }else{
            AlertUtils.dismissProgress()
            ToastUtils.newToastCenter(this,"获取社团资料失败")
        }
    }

    fun onEditSussece() {
        AlertUtils.dismissProgress()
        ToastUtils.newToastCenter(this,"修改成功")
        finish()
    }


}
