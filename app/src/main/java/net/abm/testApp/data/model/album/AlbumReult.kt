package net.abm.testApp.data.model.album



import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AlbumReult(


    @SerializedName("results")
    @Expose
    var results: Results? = null
){
//    constructor() : this(id = null , results = null)
}