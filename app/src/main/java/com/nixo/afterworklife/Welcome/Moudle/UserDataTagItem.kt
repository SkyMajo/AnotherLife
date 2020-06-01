package com.nixo.afterworklife.Welcome.Moudle

import com.google.gson.annotations.SerializedName

data class UserDataTagItem(@SerializedName("user_id")
                    val userId: Int = 0,
                           @SerializedName("tag_id")
                    val tagId: Int = 0,
                           @SerializedName("id")
                    val id: Int = 0,
                           @SerializedName("tags")
                    val tags: Tags)