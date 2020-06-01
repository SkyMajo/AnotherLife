package com.nixo.afterworklife.Utils

import android.util.Log
import com.jerey.loglib.Klog
import com.nixo.afterworklife.Common.APP
import com.nixo.afterworklife.Utils.Data.SharedUtils
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //拿到我们的request
        val original = chain.request()
        /*然后返回一个Response，使用proceed我们拿到一个response，  而proceed的参数是一个request，我们可以在参数中修改这个请求
         *然后再次发送，这也就是拦截器的作用
         * 然后我们Base64塞进去请求头
         *
         * 如果已经登录了，我们直接塞Token,通过SharedPreference取就可以
         * 如果没登录，还不是含有鉴权模式的，我们去掉请求头即可，然后重新构建并发送
         */
        return chain.proceed(original.newBuilder()
            .apply {
                //添加到请求头里
//                Klog.e("Nixo--->Authorization","--->${SharedUtils(APP._context!!).getString("token")}")
                header("token",SharedUtils.getString("token"))
//                header("token","eyJpdiI6IjFyakhLUk5BaFBiaGdYNEowdmNudWc9PSIsInZhbHVlIjoiN0hSZ2h0bThmWDY2NVp0ZmExZlJuQkZzSVR3ZEVJU3JiMWhTYXBOODA1ZjNYNG1kQjhNSmtlVzRXME1UTFdlSmdSWG1RalNMOTlUMUR0NkJJcm5relRlOTFCVVcwSkU0d011bXhOSVhJMU4rRVV4VjRHcnI4Wmphb1hGelJzdEgrU2FZRWRucndZYitjdnpsSGtJRk1QUmtRUHRcL2szTDVwTkh6N3p5RTdYcm81SWczbXJRK3E3SXlpcklBb0IzN0NsSm1NUHkyWE1mRXRUVzZ4QXdOd1UwSERcLzJyRUtueittQUhGYXR2cnpCZDZRemNoT1plSnZld3Rqekh5UmV5MW11RmpYZkxBcTVsNlp4Qk1VdkJaZFdqV2JWN29BaVVPOVRcLys4bFZnM0RiUWFKRXhwU2VnRVwvcVJSMzBnN3VncDVHenVTQ29xVHloKzlyOGRUZFdBWDJKRjFiQnJuelFGb0pcL3cybWlvK3ZGeXFPdzFMNTR0d09xSmY2dEc0bTY5ZEk3OW80UDlBcjhNK2JRd2dCUXdnPT0iLCJtYWMiOiI3NjQ1ZjkxNTA3Zjc3MWEyNTE4NjE0OTM5NzBhOWVmNDBjMmIzN2Y0NTNjNzBhMGI5YTJkYzFmMzg4YzZjM2E3In0")
                header("X-Requested-With","XMLHttpRequest")
                header("Content-Type","application/x-www-form-urlencoded")
            }
            .build())
    }
}