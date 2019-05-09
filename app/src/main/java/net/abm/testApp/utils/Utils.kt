package net.abm.testApp.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import net.abm.testApp.data.model.album.Album
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

        fun hideKeyboard(context: Context, view: View) {
            (context.getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken,
                0)
        }
    }




}