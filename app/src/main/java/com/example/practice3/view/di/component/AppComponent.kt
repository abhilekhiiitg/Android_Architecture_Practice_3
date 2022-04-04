package com.example.practice3.view.di.component

import com.example.practice3.view.activity.MainActivity
import com.example.practice3.view.di.module.ApplicationModule
import com.example.practice3.view.fragment.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment)

    fun inject(activity: MainActivity)

}