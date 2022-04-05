package com.example.practice3.userdetails.contract

import com.example.practice3.base.contract.BasePresenter
import com.example.practice3.base.contract.BaseState
import com.example.practice3.base.contract.BaseView
import com.example.practice3.userdetails.model.User

interface IUserView : BaseView {
    fun showUserInfo(userInfo: User?)
}

sealed class UserState : BaseState {
    data class UserInfoFetchState(
        val isLoading: Boolean = false,
        var data: User? = null,
        var error: Throwable? = null,
        var isDataLoaded: Boolean = false
    ) : UserState()
}

interface IUserPresenter : BasePresenter<IUserView, UserState> {
    fun getUserInfo(id: Int)
    fun clearResources()
}

