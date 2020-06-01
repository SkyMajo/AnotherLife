package com.nixo.afterworklife.MainPage.Data.shenqing

data class SQDataItem(val images: String = "",
                    val comments: Int = 0,
                    val address: String = "",
                    val createdAt: String = "",
                    val video: String = "",
                    val relationGroupId: Int = 0,
                    val content: String = "",
                    val updatedAt: String = "",
                    val userId: Int = 0,
                    val isLike: Boolean = false,
                    val id: Int = 0,
                    val user: User,
                    val likes: Int = 0)