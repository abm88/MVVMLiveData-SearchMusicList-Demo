package net.abm.testApp.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.twistedequations.rx2.AndroidRxSchedulers
import dagger.Module
import dagger.Provides
import net.abm.testApp.AppConstants
import net.abm.testApp.data.utils.RequestInterceptor
import net.abm.testApp.data.api.source.service.AlbumTestService
import net.abm.testApp.data.api.source.testDataSource.AlbumDataSource
import net.abm.testApp.data.api.source.testDataSource.AlbumDataSourceImpl
import net.abm.testApp.data.repository.test.AlbumRepository
import net.abm.testApp.data.repository.test.AlbumRepositoryImpl
import net.abm.testApp.data.room.albumDao
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.annotation.Nonnull
import javax.inject.Singleton


@Module
class
NetworkModule {


    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }


    @Provides
    @Singleton
    fun proviudeRetrofit(@Nonnull okHttpClient :  OkHttpClient) : Retrofit {

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }





    @Provides
    @Singleton
    fun provideCellarItems(@Nonnull retrofit: Retrofit) : AlbumTestService {
        return retrofit.create(AlbumTestService::class.java)

    }


    @Provides
    @Singleton
    fun provideTestDataSource(@Nonnull testService : AlbumTestService, @Nonnull rxSchedulers: AndroidRxSchedulers) : AlbumDataSource {
        return AlbumDataSourceImpl(testService , rxSchedulers)
    }

    @Provides
    @Singleton
    fun provideTestRepository(@Nonnull  albumDataSource : AlbumDataSource
                              , @Nonnull albumDao: albumDao
    ): AlbumRepository {
        return AlbumRepositoryImpl(albumDataSource , albumDao)
    }
}