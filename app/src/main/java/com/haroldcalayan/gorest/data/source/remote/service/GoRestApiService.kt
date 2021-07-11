package com.haroldcalayan.gorest.data.source.remote.service

import com.haroldcalayan.gorest.base.BaseResponse
import com.haroldcalayan.gorest.data.model.*
import retrofit2.http.*

interface GoRestApiService {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): BaseResponse<List<User>>?

    @GET("user/{id}/todos")
    suspend fun getUserTodos(@Path("id") userId: Int, @Query("page") page: Int): BaseResponse<List<Todo>>?

    @Headers("Authorization: Bearer")
    @POST("users")
    suspend fun createUser(@Body body: User): BaseResponse<User>?

    @GET("products")
    suspend fun getProducts(@Query("page") page: Int): BaseResponse<List<Product>>?

    @GET("categories")
    suspend fun getCategories(@Query("page") page: Int): BaseResponse<List<Category>>?

    @GET("product-categories")
    suspend fun getProductCategories(@Query("page") page: Int): BaseResponse<List<ProductCategory>>?

}