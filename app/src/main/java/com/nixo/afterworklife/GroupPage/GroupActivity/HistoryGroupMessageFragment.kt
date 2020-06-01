package com.nixo.afterworklife.GroupPage.GroupActivity

import android.os.Bundle
import android.view.View
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.GroupPage.GroupPresent.HistoryGroupMessagePresent
import com.nixo.afterworklife.R
import kotlinx.android.synthetic.main.include_no_content_big.*

class HistoryGroupMessageFragment :BaseFragment<HistoryGroupMessagePresent>(){
    override fun setLayoutParame(): Int = R.layout.fragment_history

    override fun onState() {
        ll_no_content.visibility = View.VISIBLE
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}

}