package com.guioliveiraapps.fulllab.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseCategory : Serializable {

    @SerializedName("Categories")
    var categories: List<Category>? = null

    @SerializedName("Id")
    var id: Int? = null

}