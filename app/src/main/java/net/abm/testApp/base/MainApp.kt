package net.abm.testApp.base

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.abm.testApp.di.DaggerAppComponent


@Suppress("unused")
class MainApp : DaggerApplication(){


    private val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        Stetho.initializeWithDefaults(this)
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {


        return appComponent
    }


}