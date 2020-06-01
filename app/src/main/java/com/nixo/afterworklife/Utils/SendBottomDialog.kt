package com.nixo.afterworklife.Utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.nixo.afterworklife.ActivitePage.Activity.SendActivieActivity
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.JCameraView.JCameraActivity
import com.tbruyelle.rxpermissions2.RxPermissions

class SendBottomDialog(context: Context) :BaseDialog(context){
    init {
        initView()
    }

    fun initView(){
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_send_main, null)

        view.findViewById<LinearLayout>(R.id.ll_text).click {
            var intent = Intent(context, SendActivieActivity::class.java)

            intent.putExtra("startType","text")
            context.startActivity(intent)
            dismiss()
        }
        view.findViewById<LinearLayout>(R.id.ll_photo).click {
            var intent = Intent(context, SendActivieActivity::class.java)
            intent.putExtra("startType","photo")
            context.startActivity(intent)
            dismiss()
        }
        view.findViewById<LinearLayout>(R.id.ll_video).click {
            var intent = Intent(context, JCameraActivity::class.java)
            context.startActivity(intent)
            dismiss()
        }
        view.findViewById<TextView>(R.id.tv_cancel).click {
            dismiss()
        }
        setCancelable(true)
        setContentView(view)
    }
}