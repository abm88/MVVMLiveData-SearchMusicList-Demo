package net.mvvm.testApp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class ,
    ActivityModule::class ,
    NetworkModule::class ,
    AndroidModule::class ,
    PersistenceModule::class ,
    ViewModelModule::class ,
    RxModule::class])
interface AppComponent: AndroidInjector<DaggerApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication)
}