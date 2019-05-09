package net.abm.searchArtist

import android.app.Application
import androidx.room.Room
import net.abm.searchArtist.data.model.account.AuthenticateModel
import net.abm.searchArtist.data.room.AppDatabase
import net.abm.searchArtist.data.room.albumDao
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Mock private val  application = Application()

    private lateinit var albumDao : albumDao
    @Before
    fun setup(){
        albumDao = Room.databaseBuilder( application,
            AppDatabase::class.java ,
            AppConstants.DATABASE_NAME)
            .allowMainThreadQueries().build().authDao()
    }



    @Test
    fun roomTest(){
        val auth1 = AuthenticateModel("access token1" ,
            "token_type" ,
            "refresh_token" ,
            0)

        albumDao.upsert(auth1)

        val auth2 = AuthenticateModel("access token2" ,
            "token_typ2" ,
            "refresh_toke2" ,
            0)

        albumDao.upsert(auth2)

        albumDao.getAlbumData().observeForever {

        }



    }
}
