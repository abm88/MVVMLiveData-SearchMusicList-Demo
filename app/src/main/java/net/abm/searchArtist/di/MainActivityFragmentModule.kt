package net.abm.searchArtist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.abm.searchArtist.screen.home.HomeFragment


@Suppress("unused")
@Module
abstract class MainActivityFragmentModule{


    @ContributesAndroidInjector
    abstract fun contributeHomeFragment() : HomeFragment


}