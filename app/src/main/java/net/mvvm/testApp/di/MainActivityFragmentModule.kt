package net.mvvm.testApp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.mvvm.testApp.screen.home.HomeFragment


@Suppress("unused")
@Module
abstract class MainActivityFragmentModule{


    @ContributesAndroidInjector
    abstract fun contributeHomeFragment() : HomeFragment


}