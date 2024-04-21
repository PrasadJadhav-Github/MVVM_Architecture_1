package com.example.mvvm_architecture_1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_architecture_1.Adapters.UsersAdapters
import com.example.mvvm_architecture_1.Factory.MyViewModelFactory
import com.example.mvvm_architecture_1.Fragments.UserDetailsFragment
import com.example.mvvm_architecture_1.Models.UserModel
import com.example.mvvm_architecture_1.Network.UsersService
import com.example.mvvm_architecture_1.Repo.UsersRepo
import com.example.mvvm_architecture_1.ViewModel.UsersViewModel
import com.example.mvvm_architecture_1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var usersAdapter: UsersAdapters
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initViewModels()
        initAdapter()
        initObservers()
        initListeners()

        usersViewModel.fetchUsers()
    }

    private fun initListeners() {
        usersAdapter.onUserClickListener = object : UsersAdapters.OnUserClickListener {
            override fun onUserClick(user: UserModel, position: Int) {
                val userDetailsFragment = UserDetailsFragment()

                val input = Bundle()
                input.putInt("id", user.id)
                userDetailsFragment.arguments = input


                supportFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, userDetailsFragment, "userdetailsfragment")
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun initObservers() {
        usersViewModel.usersLiveData.observe(this) { success ->
            if (success) {
                usersAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun initViewModels() {
        usersViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(UsersRepo(UsersService.getInstance()))
        ).get(UsersViewModel::class.java)
    }

    private fun initAdapter() {
        usersAdapter = UsersAdapters(usersViewModel.users)
        activityMainBinding.recyclerUsers.adapter = usersAdapter
    }

    private fun initViews() {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.recyclerUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
