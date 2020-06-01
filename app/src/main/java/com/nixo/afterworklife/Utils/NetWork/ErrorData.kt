package com.nixo.afterworklife.Utils.NetWork
import com.google.gson.annotations.SerializedName
import com.nixo.afterworklife.Utils.PoKo

@PoKo
data class ErrorData(
    @SerializedName("errors")
    var errors: ErrorsData,
    @SerializedName("message")
    val message: String
)

@PoKo
data class ErrorsData(
    @SerializedName("password")
    val password: List<String>,
    @SerializedName("password_again")
    val passwordAgain: List<String>,
    @SerializedName("username")
    val username: List<String>
)