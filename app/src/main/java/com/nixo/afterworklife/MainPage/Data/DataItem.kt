package com.nixo.afterworklife.MainPage.Data

data class DataItem(val user_id: Int = 0,
                    val createdAt: String = "",
                    val id: Int = 0,
                    val users: Users,
                    val fansId: Int = 0,
                    val is_follow: Boolean)