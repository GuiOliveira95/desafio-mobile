package com.guioliveiraapps.fulllab.response

import com.google.gson.annotations.SerializedName
import com.guioliveiraapps.fulllab.model.category.Category
import java.io.Serializable

class ResponseCategory : Serializable {

    @SerializedName("Categories")
    var categories: List<Category>? = null

    @SerializedName("Id")
    var id: Int? = null

}