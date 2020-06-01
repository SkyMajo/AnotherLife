package com.nixo.afterworklife.Common

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.os.Handler
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.nixo.afterworklife.Utils.Data.SharedUtils

private  var INSTANCE:Application = APP()

class APP:  MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        _context = this
        _handler = Handler()

    }

    fun getContext(): Context {
        return applicationContext
    }

    companion object {
        var _context: Application? = null
        var _handler: Handler? = null


        private fun getContext(): Context {
            return _context!!
        }

        fun getWidth(): Int {
            return getContext().resources.displayMetrics.widthPixels
        }

        fun getHeight(): Int {
            return getContext().resources.displayMetrics.heightPixels
        }

        fun getHandler(): Handler {
            return _handler!!
        }


    }
}





