package net.mvvm.testApp.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import net.mvvm.testApp.data.model.album.Album
import net.mvvm.testApp.data.model.album.AlbumReult


@Dao
interface albumDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(auth : List<Album>)


    @Query("SELECT * FROM album ")
    fun getAlbumData() : LiveData<List<Album>>

    @Query("delete from album")
    fun deleteAll()

}