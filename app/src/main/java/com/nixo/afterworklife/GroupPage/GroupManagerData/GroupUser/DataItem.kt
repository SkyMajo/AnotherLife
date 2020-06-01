package com.nixo.afterworklife.GroupPage.GroupManagerData.GroupUser

data class DataItem(val role: Int = 0,
                    val groupId: Int = 0,
                    val userId: Int = 0,
                    val createdAt: String = "",
                    val id: Int = 0,
                    val is_follow:Boolean = false,
                    val users: Users)