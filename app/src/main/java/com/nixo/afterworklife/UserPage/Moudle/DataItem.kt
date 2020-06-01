package com.nixo.afterworklife.UserPage.Moudle

import com.google.gson.annotations.SerializedName

data class DataItem(@SerializedName("user_id")
                    val userId: Int = 0,
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("fans_id")
                    val fansId: Int = 0,
                    @SerializedName("is_follow")
                    val is_follow:Boolean = false,
                    @SerializedName("fans")
                    val fans: MutableList<FansItem>?)