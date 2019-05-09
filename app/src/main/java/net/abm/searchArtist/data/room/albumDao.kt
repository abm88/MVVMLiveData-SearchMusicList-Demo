package net.abm.searchArtist.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import net.abm.searchArtist.data.model.album.Album


@Dao
interface albumDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(auth : List<Album>)


    @Query("SELECT * FROM album ")
    fun getAlbumData() : LiveData<List<Album>>

    @Query("delete from album")
    fun deleteAll()

}