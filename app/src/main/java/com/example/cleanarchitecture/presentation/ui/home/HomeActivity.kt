package com.example.cleanarchitecture.presentation.ui.home

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityHomeBinding
import com.example.cleanarchitecture.base.views.BaseActivity
import com.example.cleanarchitecture.base.viewmodel.BaseViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, BaseViewModel>(
    R.layout.activity_home,
    BaseViewModel::class.java
)