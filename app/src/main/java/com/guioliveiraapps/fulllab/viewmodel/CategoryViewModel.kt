package com.guioliveiraapps.fulllab.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.guioliveiraapps.fulllab.model.ResponseCategory
import com.guioliveiraapps.fulllab.service.HttpClient

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    var mResponse = MutableLiveData<ResponseCategory>()

    fun getCategories() {
        HttpClient.getInstance(getApplication()).getCategories(mResponse)
    }

}