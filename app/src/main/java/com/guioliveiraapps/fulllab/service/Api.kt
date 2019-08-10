package com.guioliveiraapps.fulllab.service

import com.guioliveiraapps.fulllab.model.ProductQuery
import com.guioliveiraapps.fulllab.model.ResponseCategory
import com.guioliveiraapps.fulllab.model.ResponseProducts
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @POST("/Search/Criteria")
    fun getProducts(@Body productQuery: ProductQuery): Call<ResponseProducts>

    @GET("/StorePreference/CategoryTree")
    fun getCategories(): Call<ResponseCategory>


}