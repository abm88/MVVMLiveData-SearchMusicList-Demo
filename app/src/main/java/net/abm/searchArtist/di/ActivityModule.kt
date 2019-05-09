package net.abm.searchArtist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.abm.searchArtist.screen.home.HomeActivity


@Suppress("unused")
@Module
abstract class ActivityModule{

    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    internal abstract fun contributeMainActivity() : HomeActivity

}