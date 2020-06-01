package com.nixo.afterworklife.MainPage.Data.GroupData

data class LocationDataItem(val distence: Double = 0.0,
                            val lng: String = "",
                            val max_member: Int = 0,
                            val description: String = "",
                            val createdAt: String = "",
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
                            val holder:holder

)


data class holder(
    var id: Int,
    var username:String,
    var avatar : String

)