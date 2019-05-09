package net.abm.testApp.data.model.album


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Albummatches(



    @SerializedName("album")
    @Expose
    var album: ArrayList<Album>? = null
){
//    constructor() : this(mutableListOf())
}