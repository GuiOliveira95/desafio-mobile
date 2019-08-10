package com.guioliveiraapps.fulllab.model.product

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Sku : Serializable {

    @SerializedName("Id")
    var id: String? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Order")
    var order: String? = null

    @SerializedName("Sellers")
    var sellers: List<Seller>? = null

    @SerializedName("Images")
    var images: List<Image>? = null

    @SerializedName("Variations")
    var variations: Variations? = null

    @SerializedName("SkuName")
    var skuName: String? = null

    @SerializedName("UnitMultiplier")
    var unitMultiplier: Double? = null

    @SerializedName("ComplementName")
    var complementName: String? = null

    @SerializedName("MeasurementUnit")
    var measurementUnit: String? = null

    @SerializedName("ReferenceId")
    var referenceId: List<ReferenceId>? = null

    @SerializedName("EAN")
    var ean: String? = null

}