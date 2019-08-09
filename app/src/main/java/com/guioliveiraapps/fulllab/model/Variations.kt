package com.guioliveiraapps.fulllab.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Variations : Serializable {

    @SerializedName("Voltagem")
    var voltagem: Array<String>? = null

    @SerializedName("Cor")
    var cor: Array<String>? = null
}