package com.guioliveiraapps.fulllab.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Category : Serializable {

    @SerializedName("Id")
    var id: Int? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Image")
    var image: String? = null

    @SerializedName("Redirect")
    var redirect: Redirect? = null

    @SerializedName("Subcategories")
    var subCategories: List<SubCategory>? = null

    @SerializedName("Highlight")
    var highligh: String? = null

    @SerializedName("Icon")
    var icon: String? = null

    @SerializedName("CategoryListOrder")
    var categoryListOrder: Int? = null

    @SerializedName("CategorytreeOrder")
    var categoryTreeOrder: Int? = null

    @SerializedName("LinkId")
    var linkId: Int? = null
}