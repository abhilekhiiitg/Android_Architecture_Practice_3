package com.example.practice3.userdetails.di.module


import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.example.practice3.userdetails.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Application = this.application
}
