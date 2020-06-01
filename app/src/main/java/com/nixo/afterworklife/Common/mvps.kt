package com.nixo.afterworklife.Common

interface IPresenter<out View : IView<IPresenter<View>>> : ILifecycler {
    val view : View
}


interface IView<out Presenter : IPresenter<IView<Presenter>>> : ILifecycler {
    val presenter : Presenter
}