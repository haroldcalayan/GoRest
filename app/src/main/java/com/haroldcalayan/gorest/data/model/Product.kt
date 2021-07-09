package com.haroldcalayan.gorest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "product")
data class Product(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val price: String,
    @Json(name = "discount_amount")
    val discountAmount: String,
    val status: Boolean
)