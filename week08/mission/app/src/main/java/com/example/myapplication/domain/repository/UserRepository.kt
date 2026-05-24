package com.example.myapplication.domain.repository

import com.example.myapplication.data.model.UserListResponse
import com.example.myapplication.data.model.UserResponse

interface UserRepository {
    suspend fun getUser(id: Int): Result<UserResponse>
    suspend fun getUserList(page: Int = 1): Result<UserListResponse>
}
