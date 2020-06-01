package com.nixo.afterworklife.MainPage.Data
import com.google.gson.annotations.SerializedName


data class DynamicsData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String
)

data class DynamicsDatainfo(
    @SerializedName("address")
    val address: String,
    @SerializedName("comment_peoples")
    val commentPeoples: List<CommentPeople>,
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
    @SerializedName("like_peoples")
    val likePeoples: List<LikePeople>,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("relation_group")
    val relationGroup: Group?,
    @SerializedName("relation_group_id")
    val relationGroupId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: UserXXX,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("video")
    val video: String
)

data class CommentPeople(
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val created_at:String,
    @SerializedName("explore_id")
    val exploreId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("reply_id")
    val replyId: Int,
    @SerializedName("replys")
    val replys: Replys?,
    @SerializedName("user")
    val user: UserX,
    @SerializedName("user_id")
    val userId: Int
)

data class Replys(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user")
    val user: DynamicUser,
    @SerializedName("user_id")
    val userId: Int
)

data class DynamicUser(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sex")
    val sex: Int,
    @SerializedName("username")
    val username: String
)

data class UserX(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String
)

data class LikePeople(
    @SerializedName("explore_id")
    val exploreId: Int,
    @SerializedName("user")
    val user: UserXX,
    @SerializedName("user_id")
    val userId: Int
)

data class UserXX(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String
)

data class UserXXX(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sex")
    val sex: Int,
    @SerializedName("username")
    val username: String
)