package com.example.practice3.userdetails.repository

import com.example.practice3.userdetails.model.User
import com.example.practice3.userdetails.network.IUserNetworkManager
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val  userNetworkManager: IUserNetworkManager
) : IUserRepository {
    override fun getUserInfo(id: Int): Single<Response<User>> {
        return userNetworkManager.fetchUserInfoById(id)
    }
}