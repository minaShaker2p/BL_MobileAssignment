package com.rezkalla.blacklane.di

import android.app.Application
import com.rezkalla.blacklane.BlackLaneApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    fun inject(app: BlackLaneApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}