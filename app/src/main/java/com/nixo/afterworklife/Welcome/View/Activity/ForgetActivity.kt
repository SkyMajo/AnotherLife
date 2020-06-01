package com.nixo.afterworklife.Welcome.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Welcome.Present.ForgetPresent

class ForgetActivity : BaseActivity<ForgetPresent>() {
    override fun onLayout(): Int = R.layout.activity_forget

    override fun onState() {}

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}


}
