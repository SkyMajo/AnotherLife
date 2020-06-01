package com.nixo.afterworklife.Utils.NetWork

import okhttp3.*

import java.io.IOException

class CreateIntercepter : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var proceed = chain.proceed(chain.request())
        return proceed
    }


    inner class CreateInterceptorExceptioin : Error() {
        var errorCode: Int = 0
        var retry_after: String? = null
    }

}
