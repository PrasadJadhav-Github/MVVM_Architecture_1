package com.example.mvvm_architecture_1.Repo

import com.example.mvvm_architecture_1.Models.UserDetailsResponse
import com.example.mvvm_architecture_1.Network.UsersService

import java.lang.Exception

class UsersDetailRepo (
    private  val  usersServices: UsersService
): BaseRepo(){
    suspend fun fetchUserDetails(id : Int):UserDetailsResponse?{
        try {
            return usersServices.fetchUserDetails(id)
        }
        catch (e:Exception){}
        return null
    }
}


