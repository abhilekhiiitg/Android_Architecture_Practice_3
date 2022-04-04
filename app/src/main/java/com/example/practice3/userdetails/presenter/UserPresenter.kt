package com.example.practice3.userdetails.presenter

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.practice3.base.contract.BaseState
import com.example.practice3.userdetails.contract.IUserPresenter
import com.example.practice3.userdetails.contract.IUserView
import com.example.practice3.userdetails.contract.UserState
import com.example.practice3.userdetails.contract.UserState.UserInfoFetchState
import com.example.practice3.userdetails.model.User
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import kotlin.properties.Delegates

class UserPresenter @Inject constructor() : IUserPresenter {
    override var view: IUserView? = null
    override val disposable: CompositeDisposable = CompositeDisposable()

    override fun updateView(state: UserState) {
        when (state) {
            is UserInfoFetchState -> {
                when {
                    state.isDataLoaded -> view?.showUserInfo(state.data)
                }
            }
        }
    }

    private var userInfoFetchState: UserInfoFetchState by Delegates.observable(
        UserInfoFetchState()
    ) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            updateView(newValue)
        }
    }

    override fun getUserInfo(username: String) {
        view?.loading()
        Log.d("Abhilekh", "inside getUserInfo presenter : $username")
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val user = User(username = username, imageUrl = "https://picsum.photos/200/300?random=1", repoUrl = "", id = 1)
                userInfoFetchState = userInfoFetchState.copy(isDataLoaded = true, data = user, isLoading = false)
            },
            2000
        )
    }

    override fun clearResources() {
        disposable.clear()
    }
}
