package com.nixo.afterworklife.MainPage.Data
import com.google.gson.annotations.SerializedName


data class InfoNumMoudle(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: InfoNumData,
    @SerializedName("msg")
    val msg: String
)

data class InfoNumData(
    @SerializedName("explores")
    val explores: Int,
    @SerializedName("fans")
    val fans: Int,
    @SerializedName("follows")
    val follows: Int
)