package com.example.practice3.view

import android.app.Application
import com.example.practice3.view.di.component.AppComponent
import com.example.practice3.view.di.component.DaggerAppComponent
import com.example.practice3.view.di.module.ApplicationModule

open class MainApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        createComponent()
    }

    protected open fun createComponent() {
        component = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}

