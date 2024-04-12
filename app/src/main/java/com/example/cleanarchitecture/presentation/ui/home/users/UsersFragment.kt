package com.example.cleanarchitecture.presentation.ui.home.users

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.core.data.User
import com.example.cleanarchitecture.databinding.FragmentUsersBinding
import com.example.cleanarchitecture.presentation.utils.adapters.GenericDataAdapter
import com.example.cleanarchitecture.presentation.utils.base_classes.BaseFragment

class UsersFragment : BaseFragment<FragmentUsersBinding, UsersViewModel>(
    R.layout.fragment_users, UsersViewModel::class.java
) {

    private lateinit var usersAdapter: GenericDataAdapter<User>
    private val usersList: MutableList<User> = mutableListOf()

    override fun setUpView() {
        setUpRecyclerView()
        setUpViewModel()

        super.setUpView()
    }

    private fun setUpViewModel() {
        fragmentViewModel.usersLiveData.observe(viewLifecycleOwner) {
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