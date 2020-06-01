package com.yqjr.superviseapp.utils.publicUse.bankSwitch

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.BaseDialog
import com.nixo.afterworklife.Utils.Ext.click

class SodaDialog(context: Context, themeResId: Int) : BaseDialog(context, themeResId) {


    private var ivState: ImageView? = null
    private var tvTitle: TextView? = null

    interface menuInterface{
        fun onSelect(str: String)
    }

    private var infsMenu : menuInterface? = null

    public fun setInfsMenu(infs:menuInterface){
        this.infsMenu = infs
    }

    init {
        initView()
    }

    override
    fun getWindow(): Window? {
        return super.getWindow()
    }

    fun setSwitchImg(ivState: ImageView?) {
        this.ivState = ivState
    }

    fun setSwitchTitle(tvTitle: TextView?) {
        this.tvTitle = tvTitle
    }

    @SuppressLint("ObjectAnimatorBinding")
    override fun show() {
        if (ivState != null) {
//            ivState!!.setImageResource(R.mipmap.arrow_up_unfold)
            var mAnimartor = ObjectAnimator.ofFloat(ivState, "rotation", 360f, 180f)
            mAnimartor.duration = 300
            mAnimartor.start()
        }
        super.show()
    }

    @SuppressLint("ObjectAnimatorBinding")
    override fun dismiss() {
        super.dismiss()
        if (ivState != null) {
//            ivState!!.setImageResource(R.mipmap.arrow_down_fold)
            var mAnimartor = ObjectAnimator.ofFloat(ivState, "rotation", 180f, 360f)
            mAnimartor.duration = 300
            mAnimartor.start()
        }
    }

    interface clickInfs{
        fun onClickMenu(str: String)
    }

    var infs : clickInfs? = null
    fun setClickInfs(clickInfs:clickInfs):SodaDialog{
        infs = clickInfs
        return this
    }

    private fun initView() {
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_dealer, null)
        var llCancel = view.findViewById<LinearLayout>(R.id.cancel)
        var ll_dymic = view.findViewById<LinearLayout>(R.id.ll_dymic)
        var ll_user = view.findViewById<LinearLayout>(R.id.ll_user)
        var ll_group = view.findViewById<LinearLayout>(R.id.ll_group)
        var ll_activie = view.findViewById<LinearLayout>(R.id.ll_activie)

        ll_dymic.click {
            infsMenu?.onSelect("动态")
            dismiss()
        }


        ll_user.click {
            infsMenu?.onSelect("用户")
            dismiss()
        }

        ll_group.click {
            infsMenu?.onSelect("社团")
            dismiss()
        }

        ll_activie.click {
            infsMenu?.onSelect("活动")
            dismiss()
        }



        llCancel.click {
            dismiss()
        }
        setContentView(view)
        setCancelable(false)
        window!!.setGravity(Gravity.TOP)


    }


}