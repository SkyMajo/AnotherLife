package com.nixo.afterworklife.UserPage.Moudle

import com.google.gson.annotations.SerializedName

data class FansItem(@SerializedName("sex")
                    val sex: Int = 0,
                    @SerializedName("avatar")
                    val avatar: String = "",
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("username")
                    val username: String = "")