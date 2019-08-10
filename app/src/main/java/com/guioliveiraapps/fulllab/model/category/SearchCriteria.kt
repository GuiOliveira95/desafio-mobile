package com.guioliveiraapps.fulllab.model.category

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SearchCriteria : Serializable {

    @SerializedName("Query")
    var query: String? = null

    @SerializedName("OrderBy")
    var orderBy: Int? = null

    @SerializedName("Size")
    var size: Int? = null

    @SerializedName("Offset")
    var offset: String? = null

    @SerializedName("Filter")
    var filter: String? = null

    @SerializedName("ApiQuery")
    var apiQuery: String? = null

    @SerializedName("ProductId")
    var productId: Int? = null

    @SerializedName("Hotsite")
    var hotsite: String? = null

    @SerializedName("RealProductId")
    var realProductId: Int? = null

    @SerializedName("EAN")
    var ean: String? = null

    @SerializedName("RealProductIdGroup")
    var realProductIdGroup: Int? = null

    @SerializedName("FacetItems")
    var facetItems: String? = null

    @SerializedName("SearchApi")
    var searchApi: String? = null

}