package com.haroldcalayan.gorest.base

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    val code: Int,
    val meta: Meta,
    val data: T
) {

    @JsonClass(generateAdapter = true)
    data class Meta(
        val pagination: Pagination
    )

    @JsonClass(generateAdapter = true)
    data class Pagination(
        val total: Int,
        val pages: Int,
        val page: Int,
        val limit: Int
    )
}