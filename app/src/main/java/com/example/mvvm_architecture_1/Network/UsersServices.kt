package com.example.mvvm_architecture_1.Network

import com.example.mvvm_architecture_1.Models.UserDetailsResponse
import com.example.mvvm_architecture_1.Models.UsersResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface UsersService {

    @GET("api/users?page=2")
    suspend fun fetchUsers() : UsersResponse

    @GET("api/users/{userId}")
    suspend fun fetchUserDetails(
        @Path("userId") id : Int
    ) : UserDetailsResponse

    companion object {
        private var usersService : UsersService? = null

        fun getInstance() : UsersService {
            if(usersService == null) {
                usersService = Retrofit.Builder()
                    .baseUrl("https://reqres.in/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UsersService::class.java)
            }
            return usersService!!
        }
    }

}