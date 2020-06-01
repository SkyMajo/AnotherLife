package com.nixo.afterworklife.Welcome.Moudle

import com.google.gson.annotations.SerializedName

data class InteresBean(@SerializedName("msg")
                       val msg: String = "",
                       @SerializedName("code")
                       val code: Int = 0,
                       @SerializedName("data")
                       val data: InternectsData)