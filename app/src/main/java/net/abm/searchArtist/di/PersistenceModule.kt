package net.abm.searchArtist.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.abm.searchArtist.AppConstants
import net.abm.searchArtist.data.room.AppDatabase
import net.abm.searchArtist.data.room.albumDao
import javax.inject.Singleton


@Module
 class PersistenceModule {



    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application) :AppDatabase {

        return Room.databaseBuilder(application ,
            AppDatabase::class.java ,
            AppConstants.DATABASE_NAME)
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideAuthDao(@NonNull database : AppDatabase) : albumDao {
        return database.authDao()
    }

}