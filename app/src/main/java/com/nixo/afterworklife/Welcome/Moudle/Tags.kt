package com.nixo.afterworklife.Welcome.Moudle

import com.google.gson.annotations.SerializedName

data class Tags(@SerializedName("updated_at")
                val updatedAt: String = "",
                @SerializedName("created_at")
                val createdAt: String = "",
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("tag")
                val tag: String = "")