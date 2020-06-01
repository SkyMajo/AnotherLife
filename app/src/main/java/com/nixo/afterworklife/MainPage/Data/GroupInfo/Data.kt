package com.nixo.afterworklife.MainPage.Data.GroupInfo

import com.nixo.afterworklife.GroupPage.GroupManagerData.NewActivity

data class Data(val lng: String = "",
                val max_member: Int = 0,
                val description: String = "",
                val createdAt: String = "",
                val holder: Holder,
                val avatar: String = "",
                val updatedAt: String = "",
                val holderId: Int = 0,
                val members: Int = 0,
                val name: String = "",
                val logo: String = "",
                val id: Int = 0,
                val lat: String = "",
                val announcement: String = "",
                val status: Int = 0,
                val new_activity:NewActivity

)