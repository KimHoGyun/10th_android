package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val data: UserData
)

data class UserListResponse(
    val data: List<UserData>
)

data class UserData(
    val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val email: String,
    val avatar: String
)
