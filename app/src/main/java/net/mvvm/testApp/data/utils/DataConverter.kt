package net.mvvm.testApp.data.utils

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.mvvm.testApp.data.model.album.Image

class DataConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromImageList(images : ArrayList<Image>?) : String{

            if (images.isNullOrEmpty()){
                return ""
            }
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Image>>() {}.type
            return gson.toJson(images , type)
        }

        @TypeConverter
        @JvmStatic
        fun toListOfImages(images : String) :ArrayList<Image>?{

            if (images.isNullOrEmpty()){
                return ArrayList()
            }

            val gson = Gson()
            val type = object : TypeToken<ArrayList<Image>>() {}.type
            return gson.fromJson(images, type)


        }
    }

}