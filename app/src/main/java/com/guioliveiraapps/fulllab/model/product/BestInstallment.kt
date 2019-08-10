package com.guioliveiraapps.fulllab.model.product

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BestInstallment : Serializable {

    @SerializedName("Count")
    var count: Int? = null

    @SerializedName("Value")
    var value: Double? = null

    @SerializedName("Total")
    var total: Double? = null

    @SerializedName("Rate")
    var rate: Double? = null
}