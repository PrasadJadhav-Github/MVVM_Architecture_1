package com.example.mvvm_architecture_1.Repo

import com.example.mvvm_architecture_1.Models.UsersResponse
import com.example.mvvm_architecture_1.Network.UsersService


class UsersRepo(
    private val usersServices: UsersService
) : BaseRepo(){
    suspend fun fetchUsers():UsersResponse?{
        return usersServices.fetchUsers()
    }
}