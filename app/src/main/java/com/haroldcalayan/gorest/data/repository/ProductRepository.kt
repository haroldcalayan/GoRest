package com.haroldcalayan.gorest.data.repository

import com.haroldcalayan.gorest.base.BaseRepository
import com.haroldcalayan.gorest.base.BaseResponse
import com.haroldcalayan.gorest.data.model.Product
import com.haroldcalayan.gorest.data.model.ProductCategory
import com.haroldcalayan.gorest.data.source.local.database.GoRestDatabase
import com.haroldcalayan.gorest.data.source.remote.service.GoRestApiService

interface ProductRepository {
    suspend fun getProducts(page: Int = 1): BaseResponse<List<Product>>?
    suspend fun getProductCategories(page: Int = 1): BaseResponse<List<ProductCategory>>?
}

class ProductRepositoryImpl(
    private val database: GoRestDatabase,
    private val api: GoRestApiService
) : BaseRepository(), ProductRepository {

    override suspend fun getProducts(page: Int): BaseResponse<List<Product>>? {
        return api.getProducts(page)
    }

    override suspend fun getProductCategories(page: Int): BaseResponse<List<ProductCategory>>? {
        return api.getProductCategories(page)
    }
}