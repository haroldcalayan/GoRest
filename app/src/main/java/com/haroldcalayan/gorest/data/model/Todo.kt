package com.haroldcalayan.gorest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey
    val id: Int,
    @Json(name = "user_id")
    val userId: Int,
    val title: String,
    @Json(name = "due_on")
    val dueOn: String,
    val status: String,
    var isChecked: Boolean? = false
)