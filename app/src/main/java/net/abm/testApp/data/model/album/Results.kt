package net.abm.testApp.data.model.album


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("albummatches")
    @Expose
    var albummatches: Albummatches? = null

){
//    constructor() : this( null)
}