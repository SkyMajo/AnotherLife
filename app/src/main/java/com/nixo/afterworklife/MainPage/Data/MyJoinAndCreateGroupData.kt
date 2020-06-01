package com.nixo.afterworklife.MainPage.Data

import com.google.gson.annotations.SerializedName

data class MyCreateGroupData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: MutableList<Data>,
    @SerializedName("msg")
    val msg: String
)

data class Data(
    @SerializedName("announcement")
    val announcement: String,
    val distence:String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("holder_id")
    val holderId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("max_member")
    val maxMember: Int,
    @SerializedName("members")
    val members: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)