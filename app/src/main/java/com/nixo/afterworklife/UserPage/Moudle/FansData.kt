package com.nixo.afterworklife.UserPage.Moudle

import com.google.gson.annotations.SerializedName

data class FansData(@SerializedName("msg")
                    val msg: String = "",
                    @SerializedName("code")
                    val code: Int = 0,
                    @SerializedName("data")
                    val data: MutableList<DataItem>?)