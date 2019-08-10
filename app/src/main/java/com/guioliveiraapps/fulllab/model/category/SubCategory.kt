package com.guioliveiraapps.fulllab.model.category

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SubCategory : Serializable {

    @SerializedName("Id")
    var id: Int? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Image")
    var image: String? = null

    @SerializedName("Redirect")
    var redirect: Redirect? = null

    @SerializedName("SubCategories")
    var subCategories: List<SubCategory>? = null

    @SerializedName("Highlight")
    var highlight: Boolean? = null

    @SerializedName("Icon")
    var icon: String? = null

    @SerializedName("CategoryListOrder")
    var categoryListOrder: Int? = null

    @SerializedName("CategoryTreeOrder")
    var categoryTreeOrder: Int? = null

    @SerializedName("LinkId")
    var linkId: Int? = null

}