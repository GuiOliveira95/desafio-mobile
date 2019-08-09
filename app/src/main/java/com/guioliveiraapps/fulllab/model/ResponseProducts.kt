package com.guioliveiraapps.fulllab.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseProducts : Serializable {

    @SerializedName("Size")
    var size: Int? = null

    @SerializedName("Offset")
    var offset: Int? = null

    @SerializedName("Total")
    var total: Int? = null

    @SerializedName("Delay")
    var delay: Double? = null

    @SerializedName("Products")
    var products: List<Product>? = null

    @SerializedName("ApiQuery")
    var apiQuery: String? = null
}