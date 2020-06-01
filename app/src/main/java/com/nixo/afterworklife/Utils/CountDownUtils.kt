package com.yqjr.superviseapp.utils

import android.graphics.Color
import android.os.CountDownTimer
import android.widget.Button

class CountDownUtils {
    private var countDownTimer: CountDownTimer? = null

    constructor(button: Button, max: Int, interval: Int) {
        countDownTimer = object : CountDownTimer((max * 1000).toLong(), (interval * 1000 - 10).toLong()) {
            override fun onTick(time: Long) {
                button.isEnabled = false
                button.text = ((time + 15L) / 1000L).toString()  + "秒后重新发送"
            }

            override fun onFinish() {
//                button.setTextColor(Color.parseColor("#017EFF"))
                button.isEnabled = true
                button.text = "重新发送"

            }
        }
    }

    fun start() {
        countDownTimer!!.start()
    }
}