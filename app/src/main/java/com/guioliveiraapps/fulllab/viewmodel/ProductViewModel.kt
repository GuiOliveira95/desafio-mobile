package com.guioliveiraapps.fulllab.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.guioliveiraapps.fulllab.model.ProductQuery
import com.guioliveiraapps.fulllab.model.ResponseProducts
import com.guioliveiraapps.fulllab.service.HttpClient

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    var mResponse = MutableLiveData<ResponseProducts>()

    fun getProducts(query: String, offset: String, size: String) {
        val productQuery = ProductQuery(query, offset, size)
        HttpClient.getInstance(getApplication()).getProducts(productQuery, mResponse)
    }

}