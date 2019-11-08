package com.example.vitalii.kotlinretrofirxjavalogin.service


import com.example.vitalii.kotlinretrofirxjavalogin.model.LoginUser
import com.example.vitalii.kotlinretrofirxjavalogin.model.PostReview
import com.example.vitalii.kotlinretrofirxjavalogin.model.Product
import com.example.vitalii.kotlinretrofirxjavalogin.model.Review

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface JsonProductsApi {

    @GET("api/products/")
    fun getProducts(): Observable<List<Product>>

    @POST("api/register/")
    @FormUrlEncoded
    fun getToken(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<LoginUser>

    @GET("/api/reviews/{id}")
    fun getReview(@Path("id") id: Int): Observable<List<Review>>

    @POST("/api/reviews/{id}")
    @FormUrlEncoded
    fun postReview(
        @Header("Authorization") token: String,
        @Path("id") id: Int, @Field("rate") rate: Int,
        @Field("text") text: String
    ): Observable<PostReview>
}
