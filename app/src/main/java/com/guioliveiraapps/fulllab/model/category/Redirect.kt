package com.guioliveiraapps.fulllab.model.category

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Redirect : Serializable {

    @SerializedName("Id")
    var id: Int? = null

    @SerializedName("SearchCriteria")
    var searchCriteria: SearchCriteria? = null

    @SerializedName("Title")
    var title: String? = null

    @SerializedName("Type")
    var type: Int? = null

}