package net.mvvm.testApp.data.api.source.testDataSource

import androidx.lifecycle.LiveData
import net.mvvm.testApp.data.model.album.AlbumReult

interface AlbumDataSource {

    val getAlbum : LiveData<AlbumReult>
    val isIdling : LiveData<Boolean>
    fun getSearch(search: String)
    fun getNextPage(page: Int)
}