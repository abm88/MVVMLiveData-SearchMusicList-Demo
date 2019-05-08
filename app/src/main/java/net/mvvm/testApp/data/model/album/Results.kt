package net.mvvm.testApp.data.model.album


import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("albummatches")
    @Expose
    var albummatches: Albummatches? = null

){
//    constructor() : this( null)
}