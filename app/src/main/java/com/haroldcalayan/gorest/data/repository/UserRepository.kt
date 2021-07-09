package com.haroldcalayan.gorest.data.repository

import com.haroldcalayan.gorest.base.BaseRepository
import com.haroldcalayan.gorest.base.BaseResponse
import com.haroldcalayan.gorest.data.model.Todo
import com.haroldcalayan.gorest.data.model.User
import com.haroldcalayan.gorest.data.source.local.database.GoRestDatabase
import com.haroldcalayan.gorest.data.source.remote.service.GoRestApiService

interface UserRepository {
    suspend fun getUsers(page: Int = 1): BaseResponse<List<User>>?
    suspend fun getUserTodos(userId: Int, page: Int = 1): BaseResponse<List<Todo>>?
    suspend fun createUser(user: User): User?
}

class UserRepositoryImpl(
    private val database: GoRestDatabase,
    private val api: GoRestApiService
) : BaseRepository(), UserRepository {

    override suspend fun getUsers(page: Int): BaseResponse<List<User>>? {
        return api.getUsers(page)
    }

    override suspend fun getUserTodos(userId: Int, page: Int): BaseResponse<List<Todo>>? {
        return api.getUserTodos(userId, page)
    }

    override suspend fun createUser(user: User): User? {
        return api.createUser(user)
    }

}