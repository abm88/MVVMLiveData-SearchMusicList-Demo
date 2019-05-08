package net.mvvm.testApp.data.model.album


import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Albummatches(



    @SerializedName("album")
    @Expose
    var album: ArrayList<Album>? = null
){
//    constructor() : this(mutableListOf())
}