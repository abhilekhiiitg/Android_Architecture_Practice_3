package com.example.practice3.view.contract

import com.example.practice3.view.model.User
import com.example.practice3.view.presenter.BaseView


interface IUserView : BaseView {
    fun showUserInfo(userInfo: User)

    fun showUserInfoError(message: String?)

    fun hideKeyboard()
}
/*

interface IUserPresenter : BasePresenter<IUserView, UserState> {

    fun clearResources()
}

sealed class UserState : BaseState*/
