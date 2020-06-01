package com.nixo.afterworklife.Utils.Data


import com.nixo.afterworklife.Common.APP
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.pref(default: T,key:String) = Preference(
        APP._context!!,key, default, R::class
        .jvmName)