package com.nixo.afterworklife.Utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click
import kotlinx.android.synthetic.main.dialog_exit_cetificate_confirm.*

class ConfirmTextDialog : BaseDialog{

    var toast = ""
    constructor(context: Context, toast:String) : super(context) {
        this.toast = toast
        init(context)
    }

    private var confirmOnClickListener: ConfirmExitOnClickListener? = null
    fun setConfirExitmOnClickListener(confirmOnClickListener: ConfirmExitOnClickListener) :ConfirmTextDialog{
        this.confirmOnClickListener = confirmOnClickListener
        return this
    }



    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_group_cetificate_confirm, null)
        setContentView(view)
        var tvYes = view!!.findViewById<TextView>(R.id.tv_yes)
        var etName = view!!.findViewById<TextView>(R.id.et_name)
        var tvNo = view!!.findViewById<TextView>(R.id.tv_no)
        etName.text = toast
        tvNo.click {
            dismiss()
        }

        tvYes.click {
            if (confirmOnClickListener != null) {
                    confirmOnClickListener!!.confirmExit("")
                    dismiss()
                }
            }
        }


    interface ConfirmExitOnClickListener {
        fun confirmExit(string: String)
    }
}
