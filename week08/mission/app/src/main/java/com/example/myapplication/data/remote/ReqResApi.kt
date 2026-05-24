package com.example.myapplication.data.remote

import com.example.myapplication.data.model.UserListResponse
import com.example.myapplication.data.model.UserResponse
import retrofit2.http.*

interface ReqResApi {

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): UserResponse

    @GET("users")
    suspend fun getUserList(@Query("page") page: Int = 1): UserListResponse
}
