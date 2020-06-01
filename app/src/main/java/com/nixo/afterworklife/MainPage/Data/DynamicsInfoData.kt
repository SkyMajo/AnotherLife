package com.nixo.afterworklife.MainPage.Data
import com.google.gson.annotations.SerializedName


data class DynamicsInfoData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: DynamicsInfoDataData,
    @SerializedName("msg")
    val msg: String
)

data class DynamicsInfoDataData(
    @SerializedName("is_follow")
    val isFollow: Boolean,
    @SerializedName("address")
    val address: String,
    @SerializedName("comment_peoples")
    val commentPeoples: MutableList<CommentPeople>,
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
    val likePeoples: MutableList<LikePeople>,
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

data class Group(
    var id: Int,
    var name:String,
    var avatar: String
)

data class DynamicsInfoDataCommentPeople(
    @SerializedName("content")
    val content: String,
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

data class DynamicsInfoDataReplys(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user")
    val user: DynamicsInfoDataUser,
    @SerializedName("user_id")
    val userId: Int
)

data class DynamicsInfoDataUser(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sex")
    val sex: Int,
    @SerializedName("username")
    val username: String
)

data class DynamicsInfoDataUserX(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String
)

data class DynamicsInfoDataLikePeople(
    @SerializedName("explore_id")
    val exploreId: Int,
    @SerializedName("user")
    val user: UserXX,
    @SerializedName("user_id")
    val userId: Int
)

data class DynamicsInfoDataUserXX(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String
)

data class DynamicsInfoDataUserXXX(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sex")
    val sex: Int,
    @SerializedName("username")
    val username: String
)