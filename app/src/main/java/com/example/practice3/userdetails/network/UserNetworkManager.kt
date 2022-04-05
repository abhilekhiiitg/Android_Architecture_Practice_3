package com.example.practice3.userdetails.network

import com.example.practice3.userdetails.model.User
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class UserNetworkManager @Inject constructor(
    private val githubApiService: IGithubApiService
) : IUserNetworkManager {
    override fun fetchUserInfoById(id: Int): Single<Response<User>> {
        return githubApiService.getUser(id)
    }
}