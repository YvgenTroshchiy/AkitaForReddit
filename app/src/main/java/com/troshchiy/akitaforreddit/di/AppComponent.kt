package com.troshchiy.akitaforreddit.di

import android.content.Context
import com.troshchiy.akitaforreddit.ui.topnews.TopNewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(topNewsFragment: TopNewsFragment)

}
