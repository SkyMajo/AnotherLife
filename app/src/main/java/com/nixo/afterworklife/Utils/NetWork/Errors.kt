package com.nixo.afterworklife.Utils.NetWork

import com.google.gson.annotations.SerializedName

data class Errors(@SerializedName("password")
                  val password: List<String>?,
                  @SerializedName("password_again")
                  val passwordAgain: List<String>?,
                  @SerializedName("username")
                  val username: List<String>?)