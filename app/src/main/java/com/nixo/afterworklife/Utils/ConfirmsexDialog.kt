package com.nixo.afterworklife.Utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click

class ConfirmsexDialog : BaseDialog{

    constructor(context: Context) : super(context) {
        init(context)
    }

    private var confirmOnClickListener: ConfirmSexOnClickListener? = null
    fun setConfirmOnClickListener(confirmOnClickListener: ConfirmSexOnClickListener) :ConfirmsexDialog{
        this.confirmOnClickListener = confirmOnClickListener
        return this
    }

    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_sex_confirm, null)
        setContentView(view)
        var llGril = view!!.findViewById<LinearLayout>(R.id.ll_gril)
        var llBoy = view!!.findViewById<LinearLayout>(R.id.ll_boy)
        llGril.click {
            confirmOnClickListener!!.confirm(0)
            dismiss()
        }

        llBoy.click {
            confirmOnClickListener!!.confirm(1)
            dismiss()
        }


    }

    interface ConfirmSexOnClickListener {
        fun confirm(string: Int)
    }
}