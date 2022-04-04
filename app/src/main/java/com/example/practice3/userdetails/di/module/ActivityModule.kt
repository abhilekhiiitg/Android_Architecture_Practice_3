package com.example.practice3.userdetails.di.module

import android.app.Activity
import android.content.Context
import com.example.practice3.userdetails.di.ActivityContext
import com.example.practice3.userdetails.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @PerActivity
    @Provides
    @ActivityContext
    fun provideContext(): Context = activity
}
