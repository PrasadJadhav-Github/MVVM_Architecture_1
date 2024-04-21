package com.example.mvvm_architecture_1.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_architecture_1.Models.UserDetailsModel
import com.example.mvvm_architecture_1.Repo.UsersDetailRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailsViewModel(
    private val userDetailsRepo: UsersDetailRepo
) : ViewModel() {

    val userDetailsLiveData = MutableLiveData<UserDetailsModel?>()

    fun fetchUserDetails(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val userDetailsResponse = userDetailsRepo.fetchUserDetails(id)

            Log.e("tag", userDetailsResponse.toString())

            withContext(Dispatchers.Main) {
                userDetailsLiveData.value = userDetailsResponse?.userDetails
            }
        }
    }
}
