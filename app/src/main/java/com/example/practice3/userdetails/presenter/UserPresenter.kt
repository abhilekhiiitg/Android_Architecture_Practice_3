package com.example.practice3.userdetails.presenter

import android.util.Log
import com.example.practice3.userdetails.contract.IUserPresenter
import com.example.practice3.userdetails.contract.IUserView
import com.example.practice3.userdetails.contract.UserState
import com.example.practice3.userdetails.contract.UserState.UserInfoFetchState
import com.example.practice3.userdetails.repository.IUserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.properties.Delegates

class UserPresenter @Inject constructor(
    private val userRepository: IUserRepository
) : IUserPresenter {
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


    override fun getUserInfo(id: Int) {
        if (userInfoFetchState.isLoading) {
            return
        }
        userInfoFetchState = userInfoFetchState.copy(isLoading = true, isDataLoaded = false)
        disposable.add(
            userRepository.getUserInfo(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    Log.d("Abhilekh","success api - $it")
                    userInfoFetchState = userInfoFetchState.copy(isLoading = false, isDataLoaded = true, data = it.body())
                },
                {
                    Log.d("Abhilekh","error in  api - $it")
                    userInfoFetchState = userInfoFetchState.copy(isLoading = false, isDataLoaded = true, error = it)
                })
        )
    }

    override fun clearResources() {
        disposable.clear()
    }
}
