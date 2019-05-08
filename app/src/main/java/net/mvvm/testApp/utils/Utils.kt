package net.mvvm.testApp.utils

import net.mvvm.testApp.data.model.album.Album
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class Utils {
    companion object {
        fun generateRandomId() : String{
            try{
                return UUID.randomUUID().toString().replace("-" , "")

            }catch (e : Exception){
                e.printStackTrace()
            }
            return "0"
        }

        fun generateRandomIdForAlbumList(albums: ArrayList<Album>): List<Album> {
            for (album in albums  ){
                album.id = generateRandomId()
            }

            return albums
        }
    }
}