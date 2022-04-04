package com.example.practice3.view.di.component

import com.example.practice3.view.di.PerActivity
import com.example.practice3.view.di.module.ActivityModule
import dagger.Component

@PerActivity
@Component(modules = [ActivityModule::class])
interface ActivityComponent
