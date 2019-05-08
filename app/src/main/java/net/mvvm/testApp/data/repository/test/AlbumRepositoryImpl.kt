package net.mvvm.testApp.data.repository.test

import android.util.Log
import androidx.lifecycle.LiveData
import net.mvvm.testApp.data.api.source.testDataSource.AlbumDataSource
import net.mvvm.testApp.data.model.album.Album
import net.mvvm.testApp.data.model.album.AlbumReult
import net.mvvm.testApp.data.room.albumDao
import net.mvvm.testApp.utils.Utils
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AlbumRepositoryImpl @Inject constructor(
    val albumDataSource : AlbumDataSource,val albumDao: albumDao) : AlbumRepository {


    private var page = 0
    private var isNextPage = false
    private var isNextSearch = false
    override fun nextSearch(search: String  ) {
        isNextPage = false
        isNextSearch = true
        albumDataSource.getSearch(search )
    }

    override fun nextPage(page: Int) {
        isNextPage = true
        isNextSearch = false
        albumDataSource.getNextPage(page )

    }

    init {

        albumDataSource.getAlbum.observeForever {
            it?.let {
                Log.d("testTag" , "inserting into album")
                if (isNextSearch){
                    albumDao.deleteAll()
                }

                albumDao.upsert(it.results?.albummatches?.album!!)
//                it.body?.let {body ->
//                    Log.d("testTag" , "accessToken : ${body.accessToken}")
//                    albumDao.upsert(body)

//                }
            }
        }


    }



    override fun isIding(): LiveData<Boolean> {
        return albumDataSource.isIdling
    }

    override fun getAlbum(): LiveData<List<Album>> {
        return albumDao.getAlbumData()
//        return albumDataSource.getAlbum

    }








}