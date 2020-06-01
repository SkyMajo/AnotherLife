package com.nixo.afterworklife.ActivitePage.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import com.nixo.afterworklife.Utils.Ext.yes
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bumptech.glide.Glide
import com.jerey.loglib.Klog
import com.nixo.afterworklife.ActivitePage.DATA.AInfoData.Data
import com.nixo.afterworklife.ActivitePage.Presenter.CreateActivitePresent
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.Common.BasePresenter
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.ImageRepostion
import com.nixo.afterworklife.Utils.ImageUploadManager
import com.nixo.afterworklife.Utils.ToastUtils
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import kotlinx.android.synthetic.main.activity_create_activite.*
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.activity_user_head_review.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat


class CreateActiviteActivity : BaseActivity<CreateActivitePresent>() {

    override fun onLayout(): Int = R.layout.activity_create_activite

    var logo  = ""
    var id = 0

    override fun onState() {

        tv_finish.click {
            finish()
        }
        civ_logo.click {
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
            ISNav.getInstance().toListActivity(this, config, 200)
        }

        var stringExtra = intent.getStringExtra("startType")
        if(stringExtra == "edit"){
            tv_create.text = "保存"
            id = intent.getIntExtra("id",0)
            presenter.getInfoData(intent.getIntExtra("id",0))
        }else{
            tv_create.text = "创建"
        }

        tv_create.click {
            (logo.isEmpty()).yes{
                ToastUtils.newToastCenter(this,"请选择头像")
                return@click
            }


            checkStringEmpty(et_title,"请输入活动标题").yes { return@click  }
            checkStringEmpty(et_description,"请输入活动描述").yes { return@click  }
            checkStringEmpty(et_content,"请输入活动内容").yes { return@click  }
            (tv_start_time.text.isEmpty()).yes{
                ToastUtils.newToastCenter(this,"请输入开始时间")
                return@click
            }
            checkStringEmpty(et_charge_amount,"请输入活动所需费用").yes { return@click  }
            checkStringEmpty(et_real_amount,"请输入活动优惠费用").yes { return@click  }
            checkStringEmpty(et_address,"请输入活动地址").yes { return@click  }
            if(stringExtra == "edit"){
                presenter.onEditA(
                    id,
                    et_title.text.toString(),
                    et_description.text.toString(),
                    et_content.text.toString(),
                    tv_start_time.text.toString(),
                    logo,
                    et_charge_amount.text.toString(),
                    et_real_amount.text.toString(),
                    et_address.text.toString(),
                    if(sw_close.isChecked){0}else{1}
                )
            }else{
                presenter.onCreateA(
                    intent.getIntExtra("groupId",0),
                    et_title.text.toString(),
                    et_description.text.toString(),
                    et_content.text.toString(),
                    tv_start_time.text.toString(),
                    logo,
                    et_charge_amount.text.toString(),
                    et_real_amount.text.toString(),
                    et_address.text.toString()
                )

            }

        }
        ll_time.click {
            //时间选择器
            TimePickerBuilder(this,
                OnTimeSelectListener { dateTime, v ->
                    var time = SimpleDateFormat("YYYY-MM-dd").format(dateTime).toString()
                    tv_start_time.text  = time
                }).build().show()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 图片选择结果回调
        if (requestCode == 200 && resultCode == Activity.RESULT_OK && data != null) {
            val pathList = data.getStringArrayListExtra("result")
            var parse = MediaType.parse("image/png")
            var builder = MultipartBody.Builder()
            var decodeStream = BitmapFactory.decodeStream(FileInputStream(pathList[0]))
            Glide.with(this).load(decodeStream).into(civ_logo)
            builder.setType(MultipartBody.FORM)
            var arrayList = ArrayList<String>()
            for (path in pathList!!) {
                var file = File(path)
                if(file == null){
                    ToastUtils.newToastCenter(this,"文件是空的")
                }else{
                    builder.addPart(
                        MultipartBody.Part.createFormData("file[]", file.name, RequestBody.create(
                            MediaType.parse("image/png"),file)))
                    builder.addFormDataPart("type","avatars")
                }
            }
//            arrayList.add("${Environment.getExternalStorageState()}/Tencent/QQ_Images/-602add69ae8aeb52 jpg ")
            ImageUploadManager.instance().imageUpLoadRequest(this,pathList,
                object : ImageUploadManager.ImageUploadListener {
                    override fun onSuccess(fromJson: ImageRepostion?) {
                        GlobalScope.launch(Dispatchers.Main) {
                            if (fromJson != null) {
                                var s = fromJson.data!![0]
                                logo = s
                            }

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
    fun checkStringEmpty(et:EditText,toast:String):Boolean{
        var result = et.text.toString().isEmpty()
        if (result) {
            ToastUtils.newToastCenter(this,toast)
        }
        return result
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}

    fun OnCreateSuccess() {
        ToastUtils.newToastCenter(this,"创建成功")
        finish()
    }

    fun onSuccessInfo(data: Data) {
        logo = data.logo
        Glide.with(this).load(data.logo).into(civ_logo)
        et_title.setText(data.title)
        et_description.setText(data.description)
        et_content.setText(data.content)
        tv_start_time.setText(data.startTime)
        et_charge_amount.setText(data.chargeAmount)
        et_real_amount.setText(data.realAmount)
        et_address.setText(data.address)
    }


}