package com.nixo.afterworklife.Welcome.Moudle

import com.google.gson.annotations.SerializedName

data class LikesMoudle(@SerializedName("msg")
                       val msg: String = "",
                       @SerializedName("code")
                       val code: Int = 0,
                       @SerializedName("data")
                       val data: List<UserDataTagItem>?)