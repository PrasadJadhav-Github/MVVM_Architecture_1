package com.example.mvvm_architecture_1.Models

import com.google.gson.annotations.SerializedName

class UsersResponse(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val users : ArrayList<UserModel>

)