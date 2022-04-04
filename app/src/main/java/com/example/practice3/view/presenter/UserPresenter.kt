package com.example.practice3.view.presenter

import android.util.Log
import com.example.practice3.view.contract.IUserView
import com.example.practice3.view.model.User
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserPresenter @Inject constructor() {

    private lateinit var userView: IUserView
    private val disposable = CompositeDisposable()



    fun injectView(view: IUserView) {
        this.userView = view
    }

    fun getUserInfo(username: String) {
        userView.loading()
        Log.d("Abhilekh","inside getUserInfo presenter : $username")
        userView.showUserInfo(User(username = username, imageUrl = "https://picsum.photos/200/300?random=1", repoUrl = "",
        id = 1))
    }


    fun onDestroy() {
        disposable.clear()
    }
}
