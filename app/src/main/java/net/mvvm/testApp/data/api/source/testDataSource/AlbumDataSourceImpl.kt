package net.mvvm.testApp.data.api.source.testDataSource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.twistedequations.rx2.AndroidRxSchedulers
import io.reactivex.Observable
import net.mvvm.testApp.data.api.source.service.AlbumTestService
import net.mvvm.testApp.data.model.album.AlbumReult
import javax.inject.Inject

class AlbumDataSourceImpl @Inject constructor(private val service : AlbumTestService,
                                              private val rxSchedulers: AndroidRxSchedulers): AlbumDataSource  {


    override val isIdling: LiveData<Boolean>
        get() = _isIdling

    override val getAlbum: LiveData<AlbumReult>
        get() = _authenticate


    private var _authenticate = MutableLiveData<AlbumReult>()
    private val _isIdling : MutableLiveData<Boolean> = MutableLiveData()
    private val pageLiveData: MutableLiveData<Int> = MutableLiveData()
    private val searchLiveData: MutableLiveData<String> = MutableLiveData()
    private var lastSearch = ""

    init {
        pageLiveData.observeForever { it ->
            Log.d("testTag" , "datasource next page : $it called")
            _isIdling.postValue(true)
            service.getAlbum("album.search" , lastSearch  , "$it" , "json")
                .observeOn(rxSchedulers.mainThread()).subscribeOn(rxSchedulers.io())
                .doFinally {
                    _isIdling.postValue(false)
                }
                .subscribe({
//                    it.state = State()
                    _authenticate.postValue(it!!)

                } , {

                    it.printStackTrace()
//                    val model = AuthenticateModel()
//                    model.state.successful = false
//                    if (!it?.message.isNullOrBlank()){
//                        model.state.error = it.message!!
//                    }
//                    _authenticate.postValue(model)
                })

//            service.getAlbumList("firefox" , "account" ,"$it" , "accessToken" ).observeForever{value ->
//                _authenticate.postValue(value)
//            }

//            _authenticate.postValue(service.getAlbumList("firefox" , "account" ,"$it" , "accessToken" ).value)
        }


        searchLiveData.observeForever { search ->
            Log.d("testTag" , "datasource next search : ${search}called")
            lastSearch = search
            _isIdling.postValue(true)
            service.getAlbum("album.search" , search  , "1" , "json")
                .observeOn(rxSchedulers.mainThread()).subscribeOn(rxSchedulers.io())
                .doFinally {
                    _isIdling.postValue(false)
                }
                .subscribe({
                    //                    it.state = State()
                    _authenticate.postValue(it!!)

                } , {

                    it.printStackTrace()
//                    val model = AuthenticateModel()
//                    model.state.successful = false
//                    if (!it?.message.isNullOrBlank()){
//                        model.state.error = it.message!!
//                    }
//                    _authenticate.postValue(model)
                })

//            service.getAlbumList("firefox" , "account" ,"$it" , "accessToken" ).observeForever{value ->
//                _authenticate.postValue(value)
//            }

//            _authenticate.postValue(service.getAlbumList("firefox" , "account" ,"$it" , "accessToken" ).value)
        }

    }

    override fun getSearch(search: String) {
        searchLiveData.postValue(search)
    }

    override fun getNextPage(page: Int) {
        pageLiveData.postValue(page)
    }



}