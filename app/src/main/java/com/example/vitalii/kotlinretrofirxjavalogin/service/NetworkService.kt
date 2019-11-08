package com.example.vitalii.kotlinretrofirxjavalogin.service

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor() {
    private val mRetrofit: Retrofit


    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    fun jsonApi(): JsonProductsApi {
        return mRetrofit.create(JsonProductsApi::class.java)
    }

    fun login(): JsonProductsApi {
        return mRetrofit.create(JsonProductsApi::class.java)
    }

    fun sendReviewProduct(): JsonProductsApi {
        return mRetrofit.create(JsonProductsApi::class.java)
    }
    fun getReviewProducts(): JsonProductsApi {
        return mRetrofit.create(JsonProductsApi::class.java)
    }

    companion object {
        private var mInstance: NetworkService? = null
        private const val BASE_URL = "http://smktesting.herokuapp.com/"
        val instance: NetworkService
            get() {
                if (mInstance == null) {
                    mInstance = NetworkService()
                }
                return mInstance!!
            }
    }
}

