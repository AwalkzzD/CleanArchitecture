package com.example.cleanarchitecture.presentation.ui.home.users

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.data.dto.user.User
import com.example.cleanarchitecture.databinding.FragmentUsersBinding
import com.example.cleanarchitecture.base.adapter.GenericDataAdapter
import com.example.cleanarchitecture.base.views.BaseFragment

private const val TAG = "UsersFragment"

class UsersFragment : BaseFragment<FragmentUsersBinding, UsersViewModel>(
    R.layout.fragment_users, UsersViewModel::class.java
) {

    private lateinit var usersAdapter: GenericDataAdapter<User>
    private val usersList: MutableList<User> = mutableListOf()

    override fun setUpView() {
        setUpListener()
        setUpRecyclerView()
        setUpViewModel()

        super.setUpView()
    }

    private fun setUpListener() {
        fragmentBinding.saveToDb.setOnClickListener {
            fragmentViewModel.saveUsers(usersList)
        }
    }

    private fun setUpViewModel() {
        fragmentViewModel.getUsers()
        fragmentViewModel.usersLiveData.observe(viewLifecycleOwner) {
            usersList.clear()
            usersList.addAll(it)
            usersAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        usersAdapter = GenericDataAdapter(usersList, R.layout.user_list_item) {
            showToast(it.firstName, Toast.LENGTH_SHORT)
        }

        fragmentBinding.userRv.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = usersAdapter
        }
    }
}