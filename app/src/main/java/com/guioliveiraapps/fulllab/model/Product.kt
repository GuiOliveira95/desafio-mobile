package com.guioliveiraapps.fulllab.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Product : Serializable {

    @SerializedName("Availability")
    var availability: String? = null

    @SerializedName("Skus")
    var skus: List<Sku>? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Id")
    var id: String? = null

    @SerializedName("Brand")
    var brand: String? = null

    @SerializedName("Description")
    var description: String? = null

    @SerializedName("SubCategory")
    var category: String? = null

    @SerializedName("Categories")
    var categories: Array<String>? = null

    @SerializedName("Specifications")
    var specifications: Specification? = null

    @SerializedName("Variations")
    var variations: Array<String>? = null

    @SerializedName("Videos")
    var videos: Array<String>? = null

    @SerializedName("Images")
    var images: Array<String>? = null

    @SerializedName("RealId")
    var realId: String? = null
}