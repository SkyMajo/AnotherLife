package com.nixo.afterworklife.Utils.Ext

import android.view.View

inline fun<T : View> T.click(crossinline block: (T) -> Unit)=setOnClickListener { block(this) }