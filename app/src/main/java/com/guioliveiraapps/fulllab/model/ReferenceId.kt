package com.guioliveiraapps.fulllab.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ReferenceId : Serializable {

    @SerializedName("Key")
    var key: String? = null

    @SerializedName("Value")
    var value:String? = null
}