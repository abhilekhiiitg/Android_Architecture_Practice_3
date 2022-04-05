package com.example.practice3.userdetails.repository

import com.example.practice3.userdetails.model.User
import io.reactivex.Single
import retrofit2.Response

interface IUserRepository {
    fun getUserInfo(id: Int): Single<Response<User>>
}
