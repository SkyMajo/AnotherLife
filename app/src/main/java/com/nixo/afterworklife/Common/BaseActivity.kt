package com.nixo.afterworklife.Common

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.Window
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.gyf.immersionbar.ImmersionBar
import com.lzy.ninegrid.NineGridView
import com.nixo.afterworklife.Utils.Data.SharedUtils
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure
import com.yuyh.library.imgsel.ISNav
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.yuyh.library.imgsel.common.ImageLoader


abstract class BaseActivity<out P : BasePresenter<BaseActivity<P>>> : IView<P>, AppCompatActivity() {
    override val presenter : P

    init {
        presenter = createPresenterKt()
        presenter.view = this
    }

    private fun createPresenterKt(): P {
        sequence {
            var thisClass: KClass<*> = this@BaseActivity::class
            while (true) {
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap {
            it.flatMap { it.arguments }.asSequence()
        }.first {
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }
    }

    abstract fun onLayout () : Int
    abstract fun onState () :Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState?.putParcelable("android:support:fragments", null)
        super.onCreate(savedInstanceState)
        // 允许使用transitions
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        window.enterTransition = Fade()
        window.exitTransition = Fade()
//        window.enterTransition = Slide().setDuration(500)
//        window.exitTransition = Slide().setDuration(500)
//        Sofia.with(this)

//        val contentLayout = window.decorView.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT)
//        contentLayout.setBackgroundColor( ContextCompat.getColor(this, R.color.colorPrimary))
//            .statusBarDarkFont()//状态栏浅色字体
////            .invasionStatusBar()//内容入侵状态栏
//            .navigationBarBackground(ContextCompat.getColor(this, R.color.colorPrimary))//导航栏背景色
//            .statusBarBackground(ContextCompat.getColor(this, R.color.colorPrimary));//状态栏背景色
        presenter.onCreate(savedInstanceState)
        setContentView(onLayout())
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
//            .transparentNavigationBar()
//            .barColor(R.color.color_white)
            .statusBarDarkFont(true)
            .init()
        SharedUtils.setContext(this)
        class GlideImageLoader : NineGridView.ImageLoader {

            override fun onDisplayImage(context: Context, imageView: ImageView, url: String) {
                Glide.with(context).load(url)//
                    .placeholder(com.nixo.afterworklife.R.mipmap.loading)//
                    .error(com.nixo.afterworklife.R.drawable.ic_default_image)//
                    .into(imageView)
            }

            override fun getCacheImage(url: String): Bitmap? {
                return null
            }
        }
        // 自定义图片加载器
        ISNav.getInstance().init { context, path, imageView -> Glide.with(context).load(path).into(imageView) }
        NineGridView.setImageLoader(GlideImageLoader())
        AppManager.appManager!!.addActivity(this)
        onState()
    }



    fun Action(activity : Class<*>){
        var intent = Intent(baseContext,activity)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    fun paramerAction(activity: Class<*>,bundle: Bundle){
        var intent = Intent()
        intent.setClass(this@BaseActivity,activity)
        intent.putExtras(bundle)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())

    }


    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        presenter.onDestory()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.onViewStateResotre(savedInstanceState)
    }

}
