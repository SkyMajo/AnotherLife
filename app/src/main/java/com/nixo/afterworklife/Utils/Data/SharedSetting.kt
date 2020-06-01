package com.nixo.afterworklife.Utils.Data

import com.nixo.afterworklife.Common.APP


object SharedSetting{
    var account : String by Preference(APP._context!!,"account","")
    var passWord : String by Preference(APP._context!!,"password","")

}