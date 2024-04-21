package com.example.mvvm_architecture_1.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_architecture_1.Repo.BaseRepo
import com.example.mvvm_architecture_1.Repo.UsersDetailRepo
import com.example.mvvm_architecture_1.Repo.UsersRepo
import com.example.mvvm_architecture_1.ViewModel.UserDetailsViewModel
import com.example.mvvm_architecture_1.ViewModel.UsersViewModel
import java.security.InvalidParameterException

// Custom ViewModelFactory implementation that takes a BaseRepo instance
class MyViewModelFactory(
    private val repo: BaseRepo
) : ViewModelProvider.Factory {

    // This method is called by the ViewModelProvider when it needs to create a new ViewModel instance
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        // Check if the requested ViewModel is UsersViewModel and the repo is UsersRepo
        if (modelClass.isAssignableFrom(UsersViewModel::class.java) && repo is UsersRepo) {
            // If it is, create and return a new instance of UsersViewModel with the given repo
            return UsersViewModel(repo) as T
        }

        // Check if the requested ViewModel is UserDetailsViewModel and the repo is UsersDetailRepo
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java) && repo is UsersDetailRepo) {
            // If it is, create and return a new instance of UserDetailsViewModel with the given repo
            return UserDetailsViewModel(repo) as T
        }

        // If no matching ViewModel is found, throw an exception
        throw InvalidParameterException("No matching view model found")
    }
}
