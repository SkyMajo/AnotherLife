package com.nixo.afterworklife.ActivitePage.DATA.AInfoData

import com.nixo.afterworklife.MainPage.Data.MessageInfo.User

data class Data(val address: String = "",
                val description: String = "",
                val createdAt: String = "",
                val title: String = "",
                val content: String = "",
                val startTime: String = "",
                val realAmount: String = "",
                val updatedAt: String = "",
                val groupId: Int = 0,
                val members: Int = 0,
                val logo: String = "",
                val id: Int = 0,
                val chargeAmount: String = "",
                val status: Int = 0,
                val group: Group,
                val is_signup:Boolean,
                val info:info,
                val user:User
)

data class info(
    val id: Int,
    val user_id:Int,
    val phone:String,
    val realname:String,
    val active_id:Int,
    val created_at:String,
    val updated_at:String
)