package com.guioliveiraapps.fulllab.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image : Serializable {

    @SerializedName("ImageUrl")
    var imageUrl: String? = null

    @SerializedName("ImageTag")
    var imageTag: String? = null

    @SerializedName("Label")
    var label: String? = null
}