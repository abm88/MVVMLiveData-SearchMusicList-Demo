package net.abm.testApp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.abm.testApp.data.model.album.Album
import net.abm.testApp.data.utils.DataConverter


@Database(entities = [(Album::class) ] ,
    version = 1 ,exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun authDao() : albumDao
}