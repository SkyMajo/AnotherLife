package com.nixo.afterworklife.Utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click

class ConfirmDialog : BaseDialog{

    constructor(context: Context) : super(context) {
        init(context)
    }

    private var confirmOnClickListener: ConfirmOnClickListener? = null
    fun setConfirmOnClickListener(confirmOnClickListener: ConfirmOnClickListener) :ConfirmDialog{
        this.confirmOnClickListener = confirmOnClickListener
        return this
    }

    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_cetificate_confirm, null)
        setContentView(view)
        var tvYes = view!!.findViewById<TextView>(R.id.tv_yes)
        var etName = view!!.findViewById<EditText>(R.id.et_name)
        var tvNo = view!!.findViewById<TextView>(R.id.tv_no)
        tvNo.click {
            dismiss()
        }

        tvYes.click {
            if (confirmOnClickListener != null) {
                if(etName.text.toString().isEmpty()){
                    ToastUtils.newToastCenter(context,"用户名不能为空")
                }else{
                    confirmOnClickListener!!.confirm(etName.text.toString())
                    dismiss()
                }
            }
        }

    }

    interface ConfirmOnClickListener {
        fun confirm(string: String)
    }
}