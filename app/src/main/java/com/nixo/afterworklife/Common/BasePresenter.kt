package com.nixo.afterworklife.Common

import android.content.res.Configuration
import android.os.Bundle


abstract class BasePresenter<out V: IView<BasePresenter<V>>>: IPresenter<V> {


    /**
     *     lateinit -》延迟初始化，因为Activity和Fragment都是系统初始化的
     *     Activity和Fragment这里统称为View
     *     View初始化的时候都会执行系统默认的无参构造方法，所以只能我们人为的去Set进去，
     *     所以Presenter所持有的View并不是在Presenter初始化的时候就会初始化
     *     所以我们初始化Presenter的时候使用了延迟加载初始化lateinit 等待View初始化完成并且set进来
     *     才会初始化我们的View
     *     因为我们的view是协变的(out)，但是我们这里写的view是一个属性值，属性值自带get/set方法，所以
     *     在类型V这里加上@UnsafeVariance 让编译器默认知道我们会自己处理好这个泛型，跳过报错。
     */
    override lateinit var view : @UnsafeVariance V

    override fun onCreate(savedInstanceState: Bundle?) = Unit
    override fun onSaveInstanceState(outState: Bundle) = Unit
    override fun onViewStateResotre(saveInstanceState: Bundle?) = Unit
    override fun onConfigurationChanged(newConfig: Configuration) = Unit
    override fun onResume() = Unit
    override fun onStop() = Unit
    public override fun onDestory() = Unit
    override fun onStart() = Unit
    override fun onPause() = Unit

}