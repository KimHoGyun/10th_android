package com.example.myapplication.data.repository

import com.example.myapplication.data.model.UserListResponse
import com.example.myapplication.data.model.UserResponse
import com.example.myapplication.data.remote.ReqResApi
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: ReqResApi
) : UserRepository {

    override suspend fun getUser(id: Int): Result<UserResponse> = runCatching {
        api.getUser(id)
    }

    override suspend fun getUserList(page: Int): Result<UserListResponse> = runCatching {
        api.getUserList(page)
    }
}
