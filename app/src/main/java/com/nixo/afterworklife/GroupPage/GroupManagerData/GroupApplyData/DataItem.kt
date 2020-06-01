package com.nixo.afterworklife.GroupPage.GroupManagerData.GroupApplyData

data class DataItem(val reason: String = "",
                    val role: Int = 0,
                    val updatedAt: String = "",
                    val groupId: Int = 0,
                    val userId: Int = 0,
                    val createdAt: String = "",
                    val id: Int = 0,
                    val users: Users,
                    val status: Int = 0)