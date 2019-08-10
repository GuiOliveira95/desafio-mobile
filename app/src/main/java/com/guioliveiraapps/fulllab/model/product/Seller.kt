package com.guioliveiraapps.fulllab.model.product

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Seller : Serializable {

    @SerializedName("Id")
    var id: String? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Quantity")
    var quantity: Int? = null

    @SerializedName("Price")
    var price: Double? = null

    @SerializedName("ListPrice")
    var listPrice: Double? = null

    @SerializedName("BestInstallment")
    var bestInstallment: BestInstallment? = null

    @SerializedName("Offer")
    var offer: Double? = null

}