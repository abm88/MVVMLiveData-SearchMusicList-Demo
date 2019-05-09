package net.abm.testApp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.abm.testApp.factory.AppViewModelFactory
import net.abm.testApp.screen.home.HomeViewModel


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeFragmentViewModel(homeViewModel : HomeViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory : AppViewModelFactory) : ViewModelProvider.Factory


}