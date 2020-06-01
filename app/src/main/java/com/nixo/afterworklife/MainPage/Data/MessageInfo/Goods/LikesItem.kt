package com.nixo.afterworklife.MainPage.Data.MessageInfo.Goods

data class LikesItem(val isRead: Int = 0,
                     val updatedAt: String = "",
                     val explore: Explore,
                     val exploreId: Int = 0,
                     val userId: Int = 0,
                     val createdAt: String = "",
                     val user: User)