package com.nixo.afterworklife.Welcome.Moudle
import com.google.gson.annotations.SerializedName
import com.nixo.afterworklife.MainPage.Data.Group
import java.util.*

//DynamicData
data class FlowDynamicsData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: MutableList<DynamicData>,
    @SerializedName("msg")
    val msg: String
)

data class DynamicData(
    @SerializedName("address")
    val address: String,
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("is_like")
    val is_like: Boolean = false,
    @SerializedName("likes")
    var likes: Int,
    @SerializedName("relation_group")
    val relation_group: Group,
    @SerializedName("relation_group_id")
    val relation_group_id: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("video")
    val video: String,
    val first_frame:String
)

data class User(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sex")
    val sex: Int,
    @SerializedName("username")
    val username: String
)