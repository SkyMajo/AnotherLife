package com.nixo.afterworklife.MainPage.Data.MessageInfo

data class LikesItem(val isRead: Int = 0,
                     val updatedAt: String = "",
                     val explore: Explore,
                     val explore_id: Int,
                     val userId: Int = 0,
                     val createdAt: String = "",
                     val user: User)