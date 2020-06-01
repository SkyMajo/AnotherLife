package com.nixo.afterworklife.MainPage.Data.MessageInfo

data class DataItem(val reason: String = "",
                    val role: Int = 0,
                    val updatedAt: String = "",
                    val groupId: Int = 0,
                    val userId: Int = 0,
                    val createdAt: String = "",
                    val id: Int = 0,
                    val users: Users,
                    val status: Int = 0,
                    val count: Int = 0,
                    val group: Group)