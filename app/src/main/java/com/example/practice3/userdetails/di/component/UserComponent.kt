/*
 * Copyright (c) 2021 Think and Learn Pvt Ltd. All rights reserved.
 */

package com.example.practice3.userdetails.di.component

import com.example.practice3.userdetails.activity.MainActivity
import com.example.practice3.userdetails.di.module.UserDataModule
import com.example.practice3.userdetails.repository.IUserRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UserDataModule::class])
interface UserComponent {
    fun userRepository(): IUserRepository
}