package net.abm.testApp.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import net.abm.testApp.data.model.album.Album


@Dao
interface albumDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(auth : List<Album>)


    @Query("SELECT * FROM album ")
    fun getAlbumData() : LiveData<List<Album>>

    @Query("delete from album")
    fun deleteAll()

}