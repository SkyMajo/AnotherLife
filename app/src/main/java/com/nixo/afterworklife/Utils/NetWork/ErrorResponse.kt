package com.nixo.afterworklife.Utils.NetWork

import com.google.gson.annotations.SerializedName

data class ErrorResponse(@SerializedName("message")
                         val message: String = "",
                         @SerializedName("errors")
                         val errors: Errors)