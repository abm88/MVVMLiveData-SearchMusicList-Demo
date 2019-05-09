package net.abm.searchArtist.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import net.abm.searchArtist.data.model.album.Album
import net.abm.searchArtist.data.model.album.AlbumReult
import net.abm.searchArtist.data.repository.test.AlbumRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val albumRepository: AlbumRepository)
    : ViewModel() {


    private var searchLiveData: MutableLiveData<String> = MutableLiveData()
    private var pageLiveData: MutableLiveData<Int> = MutableLiveData()
    private var progressLiveData : MutableLiveData<Boolean> = MutableLiveData()
    private var processedData : MutableLiveData<AlbumReult> = MutableLiveData()
    private val albumLiveData : LiveData<List<Album>> = albumRepository.getAlbum()

    init {


        searchLiveData.observeForever {
//            progressLiveData.postValue(true)
            albumRepository.nextSearch(it!!)

        }

        pageLiveData.observeForever {
//            progressLiveData.postValue(true)
            albumRepository.nextPage(it!!)
        }

//        albumLiveData.observeForever {
//            process data here then hand it over to view
//            if (it != null ){
//                progressLiveData.postValue(false)
//                it.state.run {
//                    if (!successful){
//                        // do something un successful
//                    }
//
//                    if (!error.isNullOrBlank()){
//                        Log.d("testTag" , "recived error :  $error")
//                    }
//                }
//                processedData.postValue(it)
//            }
//        }
//        progressLiveData = albumRepository.isIding()
    }

    fun nextPage(page : Int){
        albumRepository.nextPage(page)
    }

//    fun getAlbumData() = processedData
    fun getAlbumData() = albumLiveData
//    fun getProgress() = progressLiveData
    fun getProgress() = albumRepository.isIding()

    fun nextSearch(search : String ) {
        searchLiveData.postValue(search)
    }


}
