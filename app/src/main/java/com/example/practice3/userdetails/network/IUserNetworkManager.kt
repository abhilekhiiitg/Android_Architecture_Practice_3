package com.example.practice3.userdetails.network

import com.example.practice3.userdetails.model.User
import io.reactivex.Single
import retrofit2.Response

interface IUserNetworkManager {
    fun fetchUserInfoById(id:Int): Single<Response<User>>
}
