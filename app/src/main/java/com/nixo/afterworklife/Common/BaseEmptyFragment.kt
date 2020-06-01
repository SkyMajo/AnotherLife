package com.nixo.afterworklife.Common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.util.zip.Inflater

abstract class BaseEmptyFragment :Fragment(){


    abstract fun setLayoutParame():Int;
    abstract fun initView();


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View? = null
        if(view == null){
            view = inflater.inflate(setLayoutParame(),container,false)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}