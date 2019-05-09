package net.abm.testApp.data.model.album


import androidx.annotation.NonNull
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "album" ,
    primaryKeys = arrayOf("id"))
data class Album(

    @NonNull
    var id : String = UUID.randomUUID().toString(),

    @SerializedName("artist")
    @Expose
    var artist: String? = null,

    @SerializedName("image")
    @Expose
//    @Embedded
    var image: ArrayList<Image>? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null
){
//    constructor() : this(null , mutableListOf() , null , null )
}