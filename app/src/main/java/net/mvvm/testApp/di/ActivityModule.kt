package net.mvvm.testApp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.mvvm.testApp.screen.home.HomeActivity


@Suppress("unused")
@Module
abstract class ActivityModule{

    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    internal abstract fun contributeMainActivity() : HomeActivity

}