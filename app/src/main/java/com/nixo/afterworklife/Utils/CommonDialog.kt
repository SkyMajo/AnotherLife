package com.yqjr.superviseapp.utils.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.BaseDialog
import com.nixo.afterworklife.Utils.Ext.click
import kotlinx.android.synthetic.main.dialog_loaction.*

class CommonDialog(context: Context) : BaseDialog(context) {
    private var listener: OnDialogListener? = null
    fun setOnDialogListener(listener: OnDialogListener) {
        this.listener = listener
    }
    init {
        init(context)
    }

    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_loaction, null)
        view.findViewById<LinearLayout>(R.id.ll_dismiss).click { dismiss() }
        setContentView(view)
        setCanceledOnTouchOutside(false)
        iv_close.setOnClickListener {
            dismiss()
        }
        bt_open.setOnClickListener {
            listener!!.onDetermine()
        }
    }

    interface OnDialogListener {

        fun onDetermine(vararg msg: String)

    }
}