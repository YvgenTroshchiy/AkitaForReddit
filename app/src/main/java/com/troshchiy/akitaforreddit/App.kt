package com.troshchiy.akitaforreddit

import android.app.Application
import android.content.Context
import com.troshchiy.akitaforreddit.di.AppComponent
import com.troshchiy.akitaforreddit.di.DaggerAppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }

class App : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}
