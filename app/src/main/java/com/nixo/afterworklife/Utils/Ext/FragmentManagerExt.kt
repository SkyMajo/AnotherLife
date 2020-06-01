package com.nixo.afterworklife.Utils.Ext

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline  fun FragmentManager.inT(func : FragmentTransaction.() -> Unit){
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
//    fragmentTransaction.commitAllowingStateLoss()
    fragmentTransaction.commit()
}