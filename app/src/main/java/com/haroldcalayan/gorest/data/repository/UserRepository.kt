package com.haroldcalayan.gorest.data.repository

import android.content.Context
import com.haroldcalayan.gorest.base.BaseRepository
import com.haroldcalayan.gorest.base.BaseResponse
import com.haroldcalayan.gorest.data.model.Todo
import com.haroldcalayan.gorest.data.model.User
import com.haroldcalayan.gorest.data.source.local.database.GoRestDatabase
import com.haroldcalayan.gorest.data.source.remote.service.GoRestApiService
import com.haroldcalayan.gorest.util.NetworkUtils

interface UserRepository {
    suspend fun getUsers(page: Int = 1): BaseResponse<List<User>>?
    suspend fun getUserTodos(userId: Int, page: Int = 1): BaseResponse<List<Todo>>?
    suspend fun createUser(user: User): User?
}

class UserRepositoryImpl(
    private val context: Context,
    private val database: GoRestDatabase,
    private val api: GoRestApiService
) : BaseRepository(), UserRepository {

    override suspend fun getUsers(page: Int): BaseResponse<List<User>>? {
        return if (NetworkUtils.isNetworkConnected(context)) {
            getUsersFromRemote(page)
        } else {
            BaseResponse(data = getUsersFromDatabase())
        }
    }

    override suspend fun getUserTodos(userId: Int, page: Int): BaseResponse<List<Todo>>? {
        return if (NetworkUtils.isNetworkConnected(context)) {
            getTodosFromRemote(userId, page)
        } else {
            BaseResponse(data = getTodosFromDatabase())
        }
    }

    override suspend fun createUser(user: User): User? {
        return api.createUser(user)
    }

    private suspend fun getUsersFromDatabase() = database.userDao().all()

    @Throws(java.lang.Exception::class)
    private suspend fun getUsersFromRemote(page: Int) : BaseResponse<List<User>>? {
        var response = api.getUsers(page)
        var users = response?.data
        database.userDao().nukeTable()
        if (users != null) database.userDao().insertAll(users)
        return response
    }

    private suspend fun getTodosFromDatabase() = database.todoDao().all()

    @Throws(java.lang.Exception::class)
    private suspend fun getTodosFromRemote(userId: Int, page: Int) : BaseResponse<List<Todo>>? {
        var response = api.getUserTodos(userId, page)
        var todos = response?.data
        database.todoDao().nukeTable()
        if (todos != null) database.todoDao().insertAll(todos)
        return response
    }

}