package net.mvvm.testApp.data.repository.test

import androidx.lifecycle.LiveData
import net.mvvm.testApp.data.model.album.Album
import net.mvvm.testApp.data.model.album.AlbumReult

interface AlbumRepository {


    fun getAlbum() : LiveData<List<Album>>
    fun isIding (): LiveData<Boolean>
    fun nextPage(page : Int)
    fun nextSearch(search: String)
}