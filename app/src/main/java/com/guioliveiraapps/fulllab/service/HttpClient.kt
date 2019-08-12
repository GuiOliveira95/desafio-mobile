package com.guioliveiraapps.fulllab.service

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.guioliveiraapps.fulllab.model.ProductQuery
import com.guioliveiraapps.fulllab.response.ResponseCategory
import com.guioliveiraapps.fulllab.response.ResponseProducts
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

class HttpClient {

    lateinit var repository: Api
    private var gson: Gson? = null

    var mContext: Context

    constructor(context: Context) {
        mContext = context
        configClient("https://desafio.mobfiq.com.br")
    }

    private fun configClient(url: String) {

        val builder = GsonBuilder().setLenient()
        gson = builder.create()
        builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)

        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.addInterceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder().build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson!!))
            .client(httpClient.build())
            .build()

        repository = retrofit.create(Api::class.java)
    }

    companion object {
        private var instance: HttpClient? = null
        fun getInstance(context: Context): HttpClient {
            if (instance == null) {
                instance = HttpClient(context)
            }

            return instance!!
        }
    }

    fun getProducts(productQuery: ProductQuery, mApiResponse: MutableLiveData<ResponseProducts>) {
        repository.getProducts(productQuery).enqueue(object : Callback<ResponseProducts> {
            override fun onResponse(call: Call<ResponseProducts>?, response: Response<ResponseProducts>?) {
                if (response!!.isSuccessful) {
                    val res = ResponseProducts()
                    res.products = response.body()!!.products
                    mApiResponse.postValue(res)
                }
            }

            override fun onFailure(call: Call<ResponseProducts>, t: Throwable) {
//                    val res = ResponseProducts()
//                    res.error = true
//                    mApiResponse.postValue(res)
            }
        })
    }

    fun getCategories(mApiResponse: MutableLiveData<ResponseCategory>) {
        repository.getCategories().enqueue(object : Callback<ResponseCategory> {
            override fun onResponse(call: Call<ResponseCategory>?, response: Response<ResponseCategory>?) {
                if (response!!.isSuccessful) {
                    val res = ResponseCategory()
                    res.categories = response.body()!!.categories
                    mApiResponse.postValue(res)
                }
            }

            override fun onFailure(call: Call<ResponseCategory>, t: Throwable) {
//                    val res = ResponseProducts()
//                    res.error = true
//                    mApiResponse.postValue(res)
            }
        })
    }
}