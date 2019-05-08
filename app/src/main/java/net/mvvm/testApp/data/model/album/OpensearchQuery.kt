package net.mvvm.testApp.data.model.album


import com.google.gson.annotations.SerializedName

data class OpensearchQuery(
    @SerializedName("role")
    var role: String,
    @SerializedName("searchTerms")
    var searchTerms: String,
    @SerializedName("startPage")
    var startPage: String,
    @SerializedName("#text")
    var text: String
)