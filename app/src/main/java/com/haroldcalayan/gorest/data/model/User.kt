package com.haroldcalayan.gorest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: Int? = null,
    var name: String,
    var email: String,
    var gender: String,
    var status: String,
    @Json(name = "created_at")
    val createdAt: String? = null,
    @Json(name = "updated_at")
    val updatedAt: String? = null
)