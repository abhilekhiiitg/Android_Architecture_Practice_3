package com.example.practice3

import android.app.Application
import com.example.practice3.userdetails.di.component.AppComponent
import com.example.practice3.userdetails.di.component.DaggerAppComponent
import com.example.practice3.userdetails.di.component.UserComponent
import com.example.practice3.userdetails.di.module.ApplicationModule
import com.example.practice3.userdetails.di.module.UserDataModule

open class MainApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        createComponent()
    }

    protected open fun createComponent() {
        component = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .userDataModule(UserDataModule())
            .build()
    }

}

