package com.example.mvvm_architecture_1.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_architecture_1.Models.UserModel
import com.example.mvvm_architecture_1.Repo.UsersRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModel(
    private val usersRepo: UsersRepo
) : ViewModel() {

    val usersLiveData = MutableLiveData<Boolean>()
    val users = ArrayList<UserModel>()

    fun fetchUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val usersResponse = usersRepo.fetchUsers()

                withContext(Dispatchers.Main) {
                    if(usersResponse != null) {
                        usersLiveData.postValue(true)
                    }
                    else {
                        usersLiveData.postValue(false)
                    }
                }
            }
            catch (e : Exception) {
                // Log the exception for debugging purposes
                e.printStackTrace()
                usersLiveData.postValue(false)
            }
        }
    }
}
