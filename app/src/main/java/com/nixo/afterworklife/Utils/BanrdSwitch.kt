package com.yqjr.superviseapp.utils.publicUse.bankSwitch

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Search.SearchActivity

class BanrdSwitch{

    class Builder(context: Context){
        val dialog :SodaDialog = SodaDialog(context, R.style.NewDialog)
        private var ivState : ImageView? = null
        private var tvTitle : TextView? = null

        fun setSwitchImg(ivState : ImageView?):Builder{
            this.ivState = ivState
            dialog.setSwitchImg(ivState)
            return this
        }
        fun setSwitchTitle(tvTitle : TextView?):Builder{
            this.tvTitle = tvTitle
            dialog.setSwitchTitle(tvTitle)
            return this
        }



        fun build():SodaDialog = dialog
        fun setInfs(infs :SodaDialog.menuInterface): Builder {
            dialog.setInfsMenu(infs)
            return this
        }

    }


}