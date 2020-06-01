package com.nixo.afterworklife.Utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Ext.click

class ReplayDialog(context: Context) : com.nixo.afterworklife.Utils.BaseDialog(context) {
    init {
        initView()
    }

    public interface replayInterface{
        fun onReplay(string: String)
    }
    var infs : replayInterface? = null


    fun initView(){
        var view = LayoutInflater.from(context).inflate(R.layout.item_replays, null)
        var tvSendReplay = view.findViewById<TextView>(R.id.tv_send)
        var v_back = view.findViewById<View>(R.id.v_back)
        v_back.click {
            dismiss()
        }
        tvSendReplay.click {
        infs!!.onReplay(view.findViewById<EditText>(R.id.et_replay).text.toString())
        }
        setCancelable(true)
        setView(view)

    }
}

