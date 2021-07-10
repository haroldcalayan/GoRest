package com.haroldcalayan.gorest.data.repository

import android.content.Context
import com.haroldcalayan.gorest.base.BaseRepository
import com.haroldcalayan.gorest.base.BaseResponse
import com.haroldcalayan.gorest.data.model.Category
import com.haroldcalayan.gorest.data.model.Product
import com.haroldcalayan.gorest.data.model.ProductCategory
import com.haroldcalayan.gorest.data.source.local.database.GoRestDatabase
import com.haroldcalayan.gorest.data.source.remote.service.GoRestApiService
import com.haroldcalayan.gorest.util.NetworkUtils

interface ProductRepository {
    suspend fun getProducts(page: Int = 1): BaseResponse<List<Product>>?
    suspend fun getCategories(page: Int = 1): BaseResponse<List<Category>>?
    suspend fun getProductCategories(page: Int = 1): BaseResponse<List<ProductCategory>>?
}

class ProductRepositoryImpl(
    private val context: Context,
    private val database: GoRestDatabase,
    private val api: GoRestApiService
) : BaseRepository(), ProductRepository {

    override suspend fun getProducts(page: Int): BaseResponse<List<Product>>? {
        return if (NetworkUtils.isNetworkConnected(context)) {
            getProductsFromRemote(page)
        } else {
            BaseResponse(data = getProductsFromDatabase())
        }
    }

    override suspend fun getCategories(page: Int): BaseResponse<List<Category>>? {
        return if (NetworkUtils.isNetworkConnected(context)) {
            getCategoriesFromRemote(page)
        } else {
            BaseResponse(data = getCategoriesFromDatabase())
        }
    }

    override suspend fun getProductCategories(page: Int): BaseResponse<List<ProductCategory>>? {
        return if (NetworkUtils.isNetworkConnected(context)) {
            getProductCategoriesFromRemote(page)
        } else {
            BaseResponse(data = getProductCategoriesFromDatabase())
        }
    }

    private suspend fun getProductsFromDatabase() = database.productDao().all()

    @Throws(java.lang.Exception::class)
    private suspend fun getProductsFromRemote(page: Int) : BaseResponse<List<Product>>? {
        var response = api.getProducts(page)
        var products = response?.data
        database.productDao().nukeTable()
        if (products != null) database.productDao().insertAll(products)
        return response
    }

    private suspend fun getCategoriesFromDatabase() = database.categoryDao().all()

    @Throws(java.lang.Exception::class)
    private suspend fun getCategoriesFromRemote(page: Int) : BaseResponse<List<Category>>? {
        var response = api.getCategories(page)
        var categories = response?.data
        database.categoryDao().nukeTable()
        if (categories != null) database.categoryDao().insertAll(categories)
        return response
    }

    private suspend fun getProductCategoriesFromDatabase() = database.productCategoryDao().all()

    @Throws(java.lang.Exception::class)
    private suspend fun getProductCategoriesFromRemote(page: Int) : BaseResponse<List<ProductCategory>>? {
        var response = api.getProductCategories(page)
        var productCategories = response?.data
        database.productCategoryDao().nukeTable()
        if (productCategories != null) database.productCategoryDao().insertAll(productCategories)
        return response
    }
}