package com.kabira.boredapp.models

import com.google.gson.annotations.SerializedName

class CardModel {

    @SerializedName("activity")
    var activity : String? = null
    @SerializedName("type")
    var type: String? = null
    @SerializedName("participants")
    var participants: String? = null
    @SerializedName("price")
    var price: String? = null
    @SerializedName("link")
    var link: String? = null
    @SerializedName("key")
    var key: String? = null
    @SerializedName("accessibility")
    var accessibility: String? = null
}