package com.rezkalla.blacklane.di


import com.rezkalla.blacklane.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [RepositoryModule::class])
    abstract fun bindMainActivity(): MainActivity
}