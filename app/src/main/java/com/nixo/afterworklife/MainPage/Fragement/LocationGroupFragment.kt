package com.nixo.afterworklife.MainPage.Fragement

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.MainPage.Adapter.SimplerAdapter
import com.nixo.afterworklife.MainPage.Present.FindPresent
import com.nixo.afterworklife.R
import kotlinx.android.synthetic.main.fragment_group.*

class LocationGroupFragment : BaseFragment<FindPresent>() {

    var array = ArrayList<String>().also {
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
        it.add("")
    }


    override fun setLayoutParame(): Int = R.layout.fragment_location_group

    override fun onState() {
        rv_location.layoutManager = LinearLayoutManager(activity).also {
            it.orientation = LinearLayoutManager.VERTICAL
        }
        rv_location.adapter = SimplerAdapter(activity!!,R.layout.item_find,array)
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}

}