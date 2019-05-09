package net.abm.testApp.data.repository.test

import androidx.lifecycle.LiveData
import net.abm.testApp.data.model.album.Album

interface AlbumRepository {


    fun getAlbum() : LiveData<List<Album>>
    fun isIding (): LiveData<Boolean>
    fun nextPage(page : Int)
    fun nextSearch(search: String)
}