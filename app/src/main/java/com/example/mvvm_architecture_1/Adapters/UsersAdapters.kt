package com.example.mvvm_architecture_1.Adapters

// Import necessary classes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mvvm_architecture_1.Models.UserModel
import com.example.mvvm_architecture_1.R
import com.example.mvvm_architecture_1.databinding.UserViewBinding

// Define the UsersAdapter class which extends RecyclerView.Adapter
class UsersAdapters (
    private val users: ArrayList<UserModel>
):Adapter<UsersAdapters.UserViewHolder>(){

    // Define an interface for click handling
    interface OnUserClickListener{
        fun onUserClick(user : UserModel,position:Int)
    }

    // Initialize an OnUserClickListener variable
    var onUserClickListener:OnUserClickListener?=null

    // Define the ViewHolder class which extends RecyclerView.ViewHolder
    inner  class  UserViewHolder(view: View): ViewHolder(view){
        // Initialize UserViewBinding variable
        val userViewBinding : UserViewBinding
        init {
            // Bind the view
            userViewBinding=UserViewBinding.bind(view)

            // Set click listener for the item
            userViewBinding.root.setOnClickListener {
                onUserClickListener?.onUserClick(users[adapterPosition],adapterPosition)
            }
        }
    }

    // Return the number of items in the data set
    override fun getItemCount(): Int {
        return  users.size
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return  UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_view, null)
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userViewBinding.user=users[position]
    }
}
