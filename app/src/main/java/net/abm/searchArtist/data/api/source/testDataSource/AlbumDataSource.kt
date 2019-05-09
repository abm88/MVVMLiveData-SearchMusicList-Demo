package net.abm.searchArtist.data.api.source.testDataSource

import androidx.lifecycle.LiveData
import net.abm.searchArtist.data.model.album.AlbumReult

interface AlbumDataSource {

    val getAlbum : LiveData<AlbumReult>
    val isIdling : LiveData<Boolean>
    fun getSearch(search: String)
    fun getNextPage(page: Int)
}