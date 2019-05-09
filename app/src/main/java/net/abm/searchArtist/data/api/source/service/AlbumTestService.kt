package net.abm.searchArtist.data.api.source.service

import io.reactivex.Observable
import net.abm.searchArtist.data.model.album.AlbumReult
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumTestService {


//    @GET("bbrmobileapi/oauth/token")
//    fun getAlbumList(@Header("User-Agent") userAgent : String ,
//                        @Header("account") account : String,
//                        @Query("page")  page : String  ,
//                        @Query("access_token")  accessToken : String) : Observable<AuthenticateModel>



    @GET("2.0")
    fun getAlbum(@Query("method") method : String = "album.search",
                 @Query("album") alnum : String,
//                    @Query("api_key") apy_key : String,
                 @Query("page") page : String,
                 @Query("format") format : String) : Observable<AlbumReult>


}
