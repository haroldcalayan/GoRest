package com.haroldcalayan.gorest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "product_category")
data class ProductCategory(
    @PrimaryKey
    val id: Int,
    @Json(name = "product_id")
    val productId: Int,
    @Json(name = "category_id")
    val categoryId: Int,
)