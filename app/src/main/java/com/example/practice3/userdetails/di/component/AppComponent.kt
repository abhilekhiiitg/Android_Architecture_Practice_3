package com.example.practice3.userdetails.di.component

import com.example.practice3.userdetails.activity.MainActivity
import com.example.practice3.userdetails.di.module.ApplicationModule
import com.example.practice3.userdetails.di.module.UserDataModule
import com.example.practice3.userdetails.fragment.UserFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, UserDataModule::class])
interface AppComponent {
    fun inject(fragment: UserFragment)

    fun inject(activity: MainActivity)
}