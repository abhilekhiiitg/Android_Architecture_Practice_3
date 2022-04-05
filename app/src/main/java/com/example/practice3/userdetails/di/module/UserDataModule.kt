package com.example.practice3.userdetails.di.module

import com.example.practice3.userdetails.network.IGithubApiService
import com.example.practice3.userdetails.network.IUserNetworkManager
import com.example.practice3.userdetails.network.UserNetworkManager
import com.example.practice3.userdetails.repository.IUserRepository
import com.example.practice3.userdetails.repository.UserRepository
import com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class UserDataModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        userNetworkManager: IUserNetworkManager
    ): IUserRepository {
        return UserRepository(userNetworkManager)
    }

    @Provides
    @Singleton
    internal fun providesUserNetworkManager(
        githubApiService: IGithubApiService
    ): IUserNetworkManager {
        return UserNetworkManager(githubApiService)
    }

    @Provides
    @Singleton
    fun providesGithubApiService(retrofit: Retrofit): IGithubApiService {
        return retrofit.create(IGithubApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://gorest.co.in/public/v2/users/")
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }
}
