package com.example.practice3.base.contract

import io.reactivex.disposables.CompositeDisposable

interface BaseView  {
    fun loading()
    fun dismissLoading()
}

interface BaseState

interface BasePresenter <V : BaseView, S : BaseState>{
    var view: V?
    val disposable: CompositeDisposable

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun updateView(state: S)
}