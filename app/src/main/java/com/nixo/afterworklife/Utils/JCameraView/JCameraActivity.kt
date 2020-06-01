package com.nixo.afterworklife.Utils.JCameraView

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cjt2325.cameralibrary.listener.JCameraListener
import com.jerey.loglib.Klog
import com.nixo.afterworklife.ActivitePage.Activity.SendActivieActivity
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.ToastUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yqjr.superviseapp.utils.GpsUtil
import kotlinx.android.synthetic.main.activity_jcamera.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class JCameraActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.nixo.afterworklife.R.layout.activity_jcamera)

        var rxPermissions = RxPermissions(this)
        requestPermissionLocations(rxPermissions)

        if (Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        } else {
            val decorView = window.decorView
            val option = View.SYSTEM_UI_FLAG_FULLSCREEN
            decorView.systemUiVisibility = option
        }
        /**
         * 设置视频保存路径
         */
        jcameraview.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera")
        /**
         * JCameraView监听
         */
        jcameraview.setJCameraLisenter(object : JCameraListener {
            override fun recordSuccess(url: String?, firstFrame: Bitmap?) {
                var intent = Intent(this@JCameraActivity, SendActivieActivity::class.java)
                intent.putExtra("startType","video_J")
                intent.putExtra("video_J",url)
                startActivity(intent)
                finish()
            }

            override fun captureSuccess(bitmap: Bitmap?) {
                if (bitmap != null) {
                    var url = saveBitmap(bitmap)
                    var intent = Intent(this@JCameraActivity, SendActivieActivity::class.java)
                    intent.putExtra("startType","photo_J")
                    intent.putExtra("imageUrl",url)
                    startActivity(intent)
                    Log.i("JCameraView", "bitmap = " + bitmap.getWidth())
                    finish()
                }
            }


        })
    }

    @SuppressLint("CheckResult", "MissingPermission")
    fun requestPermissionLocations(rxPermissions:RxPermissions) {
        rxPermissions!!.requestEachCombined(Manifest.permission.RECORD_AUDIO,Manifest.permission.CAMERA)
            .subscribe ({

                if (Build.VERSION.SDK_INT >= 19) {
                    val decorView = window.decorView
                    decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
                } else {
                    val decorView = window.decorView
                    val option = View.SYSTEM_UI_FLAG_FULLSCREEN
                    decorView.systemUiVisibility = option
                }
                /**
                 * 设置视频保存路径
                 */
                jcameraview.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera")
                /**
                 * JCameraView监听
                 */
                jcameraview.setJCameraLisenter(object : JCameraListener {
                    override fun recordSuccess(url: String?, firstFrame: Bitmap?) {
                        var intent = Intent(this@JCameraActivity, SendActivieActivity::class.java)
                        intent.putExtra("startType","video_J")
                        intent.putExtra("video_J",url)
                        startActivity(intent)
                        finish()
                    }

                    override fun captureSuccess(bitmap: Bitmap?) {
                        if (bitmap != null) {
                            var url = saveBitmap(bitmap)
                            var intent = Intent(this@JCameraActivity, SendActivieActivity::class.java)
                            intent.putExtra("startType","photo_J")
                            intent.putExtra("imageUrl",url)
                            startActivity(intent)
                            Log.i("JCameraView", "bitmap = " + bitmap.getWidth())
                            finish()
                        }
                    }


                })
            },{
                ToastUtils.newToastCenter(this,"请赋予权限后重试")

                finish()
            })


    }

    fun saveBitmap( mBitmap:Bitmap):String {
        //图片地址
        var sdpath =Environment.getExternalStorageDirectory().getPath() + File.separator
        var  file =  File("${sdpath}","photo")
        if (!file.exists()) {
            file.mkdir();
        }

        if(SharedUtils.getInt("photoIndex") == null){
            SharedUtils.putInt("photoIndex",0)
        }

        var tmpf =  File(file, "photo${SharedUtils.getInt("photoIndex")}" + ".jpg");
        SharedUtils.putInt("photoIndex",SharedUtils.getInt("photoIndex")+1)
//        var tmpf =  isAbsolutes(file,2)
        var f = tmpf;

        try {
            f.createNewFile();
            var  fOut :FileOutputStream?= null;
            fOut =  FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut!!.flush();
            fOut!!.close();
        } catch ( e:FileNotFoundException ) {
            e.printStackTrace();
        } catch ( e : IOException) {
            e.printStackTrace();
        }
        var image_file_url=f.getAbsolutePath();
        Log.i("image_file_url", image_file_url);

        return image_file_url;
    }

    fun isAbsolutes(file: File,index:Int):File{
        var fileI = File(file, "photo$index.jpg")
        if(fileI.isAbsolute){
            return isAbsolutes(fileI,index)
        }else{
            return fileI
        }
    }

    override fun onResume() {
        super.onResume()
        jcameraview.onResume()
    }

    override fun onPause() {
        super.onPause()
        jcameraview.onPause()
    }

}
