package com.nixo.afterworklife.Common

import android.content.res.Configuration
import android.os.Bundle

interface ILifecycler{

    fun onCreate(savedInstanceState: Bundle?)

//    public fun onSaveInstanceState(outState : Bundle?)

    fun onSaveInstanceState(outState : Bundle)

    fun onViewStateResotre(saveInstanceState : Bundle?)

    fun onConfigurationChanged(newConfig: Configuration)

    fun onResume()

    fun onStop()

    fun onDestory()

    fun onStart()

    fun onPause()



}